package com.example.bookapp

import android.app.Application
import com.example.bookapp.data.database.AppDatabase
import com.example.bookapp.repository.BibliotecaRepository

class BookApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { 
        BibliotecaRepository(
            database.libroDao(), 
            database.usuarioDao(), 
            database.prestamoDao(), 
            database.socioDao()
        ).apply {
            startRealtimeSync()
        }
    }
}
