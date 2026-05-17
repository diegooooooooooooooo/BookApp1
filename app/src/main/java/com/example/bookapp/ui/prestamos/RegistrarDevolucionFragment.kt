package com.example.bookapp.ui.prestamos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapp.BookApplication
import com.example.bookapp.data.entities.PrestamoConDetalles
import com.example.bookapp.data.entities.SocioEntity
import com.example.bookapp.databinding.FragmentRegistrarDevolucionBinding
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

/**
 * Fragmento para registrar la devolución de uno o varios libros.
 * El bibliotecario selecciona un usuario y luego los libros a devolver.
 */
class RegistrarDevolucionFragment : Fragment() {

    private var _binding: FragmentRegistrarDevolucionBinding? = null
    private val binding get() = _binding!!
    private val args: RegistrarDevolucionFragmentArgs by navArgs()

    private val viewModel: BibliotecaViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    private lateinit var libroAdapter: LibroDevolucionAdapter
    private var selectedSocio: SocioEntity? = null
    private val MULTA_DIARIA = 50.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrarDevolucionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupUserSelection()

        binding.btnConfirmarDevolucion.setOnClickListener {
            confirmarDevolucionMasiva()
        }
    }

    private fun setupRecyclerView() {
        libroAdapter = LibroDevolucionAdapter { selectedList ->
            actualizarResumen(selectedList)
        }
        binding.rvLibrosDevolucion.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = libroAdapter
        }
    }

    private fun setupUserSelection() {
        viewModel.allSocios.observe(viewLifecycleOwner) { socios ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line,
                socios.map { "${it.nombre} (${it.dni})" })

            binding.spinnerUsuarios.setAdapter(adapter)
            
            // Handle pre-selected user from args
            if (args.socioId != -1 && selectedSocio == null) {
                val socioPreseleccionado = socios.find { it.id == args.socioId }
                socioPreseleccionado?.let {
                    selectedSocio = it
                    binding.spinnerUsuarios.setText("${it.nombre} (${it.dni})", false)
                    cargarLibrosDeUsuario(it.id)
                }
            }

            binding.spinnerUsuarios.setOnItemClickListener { _, _, position, _ ->
                selectedSocio = socios[position]
                cargarLibrosDeUsuario(socios[position].id)
            }
        }
    }

    private fun cargarLibrosDeUsuario(socioId: Int) {
        viewModel.getPrestamosActivosPorSocio(socioId).observe(viewLifecycleOwner) { prestamos ->
            libroAdapter.submitList(prestamos)
            binding.tvLibrosPrestadosTitle.isVisible = true
            libroAdapter.clearSelection()
            actualizarResumen(emptyList())
            
            if (prestamos.isEmpty()) {
                Toast.makeText(context, "Este usuario no tiene préstamos activos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun actualizarResumen(selectedList: List<PrestamoConDetalles>) {
        binding.cardResumenMulta.isVisible = selectedList.isNotEmpty()
        binding.btnConfirmarDevolucion.isEnabled = selectedList.isNotEmpty()

        if (selectedList.isNotEmpty()) {
            val totalMulta = selectedList.sumOf { calcularMulta(it.fechaDevolucionEsperada, System.currentTimeMillis()) }
            binding.tvLibrosSeleccionados.text = "Libros seleccionados: ${selectedList.size}"
            binding.tvTotalMulta.text = "Total Multa: $${String.format("%.2f", totalMulta)}"
        }
    }

    private fun calcularMulta(fechaEsperada: Long, fechaReal: Long): Double {
        if (fechaReal <= fechaEsperada) return 0.0
        val diff = fechaReal - fechaEsperada
        val dias = (diff / (1000 * 60 * 60 * 24)).toInt()
        return dias * MULTA_DIARIA
    }

    private fun confirmarDevolucionMasiva() {
        val selectedPrestamos = libroAdapter.getSelectedPrestamos()
        if (selectedPrestamos.isEmpty()) return

        lifecycleScope.launch {
            val hoy = System.currentTimeMillis()
            var procesados = 0
            
            selectedPrestamos.forEach { prestamoDetalle ->
                val prestamoEntity = viewModel.getPrestamoById(prestamoDetalle.id)
                prestamoEntity?.let {
                    val multa = calcularMulta(it.fechaDevolucionEsperada, hoy)
                    val prestamoActualizado = it.copy(
                        fechaEntregaReal = hoy,
                        multa = multa
                    )
                    viewModel.registrarDevolucion(prestamoActualizado)
                    procesados++
                }
            }
            
            Toast.makeText(context, "$procesados devoluciones registradas con éxito", Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
