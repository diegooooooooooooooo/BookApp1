package com.example.bookapp.repository

import com.example.bookapp.data.dao.LibroDao
import com.example.bookapp.data.dao.PrestamoDao
import com.example.bookapp.data.dao.UsuarioDao
import com.example.bookapp.data.dao.SocioDao
import com.example.bookapp.data.entities.LibroEntity
import com.example.bookapp.data.entities.PrestamoEntity
import com.example.bookapp.data.entities.UsuarioEntity
import com.example.bookapp.data.entities.SocioEntity
import kotlinx.coroutines.flow.Flow

class BibliotecaRepository(
    private val libroDao: LibroDao,
    private val usuarioDao: UsuarioDao,
    private val prestamoDao: PrestamoDao,
    private val socioDao: SocioDao
) {
    // Libros
    val allLibros: Flow<List<LibroEntity>> = libroDao.getAllLibros()
    suspend fun insertLibro(libro: LibroEntity) = libroDao.insert(libro)
    suspend fun updateLibro(libro: LibroEntity) = libroDao.update(libro)
    fun searchLibros(query: String) = libroDao.searchLibros(query)
    suspend fun getLibroById(id: Int) = libroDao.getLibroById(id)

    // Usuarios (App accounts)
    val allUsuarios: Flow<List<UsuarioEntity>> = usuarioDao.getAllUsuarios()
    suspend fun insertUsuario(usuario: UsuarioEntity) = usuarioDao.insert(usuario)
    suspend fun getUsuarioByCorreo(correo: String) = usuarioDao.getUsuarioByCorreo(correo)

    // Socios (Readers)
    val allSocios: Flow<List<SocioEntity>> = socioDao.getAllSocios()
    suspend fun insertSocio(socio: SocioEntity) = socioDao.insert(socio)

    // Préstamos
    val allPrestamos: Flow<List<PrestamoEntity>> = prestamoDao.getAllPrestamos()
    val prestamosActivos: Flow<List<PrestamoEntity>> = prestamoDao.getPrestamosActivos()
    
    suspend fun registrarPrestamo(prestamo: PrestamoEntity) {
        prestamoDao.insert(prestamo)
        val libro = libroDao.getLibroById(prestamo.libroId)
        libro?.let {
            libroDao.update(it.copy(disponible = false))
        }
    }

    suspend fun registrarDevolucion(prestamo: PrestamoEntity) {
        prestamoDao.update(prestamo)
        val libro = libroDao.getLibroById(prestamo.libroId)
        libro?.let {
            libroDao.update(it.copy(disponible = true))
        }
    }

    fun getMultasPorMes(mes: String): Flow<Double?> = prestamoDao.getTotalMultasDelMes(mes)
    fun getPrestamosPorMes(mes: String): Flow<List<PrestamoEntity>> = prestamoDao.getPrestamosDelMes(mes)
}
