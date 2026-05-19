package com.example.bookapp.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.bookapp.BookApplication
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

class LoanNotificationWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val prefs = applicationContext.getSharedPreferences("bookapp_prefs", Context.MODE_PRIVATE)
        val isEnabled = prefs.getBoolean("pref_notif_enabled", false)
        if (!isEnabled) return Result.success()

        val bufferDays = prefs.getInt("pref_notif_days", 3)
        val bufferMillis = TimeUnit.DAYS.toMillis(bufferDays.toLong())
        val currentTime = System.currentTimeMillis()
        val targetTime = currentTime + bufferMillis

        val repository = (applicationContext as BookApplication).repository
        
        // Get user email to filter notifications (optional, but good practice)
        // Here we'll just check all active loans in the local DB for simplicity 
        // as the app is currently local-first with sync.
        val activeLoans = repository.prestamosActivosConDetalles.first()

        activeLoans.forEach { loan ->
            // If loan is not returned and deadline is within buffer
            if (loan.fechaEntregaReal == null && 
                loan.fechaDevolucionEsperada > currentTime && 
                loan.fechaDevolucionEsperada <= targetTime) {
                
                showNotification(loan.id, loan.libroTitulo, loan.fechaDevolucionEsperada)
            }
        }

        return Result.success()
    }

    private fun showNotification(loanId: Int, bookTitle: String, deadline: Long) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "loan_reminders"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Recordatorios de Préstamos",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val dateFormat = android.text.format.DateFormat.getDateFormat(applicationContext)
        val dateStr = dateFormat.format(deadline)

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Próxima Devolución")
            .setContentText("El libro '$bookTitle' vence el $dateStr")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(loanId, notification)
    }
}
