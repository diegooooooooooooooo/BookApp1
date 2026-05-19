package com.example.bookapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
<<<<<<< HEAD
=======
import androidx.core.view.isVisible
>>>>>>> 7ae96b5 (Versión más acutual)
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentDashboardBibliotecarioBinding
<<<<<<< HEAD
=======
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentDashboardBibliotecarioBinding
import com.example.bookapp.ui.reportes.TopLibrosAdapter
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
>>>>>>> experimental
=======
import com.example.bookapp.ui.reportes.TopLibrosAdapter
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
>>>>>>> 7ae96b5 (Versión más acutual)

/**
 * Pantalla principal para el rol de Bibliotecario.
 * Contiene accesos rápidos a préstamos, devoluciones y búsqueda.
 */
class DashboardBibliotecarioFragment : Fragment() {

    private var _binding: FragmentDashboardBibliotecarioBinding? = null
    private val binding get() = _binding!!

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 7ae96b5 (Versión más acutual)
    private val viewModel: BibliotecaViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

<<<<<<< HEAD
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBibliotecarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
<<<<<<< HEAD
<<<<<<< HEAD
=======
        setupRecyclerViews()

>>>>>>> experimental
=======
        setupRecyclerViews()

>>>>>>> 7ae96b5 (Versión más acutual)
        // Configurar botones de acciones rápidas
        binding.cardPrestamo.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardBibliotecarioFragment_to_registrarPrestamoFragment)
        }

        binding.cardDevolucion.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardBibliotecarioFragment_to_registrarDevolucionFragment)
        }

        binding.cardCatalogo.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardBibliotecarioFragment_to_catalogoLibrosFragment)
        }
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 7ae96b5 (Versión más acutual)

        // Observar datos de préstamos con retraso
        viewModel.prestamosPendientesCalculados.observe(viewLifecycleOwner) { list ->
            binding.rvMorosos.adapter = PrestamosPendientesAdapter(list)
            binding.rvMorosos.isVisible = list.isNotEmpty()
            binding.tvSectionTitleMorosos.isVisible = list.isNotEmpty()
        }

        // Observar libros más solicitados
        viewModel.top5LibrosMasPrestados.observe(viewLifecycleOwner) { list ->
            binding.rvTopLibros.adapter = TopLibrosAdapter(list)
            binding.tvEmptyTopLibros.isVisible = list.isEmpty()
            binding.rvTopLibros.isVisible = list.isNotEmpty()
        }
    }

    private fun setupRecyclerViews() {
        binding.rvMorosos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMorosos.setHasFixedSize(true)

        binding.rvTopLibros.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTopLibros.setHasFixedSize(true)
<<<<<<< HEAD
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
