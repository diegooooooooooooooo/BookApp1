package com.example.bookapp.ui.configuracion

import android.content.Context
import android.content.pm.PackageManager
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import android.graphics.Bitmap
=======
>>>>>>> 0800574 (Versión más acutual)
=======
import android.graphics.Bitmap
>>>>>>> experimental
=======
import android.graphics.Bitmap
>>>>>>> 7ae96b5 (Versión más acutual)
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.work.*
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.data.model.UserRole
import com.example.bookapp.databinding.FragmentConfiguracionBinding
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import com.example.bookapp.worker.LoanNotificationWorker
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.io.File
import java.io.FileOutputStream
=======
>>>>>>> 0800574 (Versión más acutual)
=======
import java.io.File
import java.io.FileOutputStream
>>>>>>> experimental
=======
import java.io.File
import java.io.FileOutputStream
>>>>>>> 7ae96b5 (Versión más acutual)
import java.util.concurrent.TimeUnit

class ConfiguracionFragment : Fragment() {

    private var _binding: FragmentConfiguracionBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    private val sharedPrefs by lazy {
        requireContext().getSharedPreferences("bookapp_prefs", Context.MODE_PRIVATE)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(requireContext(), "Permisos de notificación concedidos", Toast.LENGTH_SHORT).show()
        } else {
            binding.switchNotificaciones.isChecked = false
            Toast.makeText(requireContext(), "Se requieren permisos para los recordatorios", Toast.LENGTH_LONG).show()
        }
    }

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            val usuarioActual = loginViewModel.usuarioLogueado.value
            if (usuarioActual != null) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
                try {
                    val fileName = "id_card_${usuarioActual.id}.jpg"
                    val file = File(requireContext().filesDir, fileName)
                    val out = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                    out.flush()
                    out.close()

                    val updatedUser = usuarioActual.copy(fotoId = file.absolutePath)
                    loginViewModel.updateUsuario(updatedUser)
                    Toast.makeText(requireContext(), "ID Guardada correctamente", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Error al guardar imagen: ${e.message}", Toast.LENGTH_SHORT).show()
                }
<<<<<<< HEAD
<<<<<<< HEAD
=======
                // En una app real, guardaríamos el bitmap en un archivo y guardaríamos la ruta
                // Aquí simulamos guardando una marca de tiempo como ID de foto
                val updatedUser = usuarioActual.copy(fotoId = "ID_SCANNED_${System.currentTimeMillis()}")
                loginViewModel.updateUsuario(updatedUser)
                Toast.makeText(requireContext(), "ID Guardada correctamente", Toast.LENGTH_SHORT).show()
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfiguracionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSettingsUI()

        loginViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            if (usuario?.rol == UserRole.LECTOR) {
                binding.cardScanId.visibility = View.VISIBLE
                binding.cardMiPerfil.visibility = View.VISIBLE
                binding.cardNotifications.visibility = View.VISIBLE
                if (usuario.fotoId != null) {
                    binding.tvScanIdStatus.text = "ID Escaneada (Actualizar)"
                }
            } else {
                binding.cardScanId.visibility = View.GONE
                binding.cardMiPerfil.visibility = View.GONE
                binding.cardNotifications.visibility = View.GONE
            }
        }

        binding.btnScanId.setOnClickListener {
            takePictureLauncher.launch(null)
        }

        binding.btnMiPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_configuracionFragment_to_miPerfilFragment)
        }

        binding.btnCerrarSesion.setOnClickListener {
            loginViewModel.logout()
            findNavController().navigate(R.id.action_configuracionFragment_to_loginFragment)
        }
    }

    private fun setupSettingsUI() {
        // Cargar preferencias
        val isEnabled = sharedPrefs.getBoolean("pref_notif_enabled", false)
        val bufferDays = sharedPrefs.getInt("pref_notif_days", 3)

        binding.switchNotificaciones.isChecked = isEnabled
        binding.sliderDias.value = bufferDays.toFloat()
        binding.tvDiasNotifLabel.text = "Notificar $bufferDays días antes"
        binding.layoutNotificationDetails.visibility = if (isEnabled) View.VISIBLE else View.GONE

        binding.switchNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }
            
            sharedPrefs.edit().putBoolean("pref_notif_enabled", isChecked).apply()
            binding.layoutNotificationDetails.visibility = if (isChecked) View.VISIBLE else View.GONE
            
            if (isChecked) scheduleNotificationWorker() else cancelNotificationWorker()
        }

        binding.sliderDias.addOnChangeListener { _, value, _ ->
            val days = value.toInt()
            binding.tvDiasNotifLabel.text = "Notificar $days días antes"
            sharedPrefs.edit().putInt("pref_notif_days", days).apply()
            if (binding.switchNotificaciones.isChecked) scheduleNotificationWorker()
        }

        binding.btnCheckNow.setOnClickListener {
            val checkRequest = OneTimeWorkRequestBuilder<LoanNotificationWorker>().build()
            WorkManager.getInstance(requireContext()).enqueue(checkRequest)
            Toast.makeText(requireContext(), "Verificando préstamos...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun scheduleNotificationWorker() {
        val periodicRequest = PeriodicWorkRequestBuilder<LoanNotificationWorker>(24, TimeUnit.HOURS)
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.NOT_REQUIRED).build())
            .build()

        WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork(
            "loan_notification_work",
            ExistingPeriodicWorkPolicy.UPDATE,
            periodicRequest
        )
    }

    private fun cancelNotificationWorker() {
        WorkManager.getInstance(requireContext()).cancelUniqueWork("loan_notification_work")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
