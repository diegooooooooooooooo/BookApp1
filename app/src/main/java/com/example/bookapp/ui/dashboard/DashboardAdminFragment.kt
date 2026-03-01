package com.example.bookapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentDashboardAdminBinding

/**
 * Pantalla principal para el rol de Administrador.
 * Muestra KPIs rápidos y acceso a reportes detallados.
 */
class DashboardAdminFragment : Fragment() {

    private var _binding: FragmentDashboardAdminBinding? = null
    private val binding get() = _binding!!

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
        
        // Aquí se cargarían los datos reales del ViewModel en una implementación completa
        binding.tvIngresosMes.text = "$4,250.00"
        binding.tvLibrosPrestados.text = "42"
        binding.tvUsuariosActivos.text = "15"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
