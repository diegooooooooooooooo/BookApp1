package com.example.bookapp.ui.prestamos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.data.model.UserRole
import com.example.bookapp.databinding.FragmentConfirmacionPrestamoBinding
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
<<<<<<< HEAD
=======
=======
import androidx.fragment.app.activityViewModels
>>>>>>> 7ae96b5 (Versión más acutual)
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.data.model.UserRole
import com.example.bookapp.databinding.FragmentConfirmacionPrestamoBinding
<<<<<<< HEAD
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
>>>>>>> 7ae96b5 (Versión más acutual)

/**
 * Pantalla de ticket digital.
 * Muestra el resumen del préstamo recién registrado.
 */
class ConfirmacionPrestamoFragment : Fragment() {

    private var _binding: FragmentConfirmacionPrestamoBinding? = null
    private val binding get() = _binding!!
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
    private val args: ConfirmacionPrestamoFragmentArgs by navArgs()

    private val loginViewModel: LoginViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmacionPrestamoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
        // Display loan details from args
        binding.tvTicketUsuario.text = args.socioNombre
        binding.tvTicketLibro.text = args.libroTitulo
        
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        binding.tvTicketFechaLimite.text = sdf.format(Date(args.fechaLimite))

<<<<<<< HEAD
        binding.btnFinalizar.setOnClickListener {
            val usuario = loginViewModel.usuarioLogueado.value
            when (usuario?.rol) {
                UserRole.ADMIN -> findNavController().navigate(R.id.action_confirmacionPrestamoFragment_to_dashboardAdminFragment)
                UserRole.BIBLIOTECARIO -> findNavController().navigate(R.id.action_confirmacionPrestamoFragment_to_dashboardBibliotecarioFragment)
                UserRole.LECTOR, UserRole.USUARIO -> findNavController().navigate(R.id.action_confirmacionPrestamoFragment_to_dashboardLectorFragment)
                else -> findNavController().navigate(R.id.action_confirmacionPrestamoFragment_to_dashboardLectorFragment)
            }
<<<<<<< HEAD
=======
        binding.btnFinalizar.setOnClickListener {
            // Volver al dashboard del bibliotecario
            findNavController().navigate(R.id.dashboardBibliotecarioFragment)
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
        binding.btnFinalizar.setOnClickListener {
            val usuario = loginViewModel.usuarioLogueado.value
            when (usuario?.rol) {
                UserRole.ADMIN -> findNavController().navigate(R.id.action_confirmacionPrestamoFragment_to_dashboardAdminFragment)
                UserRole.BIBLIOTECARIO -> findNavController().navigate(R.id.action_confirmacionPrestamoFragment_to_dashboardBibliotecarioFragment)
                UserRole.LECTOR, UserRole.USUARIO -> findNavController().navigate(R.id.action_confirmacionPrestamoFragment_to_dashboardLectorFragment)
                else -> findNavController().navigate(R.id.action_confirmacionPrestamoFragment_to_dashboardLectorFragment)
            }
>>>>>>> 7ae96b5 (Versión más acutual)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
