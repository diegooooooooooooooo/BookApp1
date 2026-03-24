package com.example.bookapp.data.dao

import androidx.room.*
import com.example.bookapp.data.entities.UsuarioEntity
import kotlinx.coroutines.flow.Flow

/**
 * Acceso a datos para la tabla de usuarios.
 */
@Dao
interface UsuarioDao {
    @Insert
    suspend fun insert(usuario: UsuarioEntity): Long

    @Update
    suspend fun update(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuarios")
    fun getAllUsuarios(): Flow<List<UsuarioEntity>>

    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    suspend fun getUsuarioByCorreo(correo: String): UsuarioEntity?

    @Query("SELECT * FROM usuarios WHERE id = :id")
    suspend fun getUsuarioById(id: Int): UsuarioEntity?
}
