package com.example.bookapp.data.dao

import androidx.room.*
import com.example.bookapp.data.entities.SocioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SocioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(socio: SocioEntity): Long

    @Update
    suspend fun update(socio: SocioEntity)

    @Delete
    suspend fun delete(socio: SocioEntity)

    @Query("SELECT * FROM socios")
    fun getAllSocios(): Flow<List<SocioEntity>>

    @Query("SELECT * FROM socios WHERE id = :id")
    suspend fun getSocioById(id: Int): SocioEntity?

    @Query("SELECT * FROM socios WHERE LOWER(correo) = LOWER(:correo) LIMIT 1")
    suspend fun getSocioByCorreo(correo: String): SocioEntity?
}
