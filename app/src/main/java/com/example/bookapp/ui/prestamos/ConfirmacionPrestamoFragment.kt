package com.example.bookapp.ui.prestamos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentConfirmacionPrestamoBinding

/**
 * Pantalla de ticket digital.
 * Muestra el resumen del préstamo recién registrado.
 */
class ConfirmacionPrestamoFragment : Fragment() {

    private var _binding: FragmentConfirmacionPrestamoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmacionPrestamoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFinalizar.setOnClickListener {
            // Volver al dashboard del bibliotecario
            findNavController().navigate(R.id.dashboardBibliotecarioFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
