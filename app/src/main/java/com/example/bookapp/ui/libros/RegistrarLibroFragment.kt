package com.example.bookapp.ui.libros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.BookApplication
import com.example.bookapp.data.entities.LibroEntity
import com.example.bookapp.databinding.FragmentRegistrarLibroBinding
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

import androidx.navigation.fragment.navArgs

class RegistrarLibroFragment : Fragment() {

    private var _binding: FragmentRegistrarLibroBinding? = null
    private val binding get() = _binding!!
    private val args: RegistrarLibroFragmentArgs by navArgs()

    // Usar activityViewModels para compartir el estado
    private val viewModel: BibliotecaViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    private var libroAEditar: LibroEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrarLibroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recuperar argumentos si existen (para edición) - Safe Args
        val libroId = args.libroId
        if (libroId != -1) {
            setupEditMode(libroId)
        }

        binding.btnGuardarLibro.setOnClickListener {
            val titulo = binding.etTitulo.text.toString().trim()
            val autor = binding.etAutor.text.toString().trim()
            val editorial = binding.etEditorial.text.toString().trim()
            val isbn = binding.etIsbn.text.toString().trim()
            val ejemplaresStr = binding.etEjemplares.text.toString().trim()
            val valorStr = binding.etValor.text.toString().trim()

            if (titulo.isNotEmpty() && autor.isNotEmpty() && ejemplaresStr.isNotEmpty()) {
                val libro = libroAEditar?.copy(
                    titulo = titulo,
                    autor = autor,
                    editorial = editorial,
                    isbn = isbn,
                    ejemplares = ejemplaresStr.toIntOrNull() ?: 1,
                    valor = valorStr.toDoubleOrNull() ?: 0.0
                ) ?: LibroEntity(
                    titulo = titulo,
                    autor = autor,
                    editorial = editorial,
                    isbn = isbn,
                    categoria = "",
                    ejemplares = ejemplaresStr.toIntOrNull() ?: 1,
                    valor = valorStr.toDoubleOrNull() ?: 0.0
                )

                if (libroAEditar != null) {
                    viewModel.updateLibro(libro)
                    Toast.makeText(context, "Libro actualizado exitosamente", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.insertLibro(libro)
                    Toast.makeText(context, "Libro guardado exitosamente", Toast.LENGTH_SHORT).show()
                }
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Por favor, completa los campos requeridos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupEditMode(libroId: Int) {
        // Observamos todos los libros y buscamos el que necesitamos editar
        viewModel.allLibros.observe(viewLifecycleOwner) { libros ->
            val libro = libros.find { it.id == libroId }
            if (libro != null && libroAEditar == null) {
                libroAEditar = libro
                binding.etTitulo.setText(libro.titulo)
                binding.etAutor.setText(libro.autor)
                binding.etEditorial.setText(libro.editorial)
                binding.etIsbn.setText(libro.isbn)
                binding.etEjemplares.setText(libro.ejemplares.toString())
                binding.etValor.setText(libro.valor.toString())
                binding.btnGuardarLibro.text = "Actualizar Libro"
                // Opcional: cambiar el título del fragmento
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
