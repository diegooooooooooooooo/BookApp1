package com.example.bookapp.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entidad que representa a un socio o lector de la biblioteca (quien pide prestado).
 */
@Entity(
    tableName = "socios",
    indices = [Index(value = ["correo"], unique = true)]
)
data class SocioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String = "",
    val dni: String = "",
    val telefono: String = "",
    val correo: String = ""
)
