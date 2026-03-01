package com.example.bookapp.ui.prestamos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentRegistrarPrestamoBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

/**
 * Fragmento para registrar un nuevo préstamo.
 * Permite seleccionar usuario, libro y fecha de devolución.
 */
class RegistrarPrestamoFragment : Fragment() {

    private var _binding: FragmentRegistrarPrestamoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrarPrestamoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Simulación de datos para los selectores
        val usuarios = arrayOf("Juan Pérez", "María García", "Carlos López")
        val libros = arrayOf("Don Quijote de la Mancha", "Cien años de soledad", "El Principito")

        binding.spinnerUsuario.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, usuarios))
        binding.spinnerLibro.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, libros))

        // Selector de fecha
        binding.etFechaDevolucion.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Seleccionar fecha de devolución")
                .build()

            datePicker.addOnPositiveButtonClickListener { selection ->
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.etFechaDevolucion.setText(sdf.format(Date(selection)))
            }
            datePicker.show(parentFragmentManager, "DATE_PICKER")
        }

        binding.btnConfirmarPrestamo.setOnClickListener {
            // Navegar a la pantalla de confirmación (Ticket)
            findNavController().navigate(R.id.confirmacionPrestamoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
