package com.example.bookapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa un préstamo de un libro a un socio (lector).
 */
@Entity(tableName = "prestamos")
data class PrestamoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val socioId: Int = 0,
    val libroId: Int = 0,
    val fechaPrestamo: Long = 0L,
    val fechaDevolucionEsperada: Long = 0L,
    val fechaEntregaReal: Long? = null,
    val multa: Double = 0.0,
    val valorPrestamo: Double = 0.0
)

data class PrestamoConDetalles(
    val id: Int,
    val socioNombre: String,
    val libroTitulo: String,
    val fechaPrestamo: Long,
    val fechaDevolucionEsperada: Long,
    val fechaEntregaReal: Long?,
    val multa: Double
)
