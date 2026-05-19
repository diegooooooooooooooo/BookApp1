package com.example.bookapp.repository

import com.example.bookapp.data.dao.SocioDao
import com.example.bookapp.data.entities.SocioEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull

class SocioRepository(private val socioDao: SocioDao) {
    private val firestore = FirebaseFirestore.getInstance()
    private val sociosCollection = firestore.collection("socios")

    val allSocios: Flow<List<SocioEntity>> = socioDao.getAllSocios()

    suspend fun insertSocio(socio: SocioEntity) {
        val id = socioDao.insert(socio).toInt()
        val socioConId = socio.copy(id = id)
        try {
            withTimeoutOrNull(3000) {
                sociosCollection.document(id.toString()).set(socioConId).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
