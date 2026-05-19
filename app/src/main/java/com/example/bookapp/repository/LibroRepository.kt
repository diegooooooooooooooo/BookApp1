package com.example.bookapp.repository

import com.example.bookapp.data.dao.LibroDao
import com.example.bookapp.data.entities.LibroEntity
import com.example.bookapp.data.entities.LibroEstado
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull

class LibroRepository(private val libroDao: LibroDao) {
    private val firestore = FirebaseFirestore.getInstance()
    private val librosCollection = firestore.collection("libros")

    val allLibros: Flow<List<LibroEntity>> = libroDao.getAllLibros()

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
    suspend fun insertLibro(libro: LibroEntity): Int {
        val libroAjustado = adjustLibroStatus(libro)
        val id = libroDao.insert(libroAjustado).toInt()
        syncLibroToFirestore(libroAjustado.copy(id = id))
        return id
<<<<<<< HEAD
=======
    suspend fun insertLibro(libro: LibroEntity) {
        val libroAjustado = adjustLibroStatus(libro)
        val id = libroDao.insert(libroAjustado).toInt()
        syncLibroToFirestore(libroAjustado.copy(id = id))
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
    suspend fun insertLibro(libro: LibroEntity): Int {
        val libroAjustado = adjustLibroStatus(libro)
        val id = libroDao.insert(libroAjustado).toInt()
        syncLibroToFirestore(libroAjustado.copy(id = id))
        return id
>>>>>>> 7ae96b5 (Versión más acutual)
    }

    suspend fun updateLibro(libro: LibroEntity) {
        val libroAjustado = adjustLibroStatus(libro)
        libroDao.update(libroAjustado)
        syncLibroToFirestore(libroAjustado)
    }

    suspend fun deleteLibro(libro: LibroEntity) {
        libroDao.delete(libro)
        try {
            withTimeoutOrNull(3000) {
                librosCollection.document(libro.id.toString()).delete().await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun adjustLibroStatus(libro: LibroEntity): LibroEntity {
        return if (libro.ejemplares <= 0) {
            libro.copy(estado = LibroEstado.NO_DISPONIBLE)
        } else if (libro.estado == LibroEstado.NO_DISPONIBLE) {
            libro.copy(estado = LibroEstado.DISPONIBLE)
        } else {
            libro
        }
    }

    private suspend fun syncLibroToFirestore(libro: LibroEntity) {
        try {
            withTimeoutOrNull(3000) {
                librosCollection.document(libro.id.toString()).set(libro).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getLibroById(id: Int) = libroDao.getLibroById(id)
}
