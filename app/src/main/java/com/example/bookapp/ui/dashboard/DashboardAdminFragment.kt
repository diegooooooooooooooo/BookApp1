package com.example.bookapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.R
import com.example.bookapp.data.database.AppDatabase
import com.example.bookapp.databinding.FragmentDashboardAdminBinding
import com.example.bookapp.repository.BibliotecaRepository
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import java.util.Calendar

class DashboardAdminFragment : Fragment() {

    private var _binding: FragmentDashboardAdminBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BibliotecaViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        ViewModelFactory(BibliotecaRepository(database.libroDao(), database.usuarioDao(), database.prestamoDao(), database.socioDao()))
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

        binding.btnVerReportes.setOnClickListener {
            findNavController().navigate(R.id.reporteMensualFragment)
        }

        // Obtener mes actual para los reportes
        val mesActual = Calendar.getInstance().get(Calendar.MONTH) + 1
        val mesStr = mesActual.toString().padStart(2, '0')

        viewModel.getIngresosMes(mesStr).observe(viewLifecycleOwner) { ingresos ->
            binding.tvIngresosMes.text = "$${String.format("%.2f", ingresos ?: 0.0)}"
        }

        viewModel.prestamosActivos.observe(viewLifecycleOwner) { prestamos ->
            binding.tvLibrosPrestados.text = prestamos.size.toString()
        }

        viewModel.allSocios.observe(viewLifecycleOwner) { socios ->
            binding.tvUsuariosActivos.text = socios.size.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
