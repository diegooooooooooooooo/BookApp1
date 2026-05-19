package com.example.bookapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentLoginBinding
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

import com.example.bookapp.data.model.UserRole

/**
 * Pantalla de inicio de sesión.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
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

            // Limpiar errores previos
            binding.tilEmail.error = null
            binding.tilPassword.error = null

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
            } else {
                if (email.isEmpty()) binding.tilEmail.error = "Ingresa tu correo"
                if (password.isEmpty()) binding.tilPassword.error = "Ingresa tu contraseña"
                Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGoToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        // Observar carga
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.btnLogin.isEnabled = !isLoading
            binding.btnGoToRegister.isEnabled = !isLoading
        }

        // Observar cambios en el usuario logueado
        viewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                when (it.rol) {
                    UserRole.ADMIN -> findNavController().navigate(R.id.action_loginFragment_to_dashboardAdminFragment)
                    UserRole.BIBLIOTECARIO -> findNavController().navigate(R.id.action_loginFragment_to_dashboardBibliotecarioFragment)
                    UserRole.LECTOR, UserRole.USUARIO -> findNavController().navigate(R.id.action_loginFragment_to_dashboardLectorFragment)
                    else -> Toast.makeText(context, "Rol no reconocido: ${it.rol}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            errorMsg?.let {
                when {
                    it.contains("cuenta", ignoreCase = true) -> binding.tilEmail.error = it
                    it.contains("contraseña", ignoreCase = true) -> binding.tilPassword.error = it
                    else -> Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
