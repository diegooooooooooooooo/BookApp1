package com.example.bookapp.data.dao

import androidx.room.*
import com.example.bookapp.data.entities.PrestamoEntity
import kotlinx.coroutines.flow.Flow

/**
 * Acceso a datos para la tabla de préstamos.
 * Incluye lógica para reportes administrativos.
 */
@Dao
interface PrestamoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamo: PrestamoEntity): Long

    @Update
    suspend fun update(prestamo: PrestamoEntity)

    @Query("SELECT * FROM prestamos WHERE id = :id")
    suspend fun getPrestamoById(id: Int): PrestamoEntity?

    @Query("SELECT * FROM prestamos")
    fun getAllPrestamos(): Flow<List<PrestamoEntity>>

    // Obtener préstamos del mes actual para reportes
    @Query("SELECT * FROM prestamos WHERE strftime('%m', datetime(fechaEntregaReal/1000, 'unixepoch')) = :mes")
    fun getPrestamosDelMes(mes: String): Flow<List<PrestamoEntity>>

    // Calcular el total de multas (ingresos) del mes
    @Query("SELECT SUM(multa + valorPrestamo) FROM prestamos WHERE strftime('%m', datetime(fechaPrestamo/1000, 'unixepoch')) = :mes")
    fun getTotalIngresosDelMes(mes: String): Flow<Double?>

    // Calcular el total de ingresos del año
    @Query("SELECT SUM(multa + valorPrestamo) FROM prestamos WHERE strftime('%Y', datetime(fechaPrestamo/1000, 'unixepoch')) = :anio")
    fun getTotalIngresosDelAnio(anio: String): Flow<Double?>

    // Obtener préstamos activos (no devueltos)
    @Query("SELECT * FROM prestamos WHERE fechaEntregaReal IS NULL")
    fun getPrestamosActivos(): Flow<List<PrestamoEntity>>

    @Query("""
        SELECT prestamos.id, socios.nombre as socioNombre, libros.titulo as libroTitulo, prestamos.libroId,
               prestamos.fechaPrestamo, prestamos.fechaDevolucionEsperada, 
               prestamos.fechaEntregaReal, prestamos.multa, prestamos.valorPrestamo 
        FROM prestamos 
        INNER JOIN socios ON prestamos.socioId = socios.id 
        INNER JOIN libros ON prestamos.libroId = libros.id 
        WHERE prestamos.fechaEntregaReal IS NULL
    """)
    fun getPrestamosActivosConDetalles(): Flow<List<com.example.bookapp.data.entities.PrestamoConDetalles>>

    @Query("""
        SELECT prestamos.id, socios.nombre as socioNombre, libros.titulo as libroTitulo, prestamos.libroId,
               prestamos.fechaPrestamo, prestamos.fechaDevolucionEsperada, 
               prestamos.fechaEntregaReal, prestamos.multa, prestamos.valorPrestamo 
        FROM prestamos 
        INNER JOIN socios ON prestamos.socioId = socios.id 
        INNER JOIN libros ON prestamos.libroId = libros.id 
        WHERE prestamos.fechaEntregaReal IS NULL AND prestamos.socioId = :socioId
    """)
    fun getPrestamosActivosPorSocio(socioId: Int): Flow<List<com.example.bookapp.data.entities.PrestamoConDetalles>>

    @Query("""
        SELECT prestamos.id, socios.nombre as socioNombre, libros.titulo as libroTitulo, prestamos.libroId,
               prestamos.fechaPrestamo, prestamos.fechaDevolucionEsperada, 
               prestamos.fechaEntregaReal, prestamos.multa, prestamos.valorPrestamo 
        FROM prestamos 
        INNER JOIN socios ON prestamos.socioId = socios.id 
        INNER JOIN libros ON prestamos.libroId = libros.id 
        WHERE LOWER(socios.correo) = LOWER(:correo)
    """)
    fun getPrestamosPorCorreoSocio(correo: String): Flow<List<com.example.bookapp.data.entities.PrestamoConDetalles>>

    @Query("""
        SELECT prestamos.id, socios.nombre as socioNombre, libros.titulo as libroTitulo, prestamos.libroId,
               prestamos.fechaPrestamo, prestamos.fechaDevolucionEsperada, 
               prestamos.fechaEntregaReal, prestamos.multa, prestamos.valorPrestamo 
        FROM prestamos 
        INNER JOIN socios ON prestamos.socioId = socios.id 
        INNER JOIN libros ON prestamos.libroId = libros.id 
        ORDER BY prestamos.fechaPrestamo DESC
    """)
    fun getHistorialPrestamosConDetalles(): Flow<List<com.example.bookapp.data.entities.PrestamoConDetalles>>

    @Query("""
        SELECT libros.*, COUNT(prestamos.id) as prestamosCount 
        FROM libros 
        INNER JOIN prestamos ON libros.id = prestamos.libroId 
        GROUP BY libros.id 
        ORDER BY prestamosCount DESC 
        LIMIT 5
    """)
    fun getTop5LibrosMasPrestados(): Flow<List<LibroConConteo>>

    data class LibroConConteo(
        @Embedded val libro: com.example.bookapp.data.entities.LibroEntity,
        val prestamosCount: Int
    )
}
