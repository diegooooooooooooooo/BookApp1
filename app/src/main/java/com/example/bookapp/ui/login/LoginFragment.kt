package com.example.bookapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.R
import com.example.bookapp.data.database.AppDatabase
import com.example.bookapp.databinding.FragmentLoginBinding
import com.example.bookapp.repository.BibliotecaRepository
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

/**
 * Pantalla de inicio de sesión. Permite al usuario elegir su rol.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by activityViewModels {
        val database = AppDatabase.getDatabase(requireContext())
        ViewModelFactory(BibliotecaRepository(
            database.libroDao(), 
            database.usuarioDao(), 
            database.prestamoDao(),
            database.socioDao()
        ))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val role = if (binding.rbAdmin.isChecked) "ADMIN" else "BIBLIOTECARIO"

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password, role)
            } else {
                Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Observar carga
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.btnLogin.isEnabled = !isLoading
            // Podrías mostrar un ProgressBar aquí
        }

        // Observar cambios en el usuario logueado
        viewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                if (it.rol == "ADMIN") {
                    findNavController().navigate(R.id.action_loginFragment_to_dashboardAdminFragment)
                } else {
                    findNavController().navigate(R.id.action_loginFragment_to_dashboardBibliotecarioFragment)
                }
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            errorMsg?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
