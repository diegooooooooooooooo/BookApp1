package com.example.bookapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
<<<<<<< HEAD
=======
import androidx.core.view.isVisible
>>>>>>> experimental
=======
import androidx.core.view.isVisible
>>>>>>> 7ae96b5 (Versión más acutual)
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.data.model.PrestamoPendiente
import com.example.bookapp.databinding.FragmentDashboardAdminBinding
<<<<<<< HEAD
<<<<<<< HEAD
=======
import com.example.bookapp.ui.reportes.TopLibrosAdapter
>>>>>>> experimental
=======
import com.example.bookapp.ui.reportes.TopLibrosAdapter
>>>>>>> 7ae96b5 (Versión más acutual)
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import java.util.Calendar

class DashboardAdminFragment : Fragment() {

    private var _binding: FragmentDashboardAdminBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BibliotecaViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardAdminBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

<<<<<<< HEAD
<<<<<<< HEAD
        setupMorososRecyclerView()
=======
        setupRecyclerViews()
>>>>>>> experimental
=======
        setupRecyclerViews()
>>>>>>> 7ae96b5 (Versión más acutual)

        binding.btnVerTodosMorosos.setOnClickListener {
            // Navegar a la lista completa de préstamos pendientes con opciones de ordenamiento
            findNavController().navigate(R.id.action_dashboardAdminFragment_to_listaPendientesFragment)
        }

        // Observar datos reales optimizados desde el ViewModel
        viewModel.prestamosPendientesCalculados.observe(viewLifecycleOwner) { pendientesList ->
            binding.rvMorosos.adapter = PrestamosPendientesAdapter(pendientesList)

            val countPendientes = pendientesList.size
            binding.badgeError.text = "$countPendientes con retraso"
            binding.badgeMorososCount.text = "$countPendientes pendientes"
        }

        viewModel.prestamosActivosConDetalles.observe(viewLifecycleOwner) { prestamos ->
            binding.tvTotalPrestamos.text = prestamos.size.toString()
            val countPendientes = binding.badgeMorososCount.text.toString().split(" ")[0].toIntOrNull() ?: 0
            binding.badgeSuccess.text = "${prestamos.size - countPendientes} al día"
        }
<<<<<<< HEAD
<<<<<<< HEAD
=======

        // Observar libros más solicitados
        viewModel.top5LibrosMasPrestados.observe(viewLifecycleOwner) { topList ->
            val topAdapter = TopLibrosAdapter(topList)
            binding.rvTopLibros.adapter = topAdapter
            binding.tvEmptyTopLibros.isVisible = topList.isEmpty()
            binding.rvTopLibros.isVisible = topList.isNotEmpty()
        }
>>>>>>> 7ae96b5 (Versión más acutual)
    }

    private fun setupRecyclerViews() {
        binding.rvMorosos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMorosos.setHasFixedSize(true)
<<<<<<< HEAD
=======

        // Observar libros más solicitados
        viewModel.top5LibrosMasPrestados.observe(viewLifecycleOwner) { topList ->
            val topAdapter = TopLibrosAdapter(topList)
            binding.rvTopLibros.adapter = topAdapter
            binding.tvEmptyTopLibros.isVisible = topList.isEmpty()
            binding.rvTopLibros.isVisible = topList.isNotEmpty()
        }
    }

    private fun setupRecyclerViews() {
        binding.rvMorosos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMorosos.setHasFixedSize(true)

        binding.rvTopLibros.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTopLibros.setHasFixedSize(true)
>>>>>>> experimental
=======

        binding.rvTopLibros.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTopLibros.setHasFixedSize(true)
>>>>>>> 7ae96b5 (Versión más acutual)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
