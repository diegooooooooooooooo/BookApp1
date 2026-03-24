package com.example.bookapp.repository

import com.example.bookapp.data.dao.LibroDao
import com.example.bookapp.data.dao.PrestamoDao
import com.example.bookapp.data.dao.UsuarioDao
import com.example.bookapp.data.dao.SocioDao
import com.example.bookapp.data.entities.LibroEntity
import com.example.bookapp.data.entities.PrestamoEntity
import com.example.bookapp.data.entities.UsuarioEntity
import com.example.bookapp.data.entities.SocioEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class BibliotecaRepository(
    private val libroDao: LibroDao,
    private val usuarioDao: UsuarioDao,
    private val prestamoDao: PrestamoDao,
    private val socioDao: SocioDao
) {
    private val firestore = FirebaseFirestore.getInstance()
    
    // --- Firestore Collections ---
    private val librosCollection = firestore.collection("libros")
    private val usuariosCollection = firestore.collection("usuarios")
    private val sociosCollection = firestore.collection("socios")
    private val prestamosCollection = firestore.collection("prestamos")

    // --- Libros ---
    val allLibros: Flow<List<LibroEntity>> = libroDao.getAllLibros()
    
    suspend fun insertLibro(libro: LibroEntity) {
        // 1. Guardar en Room
        val id = libroDao.insert(libro).toInt()
        // 2. Sincronizar con Firestore
        val libroConId = libro.copy(id = id)
        librosCollection.document(id.toString()).set(libroConId).await()
    }
    
    suspend fun updateLibro(libro: LibroEntity) {
        libroDao.update(libro)
        librosCollection.document(libro.id.toString()).set(libro).await()
    }
    
    fun searchLibros(query: String) = libroDao.searchLibros(query)
    suspend fun getLibroById(id: Int) = libroDao.getLibroById(id)

    // --- Usuarios (App accounts) ---
    val allUsuarios: Flow<List<UsuarioEntity>> = usuarioDao.getAllUsuarios()
    
    suspend fun insertUsuario(usuario: UsuarioEntity) {
        val id = usuarioDao.insert(usuario).toInt()
        val usuarioConId = usuario.copy(id = id)
        usuariosCollection.document(id.toString()).set(usuarioConId).await()
    }
    
    suspend fun getUsuarioByCorreo(correo: String) = usuarioDao.getUsuarioByCorreo(correo)

    // --- Socios (Readers) ---
    val allSocios: Flow<List<SocioEntity>> = socioDao.getAllSocios()
    
    suspend fun insertSocio(socio: SocioEntity) {
        val id = socioDao.insert(socio).toInt()
        val socioConId = socio.copy(id = id)
        sociosCollection.document(id.toString()).set(socioConId).await()
    }

    // --- Préstamos ---
    val allPrestamos: Flow<List<PrestamoEntity>> = prestamoDao.getAllPrestamos()
    val prestamosActivos: Flow<List<PrestamoEntity>> = prestamoDao.getPrestamosActivos()
    
    suspend fun registrarPrestamo(prestamo: PrestamoEntity) {
        val id = prestamoDao.insert(prestamo).toInt()
        val prestamoConId = prestamo.copy(id = id)
        
        // Actualizar disponibilidad del libro localmente
        val libro = libroDao.getLibroById(prestamo.libroId)
        libro?.let {
            val libroActualizado = it.copy(disponible = false)
            libroDao.update(libroActualizado)
            // Sincronizar libro en Firestore
            librosCollection.document(libroActualizado.id.toString()).set(libroActualizado).await()
        }
        
        // Sincronizar préstamo en Firestore
        prestamosCollection.document(id.toString()).set(prestamoConId).await()
    }

    suspend fun registrarDevolucion(prestamo: PrestamoEntity) {
        prestamoDao.update(prestamo)
        prestamosCollection.document(prestamo.id.toString()).set(prestamo).await()
        
        val libro = libroDao.getLibroById(prestamo.libroId)
        libro?.let {
            val libroActualizado = it.copy(disponible = true)
            libroDao.update(libroActualizado)
            // Sincronizar libro en Firestore
            librosCollection.document(libroActualizado.id.toString()).set(libroActualizado).await()
        }
    }

    fun getMultasPorMes(mes: String): Flow<Double?> = prestamoDao.getTotalMultasDelMes(mes)
    fun getPrestamosPorMes(mes: String): Flow<List<PrestamoEntity>> = prestamoDao.getPrestamosDelMes(mes)
    
    /**
     * Función para descargar datos de Firestore a Room (Sincronización inicial o manual)
     */
    suspend fun syncFromFirestore() {
        try {
            // Sincronizar Libros
            val librosSnapshot = librosCollection.get().await()
            val remoteLibros = librosSnapshot.toObjects(LibroEntity::class.java)
            remoteLibros.forEach { libroDao.insert(it) }
            
            // Sincronizar Socios
            val sociosSnapshot = sociosCollection.get().await()
            val remoteSocios = sociosSnapshot.toObjects(SocioEntity::class.java)
            remoteSocios.forEach { socioDao.insert(it) }
            
            // Sincronizar Usuarios
            val usuariosSnapshot = usuariosCollection.get().await()
            val remoteUsuarios = usuariosSnapshot.toObjects(UsuarioEntity::class.java)
            remoteUsuarios.forEach { usuarioDao.insert(it) }
            
            // Sincronizar Préstamos
            val prestamosSnapshot = prestamosCollection.get().await()
            val remotePrestamos = prestamosSnapshot.toObjects(PrestamoEntity::class.java)
            remotePrestamos.forEach { prestamoDao.insert(it) }
            
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
