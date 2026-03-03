package com.example.bookapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa un préstamo de un libro a un socio (lector).
 */
@Entity(tableName = "prestamos")
data class PrestamoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val socioId: Int,
    val libroId: Int,
    val fechaPrestamo: Long, // Almacenado como timestamp
    val fechaDevolucionEsperada: Long,
    val fechaEntregaReal: Long? = null,
    val multa: Double = 0.0
)
