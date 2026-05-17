package com.example.bookapp.ui.libros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.data.entities.LibroEstado
import com.example.bookapp.databinding.FragmentDetalleLibroBinding
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import java.util.*

import androidx.navigation.fragment.navArgs

/**
 * Fragmento para mostrar la información detallada de un libro.
 */
class LibroDetalleFragment : Fragment() {

    private var _binding: FragmentDetalleLibroBinding? = null
    private val binding get() = _binding!!
    private val args: LibroDetalleFragmentArgs by navArgs()

    private val viewModel: BibliotecaViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleLibroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val libroId = args.libroId
        if (libroId != -1) {
            viewModel.allLibrosCombined.observe(viewLifecycleOwner) { libros ->
                val libro = libros.find { it.id == libroId }
                libro?.let {
                    binding.tvDetalleTitulo.text = it.titulo
                    binding.tvDetalleAutor.text = it.autor
                    binding.tvDetalleIsbn.text = it.isbn
                    binding.tvDetalleCategoria.text = it.categoria
                    binding.tvDetalleValor.text = String.format(Locale.getDefault(), "$%.2f", it.valor)
                    
                    // Cargar portada con Coil
                    binding.ivDetallePortada.load(it.portadaUrl) {
                        crossfade(true)
                        placeholder(R.drawable.ic_book)
                        error(R.drawable.ic_book)
                    }

                    when (it.estado) {
                        LibroEstado.DISPONIBLE -> {
                            binding.chipDetalleEstado.text = "Disponible"
                            binding.chipDetalleEstado.setChipBackgroundColorResource(android.R.color.holo_green_light)
                            binding.btnPrestarLibro.isEnabled = true
                        }
                        LibroEstado.PRESTADO -> {
                            binding.chipDetalleEstado.text = "Prestado"
                            binding.chipDetalleEstado.setChipBackgroundColorResource(android.R.color.holo_red_light)
                            binding.btnPrestarLibro.isEnabled = false
                        }
                        LibroEstado.NO_DISPONIBLE -> {
                            binding.chipDetalleEstado.text = "No Disponible"
                            binding.chipDetalleEstado.setChipBackgroundColorResource(android.R.color.darker_gray)
                            binding.btnPrestarLibro.isEnabled = false
                        }
                    }
                }
            }
        }

        binding.btnPrestarLibro.setOnClickListener {
            val action = LibroDetalleFragmentDirections.actionDetalleLibroFragmentToRegistrarPrestamoFragment(libroId)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
