package com.example.bookapp.viewmodel

import androidx.lifecycle.*
import com.example.bookapp.data.entities.UsuarioEntity
import com.example.bookapp.repository.BibliotecaRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.FirebaseNetworkException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Locale

class LoginViewModel(private val repository: BibliotecaRepository) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _usuarioLogueado = MutableLiveData<UsuarioEntity?>()
    val usuarioLogueado: LiveData<UsuarioEntity?> = _usuarioLogueado

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val allUsuarios: LiveData<List<UsuarioEntity>> = repository.allUsuarios.asLiveData()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            _error.postValue(null)
            
            val cleanEmail = email.trim()
            val lowerEmail = cleanEmail.lowercase()

            // Bypass de credenciales solicitado para acceso rápido/emergencia
            if ((lowerEmail == "admin" || lowerEmail == "admin@bookapp.com") && password == "adminadmin") {
                try {
                    val existing = repository.getUsuarioByCorreo("admin@bookapp.com")
                    if (existing == null) {
                        val adminLocal = UsuarioEntity(nombre = "Administrador", correo = "admin@bookapp.com", contrasena = "adminadmin", rol = "ADMIN")
                        repository.insertUsuario(adminLocal)
                    }
                    val user = repository.getUsuarioByCorreo("admin@bookapp.com")
<<<<<<< HEAD
                    user?.let { repository.ensureLectorIsSocio(it) }
=======
>>>>>>> 0800574 (Versión más acutual)
                    _usuarioLogueado.postValue(user)
                } catch (e: Exception) {
                    _error.postValue("Error en bypass: ${e.message}")
                } finally {
                    _isLoading.postValue(false)
                }
                return@launch
            }
            
            if ((lowerEmail == "bibliotecario" || lowerEmail == "biblio@bookapp.com") && 
                (password == "bibliobiblio" || password == "Biblioblio")) {
                try {
                    val existing = repository.getUsuarioByCorreo("biblio@bookapp.com")
                    if (existing == null) {
                        val biblioLocal = UsuarioEntity(nombre = "Bibliotecario", correo = "biblio@bookapp.com", contrasena = "bibliobiblio", rol = "BIBLIOTECARIO")
                        repository.insertUsuario(biblioLocal)
                    }
                    val user = repository.getUsuarioByCorreo("biblio@bookapp.com")
<<<<<<< HEAD
                    user?.let { repository.ensureLectorIsSocio(it) }
=======
>>>>>>> 0800574 (Versión más acutual)
                    _usuarioLogueado.postValue(user)
                } catch (e: Exception) {
                    _error.postValue("Error en bypass: ${e.message}")
                } finally {
                    _isLoading.postValue(false)
                }
                return@launch
            }

            try {
                // Sincronización silenciosa (fallará si Firestore está deshabilitado)
                try {
                    repository.syncUsuariosFromFirestore()
                } catch (e: Exception) {
                    // Ignorar errores de red/Firestore durante la sincronización inicial
                }

                // 1. Firebase Auth
                val result = auth.signInWithEmailAndPassword(cleanEmail, password).await()
                
                if (result.user != null) {
                    // 2. Local DB Check
                    var usuarioLocal = repository.getUsuarioByCorreo(email)
                    
                    if (usuarioLocal == null) {
                        // MODO EMERGENCIA: Si el usuario existe en Firebase pero no localmente (debido a fallo en Firestore),
                        // lo creamos localmente con un rol por defecto para permitir el ingreso.
                        val nombreSugerido = email.split("@")[0].replaceFirstChar { 
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() 
                        }
                        
                        // Determinamos un rol sugerido basado en el email para facilitar pruebas
                        val rolSugerido = when {
                            email.contains("admin", ignoreCase = true) -> "ADMIN"
                            email.contains("biblio", ignoreCase = true) -> "BIBLIOTECARIO"
                            else -> "LECTOR"
                        }

                        val nuevoUsuario = UsuarioEntity(
                            nombre = nombreSugerido,
                            correo = email,
                            contrasena = "********", // La contraseña real está en Firebase
                            rol = rolSugerido
                        )
                        repository.insertUsuario(nuevoUsuario)
                        usuarioLocal = repository.getUsuarioByCorreo(email)
                    }
                    
                    if (usuarioLocal != null) {
<<<<<<< HEAD
                        repository.ensureLectorIsSocio(usuarioLocal)
=======
>>>>>>> 0800574 (Versión más acutual)
                        _usuarioLogueado.postValue(usuarioLocal)
                    } else {
                        _error.postValue("Error crítico: No se pudo crear el perfil local.")
                    }
                }
            } catch (e: FirebaseAuthInvalidUserException) {
                _error.postValue("La cuenta no existe o ha sido deshabilitada.")
            } catch (e: FirebaseAuthInvalidCredentialsException) {
                _error.postValue("La contraseña es incorrecta o el correo está mal escrito.")
            } catch (e: FirebaseNetworkException) {
                _error.postValue("Error de conexión. Revisa tu internet.")
            } catch (e: Exception) {
                _error.postValue("Error de autenticación: ${e.message}")
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
                
                // 2. Guardar en Room y Firestore
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

    fun updateUsuario(usuario: UsuarioEntity) = viewModelScope.launch {
        repository.updateUsuario(usuario)
        _usuarioLogueado.postValue(usuario)
    }
}
