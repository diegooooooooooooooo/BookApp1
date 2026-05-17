package com.example.bookapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapp.BookApplication
import com.example.bookapp.data.model.PrestamoPendiente
import com.example.bookapp.databinding.FragmentListaPendientesBinding
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import com.example.bookapp.R

class ListaPendientesFragment : Fragment() {

    private var _binding: FragmentListaPendientesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BibliotecaViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    private var fullList: List<PrestamoPendiente> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaPendientesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPendientesCompletos.layoutManager = LinearLayoutManager(requireContext())

        viewModel.prestamosActivosConDetalles.observe(viewLifecycleOwner) { prestamos ->
            val currentTime = System.currentTimeMillis()
            fullList = prestamos.filter { it.fechaDevolucionEsperada < currentTime }
                .map { prestamo ->
                    val diasRetraso = ((currentTime - prestamo.fechaDevolucionEsperada) / (1000 * 60 * 60 * 24)).toInt()
                    PrestamoPendiente(
                        socioNombre = prestamo.socioNombre,
                        libroTitulo = prestamo.libroTitulo,
                        fechaVencimiento = prestamo.fechaDevolucionEsperada,
                        diasRetraso = diasRetraso
                    )
                }
            updateList()
        }

        binding.toggleGroupOrder.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                updateList()
            }
        }
    }

    private fun updateList() {
        val sortedList = if (binding.btnOrderNombre.isChecked) {
            fullList.sortedBy { it.socioNombre }
        } else {
            fullList.sortedByDescending { it.diasRetraso }
        }
        binding.rvPendientesCompletos.adapter = PrestamosPendientesAdapter(sortedList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
