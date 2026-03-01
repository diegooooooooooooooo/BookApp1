package com.example.bookapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa a un usuario del sistema (Bibliotecario o Administrador).
 */
@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val correo: String,
    val rol: String // "ADMIN" o "BIBLIOTECARIO"
)
