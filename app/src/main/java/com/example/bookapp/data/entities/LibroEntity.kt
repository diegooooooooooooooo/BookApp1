package com.example.bookapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa un libro en el catálogo.
 * Se agregan valores por defecto para compatibilidad con Firebase Firestore.
 */
@Entity(tableName = "libros")
data class LibroEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String = "",
    val autor: String = "",
    val isbn: String = "",
    val categoria: String = "",
    val editorial: String = "",
    val ejemplares: Int = 0,
    val disponible: Boolean = true
)
