package com.example.bookapp.repository

import com.example.bookapp.data.dao.PrestamoDao
import com.example.bookapp.data.dao.LibroDao
import com.example.bookapp.data.entities.PrestamoEntity
import com.example.bookapp.data.entities.LibroEstado
import com.example.bookapp.data.entities.PrestamoConDetalles
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull

class PrestamoRepository(
    private val prestamoDao: PrestamoDao,
    private val libroDao: LibroDao
) {
    private val firestore = FirebaseFirestore.getInstance()
    private val prestamosCollection = firestore.collection("prestamos")
    private val librosCollection = firestore.collection("libros")

    val allPrestamos: Flow<List<PrestamoEntity>> = prestamoDao.getAllPrestamos()
    val prestamosActivos: Flow<List<PrestamoEntity>> = prestamoDao.getPrestamosActivos()
    val prestamosActivosConDetalles: Flow<List<PrestamoConDetalles>> = prestamoDao.getPrestamosActivosConDetalles()

    suspend fun registrarPrestamo(prestamo: PrestamoEntity) {
        val libro = libroDao.getLibroById(prestamo.libroId)
        if (libro == null || libro.ejemplares <= 0) return

        val id = prestamoDao.insert(prestamo).toInt()
        val valorFijo = 50.0
        val prestamoConId = prestamo.copy(id = id, valorPrestamo = valorFijo)
        
        // Update local book
        val nuevosEjemplares = libro.ejemplares - 1
        val libroActualizado = libro.copy(
            ejemplares = nuevosEjemplares,
            estado = if (nuevosEjemplares <= 0) LibroEstado.PRESTADO else LibroEstado.DISPONIBLE
        )
        libroDao.update(libroActualizado)
        
        // Sync to Firestore
        syncLibroToFirestore(libroActualizado)
        
        // Update local loan with calculated value
        prestamoDao.update(prestamoConId)
        syncPrestamoToFirestore(prestamoConId)
    }

    suspend fun registrarDevolucion(prestamo: PrestamoEntity) {
        prestamoDao.update(prestamo)
        syncPrestamoToFirestore(prestamo)
        
        val libro = libroDao.getLibroById(prestamo.libroId)
        libro?.let {
            val libroActualizado = it.copy(
                ejemplares = it.ejemplares + 1,
                estado = LibroEstado.DISPONIBLE
            )
            libroDao.update(libroActualizado)
            syncLibroToFirestore(libroActualizado)
        }
    }

    private suspend fun syncLibroToFirestore(libro: com.example.bookapp.data.entities.LibroEntity) {
        try {
            withTimeoutOrNull(3000) {
                librosCollection.document(libro.id.toString()).set(libro).await()
            }
        } catch (e: Exception) { e.printStackTrace() }
    }

    private suspend fun syncPrestamoToFirestore(prestamo: PrestamoEntity) {
        try {
            withTimeoutOrNull(3000) {
                prestamosCollection.document(prestamo.id.toString()).set(prestamo).await()
            }
        } catch (e: Exception) { e.printStackTrace() }
    }

    suspend fun getPrestamoById(id: Int): PrestamoEntity? = prestamoDao.getPrestamoById(id)

    fun getHistorialPrestamosConDetalles() = prestamoDao.getHistorialPrestamosConDetalles()
    fun getIngresosPorMes(mes: String): Flow<Double?> = prestamoDao.getTotalIngresosDelMes(mes)
    fun getIngresosPorAnio(anio: String): Flow<Double?> = prestamoDao.getTotalIngresosDelAnio(anio)
    fun getPrestamosPorMes(mes: String): Flow<List<PrestamoEntity>> = prestamoDao.getPrestamosDelMes(mes)
    fun getPrestamosPorCorreoSocio(correo: String): Flow<List<PrestamoConDetalles>> = prestamoDao.getPrestamosPorCorreoSocio(correo)
    fun getPrestamosActivosPorSocio(socioId: Int): Flow<List<PrestamoConDetalles>> = prestamoDao.getPrestamosActivosPorSocio(socioId)
    fun getTop5Libros() = prestamoDao.getTop5LibrosMasPrestados()
}
