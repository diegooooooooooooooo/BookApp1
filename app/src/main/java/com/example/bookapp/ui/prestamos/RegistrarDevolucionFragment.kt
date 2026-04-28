package com.example.bookapp.ui.prestamos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bookapp.BookApplication
import com.example.bookapp.data.entities.PrestamoConDetalles
import com.example.bookapp.data.entities.PrestamoEntity
import com.example.bookapp.databinding.FragmentRegistrarDevolucionBinding
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * Fragmento para registrar la devolución de un libro.
 * Calcula multas si existen retrasos.
 */
class RegistrarDevolucionFragment : Fragment() {

    private var _binding: FragmentRegistrarDevolucionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BibliotecaViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    private var selectedPrestamo: PrestamoConDetalles? = null
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val MULTA_DIARIA = 50.0 // Ejemplo: $50 por día de retraso

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrarDevolucionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.prestamosActivosConDetalles.observe(viewLifecycleOwner) { prestamos ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, 
                prestamos.map { "${it.libroTitulo} - ${it.socioNombre}" })
            
            binding.spinnerPrestamos.setAdapter(adapter)
            binding.spinnerPrestamos.setOnItemClickListener { _, _, position, _ ->
                selectedPrestamo = prestamos[position]
                actualizarDetalles()
            }
        }

        binding.btnConfirmarDevolucion.setOnClickListener {
            selectedPrestamo?.let { prestamoDetalle ->
                lifecycleScope.launch {
                    val prestamoEntity = viewModel.getPrestamoById(prestamoDetalle.id)
                    prestamoEntity?.let {
                        val hoy = System.currentTimeMillis()
                        val multa = calcularMulta(it.fechaDevolucionEsperada, hoy)
                        
                        val prestamoActualizado = it.copy(
                            fechaEntregaReal = hoy,
                            multa = multa
                        )
                        
                        viewModel.registrarDevolucion(prestamoActualizado)
                        Toast.makeText(context, "Devolución registrada. Multa: $$multa", Toast.LENGTH_LONG).show()
                        findNavController().popBackStack()
                    }
                }
            } ?: Toast.makeText(context, "Selecciona un préstamo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun actualizarDetalles() {
        selectedPrestamo?.let {
            binding.tvDevolucionLibro.text = "Libro: ${it.libroTitulo}"
            binding.tvDevolucionUsuario.text = "Usuario: ${it.socioNombre}"
            binding.tvDevolucionFechaLimite.text = "Fecha Límite: ${dateFormat.format(Date(it.fechaDevolucionEsperada))}"
            
            val hoy = System.currentTimeMillis()
            val diasRetraso = calcularDiasRetraso(it.fechaDevolucionEsperada, hoy)
            val multa = calcularMulta(it.fechaDevolucionEsperada, hoy)
            
            binding.tvDiasRetraso.text = "Días de retraso: $diasRetraso"
            binding.tvMultaGenerada.text = "Multa: $${String.format("%.2f", multa)}"
        }
    }

    private fun calcularDiasRetraso(fechaEsperada: Long, fechaReal: Long): Int {
        if (fechaReal <= fechaEsperada) return 0
        val diff = fechaReal - fechaEsperada
        return (diff / (1000 * 60 * 60 * 24)).toInt()
    }

    private fun calcularMulta(fechaEsperada: Long, fechaReal: Long): Double {
        val dias = calcularDiasRetraso(fechaEsperada, fechaReal)
        return dias * MULTA_DIARIA
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
