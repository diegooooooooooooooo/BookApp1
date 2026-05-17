package com.example.bookapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
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

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "loan_reminders",
                "Recordatorios de Préstamos",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notificaciones para devoluciones próximas de libros"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}
