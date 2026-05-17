package com.example.bookapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapp.BookApplication
import com.example.bookapp.data.entities.PrestamoConDetalles
import com.example.bookapp.databinding.FragmentCalendarioPrestamosBinding
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import java.util.*

/**
 * Pantalla que muestra un calendario con las fechas de préstamo y devolución.
 * Refactorizada para observar reactivamente el estado de login.
 */
class CalendarioPrestamosFragment : Fragment() {

    private var _binding: FragmentCalendarioPrestamosBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    private lateinit var adapter: PrestamosLectorAdapter
    private var allPrestamos: List<PrestamoConDetalles> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarioPrestamosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupCalendar()
        observeLoginAndPrestamos()
    }

    private fun setupRecyclerView() {
        adapter = PrestamosLectorAdapter()
        binding.rvPrestamosDia.layoutManager = LinearLayoutManager(context)
        binding.rvPrestamosDia.adapter = adapter
    }

    private fun setupCalendar() {
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            filterPrestamosByDate(year, month, dayOfMonth)
        }
    }

    private fun observeLoginAndPrestamos() {
        // Observamos el usuario logueado para reaccionar si el login tarda
        loginViewModel.usuarioLogueado.observe(viewLifecycleOwner) { user ->
            user?.let {
                val repository = (requireActivity().application as BookApplication).repository
                
                // Una vez tenemos al usuario, observamos sus préstamos
                repository.getPrestamosPorCorreoSocio(it.correo).asLiveData()
                    .observe(viewLifecycleOwner) { prestamos ->
                        allPrestamos = prestamos
                        // Mostramos todos los préstamos por defecto para que el usuario los vea inmediatamente
                        adapter.submitList(prestamos)
                    }
            }
        }
    }

    private fun filterPrestamosByDate(year: Int, month: Int, dayOfMonth: Int) {
        val prestamosDia = allPrestamos.filter { 
            val devDate = Calendar.getInstance()
            devDate.timeInMillis = it.fechaDevolucionEsperada
            devDate.get(Calendar.YEAR) == year &&
            devDate.get(Calendar.MONTH) == month &&
            devDate.get(Calendar.DAY_OF_MONTH) == dayOfMonth
        }
        adapter.submitList(prestamosDia)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
