package com.example.bookapp.data.dao

import androidx.room.*
import com.example.bookapp.data.entities.LibroEntity
import kotlinx.coroutines.flow.Flow

/**
 * Acceso a datos para la tabla de libros.
 */
@Dao
interface LibroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libro: LibroEntity): Long

    @Update
    suspend fun update(libro: LibroEntity)

    @Delete
    suspend fun delete(libro: LibroEntity)

    @Query("SELECT * FROM libros")
    fun getAllLibros(): Flow<List<LibroEntity>>

    @Query("SELECT * FROM libros WHERE id = :id")
    suspend fun getLibroById(id: Int): LibroEntity?

    @Query("SELECT * FROM libros WHERE titulo LIKE '%' || :query || '%' OR autor LIKE '%' || :query || '%'")
    fun searchLibros(query: String): Flow<List<LibroEntity>>
}
