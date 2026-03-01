package com.example.bookapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.entities.UsuarioEntity
import com.example.bookapp.repository.BibliotecaRepository
import kotlinx.coroutines.launch

/**
 * ViewModel para gestionar la lógica de autenticación y sesión de usuario.
 */
class LoginViewModel(private val repository: BibliotecaRepository) : ViewModel() {

    private val _usuarioLogueado = MutableLiveData<UsuarioEntity?>()
    val usuarioLogueado: LiveData<UsuarioEntity?> = _usuarioLogueado

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    /**
     * Simula el inicio de sesión y obtiene el rol del usuario.
     */
    fun login(email: String, role: String) {
        viewModelScope.launch {
            try {
                var usuario = repository.getUsuarioByCorreo(email)
                if (usuario == null) {
                    // Para propósitos de la demo, creamos el usuario si no existe
                    usuario = UsuarioEntity(nombre = "Usuario Demo", correo = email, rol = role)
                    repository.insertUsuario(usuario)
                    usuario = repository.getUsuarioByCorreo(email)
                }
                _usuarioLogueado.postValue(usuario)
            } catch (e: Exception) {
                _error.postValue("Error al iniciar sesión: ${e.message}")
            }
        }
    }

    fun logout() {
        _usuarioLogueado.value = null
    }
}
