package com.example.bookapp.repository

import com.example.bookapp.data.dao.LibroDao
import com.example.bookapp.data.dao.PrestamoDao
import com.example.bookapp.data.dao.UsuarioDao
import com.example.bookapp.data.dao.SocioDao
import com.example.bookapp.data.entities.LibroEntity
import com.example.bookapp.data.entities.LibroEstado
import com.example.bookapp.data.entities.PrestamoEntity
import com.example.bookapp.data.entities.UsuarioEntity
import com.example.bookapp.data.entities.SocioEntity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.DocumentChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull

class BibliotecaRepository(
    private val libroDao: LibroDao,
    private val usuarioDao: UsuarioDao,
    private val prestamoDao: PrestamoDao,
    private val socioDao: SocioDao
) {
    private val firestore = FirebaseFirestore.getInstance()
    private val repositoryScope = CoroutineScope(Dispatchers.IO)
    
    // --- Firestore Collections ---
    private val librosCollection = firestore.collection("libros")
    private val usuariosCollection = firestore.collection("usuarios")
    private val sociosCollection = firestore.collection("socios")
    private val prestamosCollection = firestore.collection("prestamos")

    /**
     * Inicia la sincronización en tiempo real desde Firestore hacia Room.
     * Cualquier cambio realizado por otro usuario en la nube se reflejará localmente.
     */
    fun startRealtimeSync() {
        // Escuchar cambios en Libros
        librosCollection.addSnapshotListener { snapshot, e ->
            if (e != null) return@addSnapshotListener
            snapshot?.documentChanges?.forEach { change ->
                val entity = change.document.toObject(LibroEntity::class.java)
                repositoryScope.launch {
                    when (change.type) {
                        DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED -> libroDao.insert(entity)
                        DocumentChange.Type.REMOVED -> libroDao.delete(entity)
                    }
                }
            }
        }

        // Escuchar cambios en Usuarios
        usuariosCollection.addSnapshotListener { snapshot, e ->
            if (e != null) return@addSnapshotListener
            snapshot?.documentChanges?.forEach { change ->
                val entity = change.document.toObject(UsuarioEntity::class.java)
                repositoryScope.launch {
                    when (change.type) {
                        DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED -> {
                            usuarioDao.insert(entity)
                            ensureLectorIsSocio(entity)
                        }
                        DocumentChange.Type.REMOVED -> { /* Los usuarios no suelen borrarse en esta app */ }
                    }
                }
            }
        }

        // Escuchar cambios en Socios
        sociosCollection.addSnapshotListener { snapshot, e ->
            if (e != null) return@addSnapshotListener
            snapshot?.documentChanges?.forEach { change ->
                val entity = change.document.toObject(SocioEntity::class.java)
                repositoryScope.launch {
                    when (change.type) {
                        DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED -> socioDao.insert(entity)
                        DocumentChange.Type.REMOVED -> socioDao.delete(entity)
                    }
                }
            }
        }

        // Escuchar cambios en Préstamos
        prestamosCollection.addSnapshotListener { snapshot, e ->
            if (e != null) return@addSnapshotListener
            snapshot?.documentChanges?.forEach { change ->
                val entity = change.document.toObject(PrestamoEntity::class.java)
                repositoryScope.launch {
                    when (change.type) {
                        DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED -> prestamoDao.insert(entity)
                        DocumentChange.Type.REMOVED -> { /* Opcional: manejar borrado */ }
                    }
                }
            }
        }
    }

    // --- Libros ---
    val allLibros: Flow<List<LibroEntity>> = libroDao.getAllLibros()
    
    suspend fun insertLibro(libro: LibroEntity) {
        // Ajustar estado según ejemplares
        val libroAjustado = if (libro.ejemplares <= 0) {
            libro.copy(estado = LibroEstado.NO_DISPONIBLE)
        } else if (libro.estado == LibroEstado.NO_DISPONIBLE) {
            libro.copy(estado = LibroEstado.DISPONIBLE)
        } else {
            libro
        }

        // 1. Guardar en Room
        val id = libroDao.insert(libroAjustado).toInt()
        // 2. Sincronizar con Firestore
        try {
            val libroConId = libroAjustado.copy(id = id)
            withTimeoutOrNull(3000) {
                librosCollection.document(id.toString()).set(libroConId).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    suspend fun updateLibro(libro: LibroEntity) {
        // Ajustar estado según ejemplares
        val libroAjustado = if (libro.ejemplares <= 0) {
            libro.copy(estado = LibroEstado.NO_DISPONIBLE)
        } else if (libro.estado == LibroEstado.NO_DISPONIBLE) {
            libro.copy(estado = LibroEstado.DISPONIBLE)
        } else {
            libro
        }

        libroDao.update(libroAjustado)
        try {
            withTimeoutOrNull(3000) {
                librosCollection.document(libroAjustado.id.toString()).set(libroAjustado).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun deleteLibro(libro: LibroEntity) {
        libroDao.delete(libro)
        try {
            withTimeoutOrNull(3000) {
                librosCollection.document(libro.id.toString()).delete().await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    fun searchLibros(query: String) = libroDao.searchLibros(query)
    suspend fun getLibroById(id: Int) = libroDao.getLibroById(id)

    // --- Usuarios (App accounts) ---
    val allUsuarios: Flow<List<UsuarioEntity>> = usuarioDao.getAllUsuarios()
    
    suspend fun insertUsuario(usuario: UsuarioEntity) {
        val id = usuarioDao.insert(usuario).toInt()
        val usuarioConId = usuario.copy(id = id)
        ensureLectorIsSocio(usuarioConId)
        try {
            withTimeoutOrNull(3000) {
                usuariosCollection.document(id.toString()).set(usuarioConId).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun ensureLectorIsSocio(usuario: UsuarioEntity) {
        if (usuario.rol == "LECTOR" || usuario.rol == "USUARIO") {
            val existingSocio = socioDao.getSocioByCorreo(usuario.correo)
            if (existingSocio == null) {
                socioDao.insert(SocioEntity(nombre = usuario.nombre, correo = usuario.correo))
            } else if (existingSocio.nombre != usuario.nombre) {
                socioDao.update(existingSocio.copy(nombre = usuario.nombre))
            }
        }
    }
    
    suspend fun getUsuarioByCorreo(correo: String) = usuarioDao.getUsuarioByCorreo(correo)

    suspend fun syncUsuariosFromFirestore() {
        try {
            val snapshot = withTimeoutOrNull(5000) {
                usuariosCollection.get().await()
            }
            val usuarios = snapshot?.toObjects(UsuarioEntity::class.java)
            usuarios?.forEach { usuario ->
                val local = usuarioDao.getUsuarioByCorreo(usuario.correo)
                if (local == null) {
                    usuarioDao.insert(usuario)
                    ensureLectorIsSocio(usuario)
                } else {
                    val updated = usuario.copy(id = local.id)
                    usuarioDao.update(updated)
                    ensureLectorIsSocio(updated)
                }
            }
        } catch (e: Exception) {
            // Manejar error de sincronización
        }
    }

    // --- Socios (Readers) ---
    val allSocios: Flow<List<SocioEntity>> = socioDao.getAllSocios()
    
    suspend fun insertSocio(socio: SocioEntity) {
        val id = socioDao.insert(socio).toInt()
        try {
            val socioConId = socio.copy(id = id)
            withTimeoutOrNull(3000) {
                sociosCollection.document(id.toString()).set(socioConId).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // --- Préstamos ---
    val allPrestamos: Flow<List<PrestamoEntity>> = prestamoDao.getAllPrestamos()
    val prestamosActivos: Flow<List<PrestamoEntity>> = prestamoDao.getPrestamosActivos()
    val prestamosActivosConDetalles: Flow<List<com.example.bookapp.data.entities.PrestamoConDetalles>> = 
        prestamoDao.getPrestamosActivosConDetalles()
    
    suspend fun registrarPrestamo(prestamo: PrestamoEntity) {
        // Verificar disponibilidad del libro
        val libro = libroDao.getLibroById(prestamo.libroId)
        if (libro == null || libro.ejemplares <= 0) {
            // No hay ejemplares disponibles para préstamo
            return
        }

        val id = prestamoDao.insert(prestamo).toInt()
        val prestamoConId = prestamo.copy(
            id = id,
            valorPrestamo = libro.valor * 0.1 // Ejemplo: costo de préstamo es 10% del valor del libro
        )
        
        // Actualizar ejemplares y estado del libro localmente
        val nuevosEjemplares = libro.ejemplares - 1
        val libroActualizado = libro.copy(
            ejemplares = nuevosEjemplares,
            estado = if (nuevosEjemplares <= 0) LibroEstado.PRESTADO else LibroEstado.DISPONIBLE
        )
        
        libroDao.update(libroActualizado)
        // Sincronizar libro en Firestore
        try {
            withTimeoutOrNull(3000) {
                librosCollection.document(libroActualizado.id.toString()).set(libroActualizado).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        // Guardar de nuevo con el valor calculado
        prestamoDao.update(prestamoConId)
        
        // Sincronizar préstamo en Firestore
        try {
            withTimeoutOrNull(3000) {
                prestamosCollection.document(id.toString()).set(prestamoConId).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getPrestamoById(id: Int): PrestamoEntity? = prestamoDao.getPrestamoById(id)

    suspend fun registrarDevolucion(prestamo: PrestamoEntity) {
        prestamoDao.update(prestamo)
        try {
            withTimeoutOrNull(3000) {
                prestamosCollection.document(prestamo.id.toString()).set(prestamo).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        val libro = libroDao.getLibroById(prestamo.libroId)
        libro?.let {
            // Al devolver, incrementamos los ejemplares y aseguramos que esté disponible
            val libroActualizado = it.copy(
                ejemplares = it.ejemplares + 1,
                estado = LibroEstado.DISPONIBLE
            )
            libroDao.update(libroActualizado)
            // Sincronizar libro en Firestore
            try {
                withTimeoutOrNull(3000) {
                    librosCollection.document(libroActualizado.id.toString()).set(libroActualizado).await()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getIngresosPorMes(mes: String): Flow<Double?> = prestamoDao.getTotalIngresosDelMes(mes)
    fun getIngresosPorAnio(anio: String): Flow<Double?> = prestamoDao.getTotalIngresosDelAnio(anio)
    fun getPrestamosPorMes(mes: String): Flow<List<PrestamoEntity>> = prestamoDao.getPrestamosDelMes(mes)

    fun getPrestamosPorCorreoSocio(correo: String): Flow<List<com.example.bookapp.data.entities.PrestamoConDetalles>> = 
        prestamoDao.getPrestamosPorCorreoSocio(correo)

    fun getTop5Libros() = prestamoDao.getTop5LibrosMasPrestados()
    
    /**
     * Función para descargar datos de Firestore a Room (Sincronización inicial o manual)
     */
    suspend fun syncFromFirestore() {
        try {
            // Sincronizar Libros
            try {
                val librosSnapshot = withTimeoutOrNull(5000) { librosCollection.get().await() }
                val remoteLibros = librosSnapshot?.toObjects(LibroEntity::class.java)
                remoteLibros?.forEach { libroDao.insert(it) }
            } catch (e: Exception) { e.printStackTrace() }
            
            // Sincronizar Socios
            try {
                val sociosSnapshot = withTimeoutOrNull(5000) { sociosCollection.get().await() }
                val remoteSocios = sociosSnapshot?.toObjects(SocioEntity::class.java)
                remoteSocios?.forEach { socioDao.insert(it) }
            } catch (e: Exception) { e.printStackTrace() }
            
            // Sincronizar Usuarios
            try {
                val usuariosSnapshot = withTimeoutOrNull(5000) { usuariosCollection.get().await() }
                val remoteUsuarios = usuariosSnapshot?.toObjects(UsuarioEntity::class.java)
                remoteUsuarios?.forEach { 
                    usuarioDao.insert(it)
                    ensureLectorIsSocio(it)
                }
            } catch (e: Exception) { e.printStackTrace() }
            
            // Sincronizar Préstamos
            try {
                val prestamosSnapshot = withTimeoutOrNull(5000) { prestamosCollection.get().await() }
                val remotePrestamos = prestamosSnapshot?.toObjects(PrestamoEntity::class.java)
                remotePrestamos?.forEach { prestamoDao.insert(it) }
            } catch (e: Exception) { e.printStackTrace() }
            
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
