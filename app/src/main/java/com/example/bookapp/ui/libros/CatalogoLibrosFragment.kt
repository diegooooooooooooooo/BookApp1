package com.example.bookapp.ui.libros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapp.R
import com.example.bookapp.data.entities.LibroEntity
import com.example.bookapp.databinding.FragmentCatalogoLibrosBinding
import com.example.bookapp.repository.BibliotecaRepository
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import com.example.bookapp.data.database.AppDatabase

class CatalogoLibrosFragment : Fragment() {

    private var _binding: FragmentCatalogoLibrosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BibliotecaViewModel by activityViewModels {
        val database = AppDatabase.getDatabase(requireContext())
        ViewModelFactory(BibliotecaRepository(database.libroDao(), database.usuarioDao(), database.prestamoDao(), database.socioDao()))
    }

    private val loginViewModel: LoginViewModel by activityViewModels {
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

        val adapter = LibroAdapter(
            onLibroClick = { libro ->
                // Navegar al detalle si es necesario
            },
            onEditClick = { libro ->
                val bundle = Bundle().apply {
                    putInt("libroId", libro.id)
                }
                findNavController().navigate(R.id.registrarLibroFragment, bundle)
            },
            onDeleteClick = { libro ->
                showDeleteConfirmation(libro)
            }
        )

        binding.rvLibros.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLibros.adapter = adapter

        binding.fabAddLibro.setOnClickListener {
            findNavController().navigate(R.id.registrarLibroFragment)
        }
        
        viewModel.allLibros.observe(viewLifecycleOwner) { libros ->
            adapter.submitList(libros)
        }

        loginViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            if (usuario?.rol == "ADMIN") {
                binding.fabAddLibro.visibility = View.VISIBLE
                adapter.setAdminMode(true)
            } else {
                binding.fabAddLibro.visibility = View.GONE
                adapter.setAdminMode(false)
            }
        }
    }

    private fun showDeleteConfirmation(libro: LibroEntity) {
        AlertDialog.Builder(requireContext())
            .setTitle("Eliminar Libro")
            .setMessage("¿Estás seguro de que deseas eliminar '${libro.titulo}'? Esta acción no se puede deshacer.")
            .setPositiveButton("Eliminar") { _, _ ->
                viewModel.deleteLibro(libro)
                Toast.makeText(context, "Libro eliminado", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
