package com.example.bookapp.ui.libros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapp.R
import com.example.bookapp.data.database.AppDatabase
import com.example.bookapp.databinding.FragmentCatalogoLibrosBinding
import com.example.bookapp.repository.BibliotecaRepository
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

class CatalogoLibrosFragment : Fragment() {

    private var _binding: FragmentCatalogoLibrosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BibliotecaViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        ViewModelFactory(BibliotecaRepository(database.libroDao(), database.usuarioDao(), database.prestamoDao(), database.socioDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogoLibrosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LibroAdapter { libro ->
            // Navegar al detalle si es necesario
        }

        binding.rvLibros.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLibros.adapter = adapter

        binding.fabAddLibro.setOnClickListener {
            findNavController().navigate(R.id.registrarLibroFragment)
        }
        
        viewModel.allLibros.observe(viewLifecycleOwner) { libros ->
            adapter.submitList(libros)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
