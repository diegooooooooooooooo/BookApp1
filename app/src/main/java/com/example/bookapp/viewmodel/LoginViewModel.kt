package com.example.bookapp.viewmodel

import androidx.lifecycle.*
import com.example.bookapp.data.entities.UsuarioEntity
import com.example.bookapp.repository.BibliotecaRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Locale

class LoginViewModel(private val repository: BibliotecaRepository) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _usuarioLogueado = MutableLiveData<UsuarioEntity?>()
    val usuarioLogueado: LiveData<UsuarioEntity?> = _usuarioLogueado

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val allUsuarios: LiveData<List<UsuarioEntity>> = repository.allUsuarios.asLiveData()

    fun login(email: String, password: String, expectedRole: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                // 1. Firebase Auth
                val result = auth.signInWithEmailAndPassword(email, password).await()
                
                if (result.user != null) {
                    // 2. Local DB Check
                    var usuarioLocal = repository.getUsuarioByCorreo(email)
                    
                    if (usuarioLocal == null) {
                        val nombreCapitalizado = email.split("@")[0].replaceFirstChar { 
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() 
                        }
                        usuarioLocal = UsuarioEntity(
                            nombre = nombreCapitalizado,
                            correo = email,
                            contrasena = password,
                            rol = expectedRole // For manual added users we assign the role they chose
                        )
                        repository.insertUsuario(usuarioLocal)
                        usuarioLocal = repository.getUsuarioByCorreo(email)
                    }
                    
                    if (usuarioLocal != null) {
                        // STRICT ROLE CHECK
                        if (usuarioLocal.rol == expectedRole) {
                            _usuarioLogueado.postValue(usuarioLocal)
                        } else {
                            auth.signOut()
                            _error.postValue("Acceso denegado: Tu cuenta no tiene el rol de $expectedRole")
                            _usuarioLogueado.postValue(null)
                        }
                    }
                }
            } catch (e: Exception) {
                _error.postValue("Credenciales incorrectas o error de red")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun register(nombre: String, email: String, password: String, role: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                // 1. Crear en Firebase
                auth.createUserWithEmailAndPassword(email, password).await()
                
                // 2. Guardar en Room
                val nuevoUsuario = UsuarioEntity(
                    nombre = nombre,
                    correo = email,
                    contrasena = password,
                    rol = role
                )
                repository.insertUsuario(nuevoUsuario)
                
                _usuarioLogueado.postValue(nuevoUsuario)
            } catch (e: Exception) {
                _error.postValue("Error al registrar: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun logout() {
        auth.signOut()
        _usuarioLogueado.value = null
    }
}
