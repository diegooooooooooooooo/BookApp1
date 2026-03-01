package com.example.bookapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookapp.databinding.FragmentDashboardBibliotecarioBinding

/**
 * Pantalla principal para el rol de Bibliotecario.
 * Contiene accesos rápidos a préstamos, devoluciones y búsqueda.
 */
class DashboardBibliotecarioFragment : Fragment() {

    private var _binding: FragmentDashboardBibliotecarioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBibliotecarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Configurar botones de acciones rápidas
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
