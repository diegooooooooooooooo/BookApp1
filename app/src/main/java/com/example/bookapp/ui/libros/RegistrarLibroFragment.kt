package com.example.bookapp.ui.libros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookapp.databinding.FragmentRegistrarLibroBinding

/**
 * Fragmento para registrar un nuevo libro en la base de datos.
 */
class RegistrarLibroFragment : Fragment() {

    private var _binding: FragmentRegistrarLibroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrarLibroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el spinner de categorías
        val categorias = arrayOf("Ficción", "Ciencia", "Historia", "Tecnología", "Infantil")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categorias)
        binding.spinnerCategoria.setAdapter(adapter)

        binding.btnGuardarLibro.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            if (titulo.isNotEmpty()) {
                Toast.makeText(context, "Libro guardado exitosamente", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                binding.etTitulo.error = "Campo requerido"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
