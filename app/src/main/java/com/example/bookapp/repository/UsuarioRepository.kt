package com.example.bookapp.repository

import com.example.bookapp.data.dao.UsuarioDao
import com.example.bookapp.data.dao.SocioDao
import com.example.bookapp.data.entities.UsuarioEntity
import com.example.bookapp.data.entities.SocioEntity
import com.example.bookapp.data.model.UserRole
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull

class UsuarioRepository(
    private val usuarioDao: UsuarioDao,
    private val socioDao: SocioDao
) {
    private val firestore = FirebaseFirestore.getInstance()
    private val usuariosCollection = firestore.collection("usuarios")

    val allUsuarios: Flow<List<UsuarioEntity>> = usuarioDao.getAllUsuarios()

    suspend fun insertUsuario(usuario: UsuarioEntity) {
        val id = usuarioDao.insert(usuario).toInt()
        val usuarioConId = usuario.copy(id = id)
        ensureLectorIsSocio(usuarioConId)
        syncUsuarioToFirestore(usuarioConId)
    }

    suspend fun updateUsuario(usuario: UsuarioEntity) {
        usuarioDao.update(usuario)
        syncUsuarioToFirestore(usuario)
    }

    private suspend fun syncUsuarioToFirestore(usuario: UsuarioEntity) {
        try {
            withTimeoutOrNull(3000) {
                usuariosCollection.document(usuario.id.toString()).set(usuario).await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getUsuarioByCorreo(correo: String) = usuarioDao.getUsuarioByCorreo(correo)

    suspend fun ensureLectorIsSocio(usuario: UsuarioEntity) {
        if (usuario.rol == UserRole.LECTOR || usuario.rol == UserRole.USUARIO) {
            val existingSocio = socioDao.getSocioByCorreo(usuario.correo)
            if (existingSocio == null) {
                socioDao.insert(SocioEntity(nombre = usuario.nombre, correo = usuario.correo))
            } else if (existingSocio.nombre != usuario.nombre) {
                socioDao.update(existingSocio.copy(nombre = usuario.nombre))
            }
        }
    }

    suspend fun syncUsuariosFromFirestore() {
        try {
            val snapshot = withTimeoutOrNull(5000) {
                usuariosCollection.get().await()
            }
            val usuarios = snapshot?.toObjects(UsuarioEntity::class.java)
            usuarios?.forEach { usuario ->
                val local = usuarioDao.getUsuarioByCorreo(usuario.correo)
                if (local == null) {
                    usuarioDao.insert(usuario)
                    ensureLectorIsSocio(usuario)
                } else {
                    val updated = usuario.copy(id = local.id)
                    usuarioDao.update(updated)
                    ensureLectorIsSocio(updated)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
