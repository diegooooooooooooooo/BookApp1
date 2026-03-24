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
    @Insert
    suspend fun insert(prestamo: PrestamoEntity): Long

    @Update
    suspend fun update(prestamo: PrestamoEntity)

    @Query("SELECT * FROM prestamos")
    fun getAllPrestamos(): Flow<List<PrestamoEntity>>

    // Obtener préstamos del mes actual para reportes
    @Query("SELECT * FROM prestamos WHERE strftime('%m', datetime(fechaEntregaReal/1000, 'unixepoch')) = :mes")
    fun getPrestamosDelMes(mes: String): Flow<List<PrestamoEntity>>

    // Calcular el total de multas (ingresos) del mes
    @Query("SELECT SUM(multa) FROM prestamos WHERE strftime('%m', datetime(fechaEntregaReal/1000, 'unixepoch')) = :mes")
    fun getTotalMultasDelMes(mes: String): Flow<Double?>

    // Obtener préstamos activos (no devueltos)
    @Query("SELECT * FROM prestamos WHERE fechaEntregaReal IS NULL")
    fun getPrestamosActivos(): Flow<List<PrestamoEntity>>
}
