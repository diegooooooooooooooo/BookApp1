package com.example.bookapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

    // Inicialización del ViewModel con una Factoría para inyectar el repositorio
    private val viewModel: LoginViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        ViewModelFactory(BibliotecaRepository(database.libroDao(), database.usuarioDao(), database.prestamoDao()))
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
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val role = if (binding.rbAdmin.isChecked) "ADMIN" else "BIBLIOTECARIO"

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, role)
            } else {
                Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Observar cambios en el usuario logueado para navegar al dashboard correcto
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
