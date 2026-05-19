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
import com.example.bookapp.data.model.UserRole
import com.example.bookapp.data.api.OpenLibraryService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    
    // Delegated repositories for internal use if needed, but the public API should eventually migrate
    val libroRepository = LibroRepository(libroDao)
    val usuarioRepository = UsuarioRepository(usuarioDao, socioDao)
    val prestamoRepository = PrestamoRepository(prestamoDao, libroDao)
    val socioRepository = SocioRepository(socioDao)

    // Keep collections for the realtime sync logic which is still centralized here for now
    private val librosCollection = firestore.collection("libros")
    private val usuariosCollection = firestore.collection("usuarios")
    private val sociosCollection = firestore.collection("socios")
    private val prestamosCollection = firestore.collection("prestamos")

    private val apiService: OpenLibraryService by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(OpenLibraryService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(OpenLibraryService::class.java)
    }

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
    val allLibros: Flow<List<LibroEntity>> = libroRepository.allLibros
    
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    suspend fun insertLibro(libro: LibroEntity): Int = libroRepository.insertLibro(libro)
=======
    suspend fun insertLibro(libro: LibroEntity) = libroRepository.insertLibro(libro)
>>>>>>> 0800574 (Versión más acutual)
=======
    suspend fun insertLibro(libro: LibroEntity): Int = libroRepository.insertLibro(libro)
>>>>>>> experimental
=======
    suspend fun insertLibro(libro: LibroEntity): Int = libroRepository.insertLibro(libro)
>>>>>>> 7ae96b5 (Versión más acutual)
    suspend fun updateLibro(libro: LibroEntity) = libroRepository.updateLibro(libro)
    suspend fun deleteLibro(libro: LibroEntity) = libroRepository.deleteLibro(libro)
    
    fun searchLibros(query: String) = libroDao.searchLibros(query)
    suspend fun getLibroById(id: Int) = libroRepository.getLibroById(id)

    // --- Usuarios (App accounts) ---
    val allUsuarios: Flow<List<UsuarioEntity>> = usuarioRepository.allUsuarios
    
    suspend fun insertUsuario(usuario: UsuarioEntity) = usuarioRepository.insertUsuario(usuario)
    suspend fun getUsuarioByCorreo(correo: String) = usuarioRepository.getUsuarioByCorreo(correo)
    suspend fun updateUsuario(usuario: UsuarioEntity) = usuarioRepository.updateUsuario(usuario)
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    suspend fun ensureLectorIsSocio(usuario: UsuarioEntity) = usuarioRepository.ensureLectorIsSocio(usuario)
=======
>>>>>>> 0800574 (Versión más acutual)
=======
    suspend fun ensureLectorIsSocio(usuario: UsuarioEntity) = usuarioRepository.ensureLectorIsSocio(usuario)
>>>>>>> experimental
=======
    suspend fun ensureLectorIsSocio(usuario: UsuarioEntity) = usuarioRepository.ensureLectorIsSocio(usuario)
>>>>>>> 7ae96b5 (Versión más acutual)
    suspend fun syncUsuariosFromFirestore() = usuarioRepository.syncUsuariosFromFirestore()

    // --- Socios (Readers) ---
    val allSocios: Flow<List<SocioEntity>> = socioRepository.allSocios
    suspend fun insertSocio(socio: SocioEntity) = socioRepository.insertSocio(socio)

    // --- Préstamos ---
    val allPrestamos: Flow<List<PrestamoEntity>> = prestamoRepository.allPrestamos
    val prestamosActivos: Flow<List<PrestamoEntity>> = prestamoRepository.prestamosActivos
    val prestamosActivosConDetalles: Flow<List<com.example.bookapp.data.entities.PrestamoConDetalles>> = 
        prestamoRepository.prestamosActivosConDetalles
    
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    suspend fun registrarPrestamo(prestamo: PrestamoEntity): Result<Unit> = prestamoRepository.registrarPrestamo(prestamo)
=======
    suspend fun registrarPrestamo(prestamo: PrestamoEntity) = prestamoRepository.registrarPrestamo(prestamo)
>>>>>>> 0800574 (Versión más acutual)
=======
    suspend fun registrarPrestamo(prestamo: PrestamoEntity): Result<Unit> = prestamoRepository.registrarPrestamo(prestamo)
>>>>>>> experimental
=======
    suspend fun registrarPrestamo(prestamo: PrestamoEntity): Result<Unit> = prestamoRepository.registrarPrestamo(prestamo)
>>>>>>> 7ae96b5 (Versión más acutual)
    suspend fun getPrestamoById(id: Int): PrestamoEntity? = prestamoRepository.getPrestamoById(id)
    suspend fun registrarDevolucion(prestamo: PrestamoEntity) = prestamoRepository.registrarDevolucion(prestamo)

    fun getHistorialPrestamosConDetalles() = prestamoRepository.getHistorialPrestamosConDetalles()
    fun getIngresosPorMes(mes: String): Flow<Double?> = prestamoRepository.getIngresosPorMes(mes)
    fun getIngresosPorAnio(anio: String): Flow<Double?> = prestamoRepository.getIngresosPorAnio(anio)
    fun getPrestamosPorMes(mes: String): Flow<List<PrestamoEntity>> = prestamoRepository.getPrestamosPorMes(mes)

    fun getPrestamosPorCorreoSocio(correo: String): Flow<List<com.example.bookapp.data.entities.PrestamoConDetalles>> = 
        prestamoRepository.getPrestamosPorCorreoSocio(correo)

    fun getPrestamosActivosPorSocio(socioId: Int): Flow<List<com.example.bookapp.data.entities.PrestamoConDetalles>> =
        prestamoRepository.getPrestamosActivosPorSocio(socioId)

    fun getTop5Libros() = prestamoRepository.getTop5Libros()
    
    suspend fun searchOnline(query: String) = apiService.searchBooks(query)

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
    private suspend fun ensureLectorIsSocio(usuario: UsuarioEntity) {
        if (usuario.rol == UserRole.LECTOR || usuario.rol == UserRole.USUARIO) {
            val existingSocio = socioDao.getSocioByCorreo(usuario.correo)
            if (existingSocio == null) {
                socioDao.insert(SocioEntity(nombre = usuario.nombre, correo = usuario.correo))
            } else if (existingSocio.nombre != usuario.nombre) {
                socioDao.update(existingSocio.copy(nombre = usuario.nombre))
            }
        }
    }

>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
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
