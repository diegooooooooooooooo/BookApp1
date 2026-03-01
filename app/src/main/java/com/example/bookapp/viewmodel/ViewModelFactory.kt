package com.example.bookapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookapp.repository.BibliotecaRepository

/**
 * Factoría para crear instancias de ViewModels con sus respectivos repositorios.
 */
class ViewModelFactory(private val repository: BibliotecaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        // Se agregarán más ViewModels aquí conforme se necesiten
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
