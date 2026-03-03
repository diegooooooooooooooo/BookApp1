package com.example.bookapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookapp.repository.BibliotecaRepository

class ViewModelFactory(private val repository: BibliotecaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(BibliotecaViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                BibliotecaViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
