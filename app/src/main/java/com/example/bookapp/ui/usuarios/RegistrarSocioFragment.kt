package com.example.bookapp.ui.usuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.data.database.AppDatabase
import com.example.bookapp.data.entities.SocioEntity
import com.example.bookapp.databinding.FragmentRegistrarSocioBinding
import com.example.bookapp.repository.BibliotecaRepository
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

class RegistrarSocioFragment : Fragment() {

    private var _binding: FragmentRegistrarSocioBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BibliotecaViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        ViewModelFactory(BibliotecaRepository(database.libroDao(), database.usuarioDao(), database.prestamoDao(), database.socioDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrarSocioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGuardarSocio.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            val dni = binding.etDni.text.toString().trim()
            val telefono = binding.etTelefono.text.toString().trim()

            if (nombre.isNotEmpty() && dni.isNotEmpty()) {
                val socio = SocioEntity(
                    nombre = nombre,
                    dni = dni,
                    telefono = telefono,
                    correo = "" // Opcional
                )
                viewModel.insertSocio(socio)
                Toast.makeText(context, "Lector registrado exitosamente", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Completa los campos obligatorios", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
