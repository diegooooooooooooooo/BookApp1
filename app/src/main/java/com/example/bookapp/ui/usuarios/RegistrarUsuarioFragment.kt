package com.example.bookapp.ui.usuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.BookApplication
import com.example.bookapp.databinding.FragmentRegistrarUsuarioBinding
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

import com.example.bookapp.data.model.UserRole

/**
 * Fragmento para que el Administrador registre nuevos usuarios (Admin o Bibliotecario).
 */
class RegistrarUsuarioFragment : Fragment() {

    private var _binding: FragmentRegistrarUsuarioBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrarUsuarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegistrar.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val role = if (binding.rbAdmin.isChecked) UserRole.ADMIN else UserRole.BIBLIOTECARIO

            if (nombre.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.register(nombre, email, password, role)
            } else {
                Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            if (usuario != null) {
                Toast.makeText(context, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
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
