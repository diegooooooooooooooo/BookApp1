package com.example.bookapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.BookApplication
import com.example.bookapp.databinding.FragmentSignUpBinding
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

import com.example.bookapp.data.model.UserRole

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSignUp.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val role = when {
                binding.rbAdmin.isChecked -> UserRole.ADMIN
                binding.rbBibliotecario.isChecked -> UserRole.BIBLIOTECARIO
                else -> UserRole.LECTOR
            }

            if (nombre.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.register(nombre, email, password, role)
            } else {
                Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            if (usuario != null) {
                Toast.makeText(context, "¡Éxito! Tu cuenta ha sido creada correctamente", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            errorMsg?.let {
                val mensaje = when {
                    it.contains("email address is already in use", ignoreCase = true) -> "Este correo ya está registrado"
                    it.contains("password", ignoreCase = true) -> "La contraseña es muy débil o inválida"
                    it.contains("network", ignoreCase = true) -> "Error de conexión. Revisa tu internet"
                    else -> "Error: $it"
                }
                Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.btnSignUp.isEnabled = !isLoading
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
