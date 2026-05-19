package com.example.bookapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.bookapp.BookApplication
import com.example.bookapp.data.entities.PrestamoConDetalles
import com.example.bookapp.databinding.FragmentCalendarioPrestamosBinding
import com.example.bookapp.viewmodel.BibliotecaViewModel
<<<<<<< HEAD
=======
=======
import androidx.fragment.app.viewModels
>>>>>>> 7ae96b5 (Versión más acutual)
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.bookapp.BookApplication
import com.example.bookapp.data.entities.PrestamoConDetalles
import com.example.bookapp.databinding.FragmentCalendarioPrestamosBinding
<<<<<<< HEAD
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
import com.example.bookapp.viewmodel.BibliotecaViewModel
>>>>>>> 7ae96b5 (Versión más acutual)
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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
    private val viewModel: BibliotecaViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        setupButtons()
=======
>>>>>>> 0800574 (Versión más acutual)
=======
        setupButtons()
>>>>>>> experimental
=======
        setupButtons()
>>>>>>> 7ae96b5 (Versión más acutual)
        observeLoginAndPrestamos()
    }

    private fun setupRecyclerView() {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
        adapter = PrestamosLectorAdapter { libroId ->
            val action = CalendarioPrestamosFragmentDirections.actionCalendarioPrestamosFragmentToDetalleLibroFragment(
                libroId = libroId,
                ocultarBotonPrestar = true
            )
            findNavController().navigate(action)
        }
<<<<<<< HEAD
<<<<<<< HEAD
=======
        adapter = PrestamosLectorAdapter()
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
        binding.rvPrestamosDia.layoutManager = LinearLayoutManager(context)
        binding.rvPrestamosDia.adapter = adapter
    }

    private fun setupCalendar() {
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            binding.tvInfoLabel.text = "Detalle del día seleccionado ($dayOfMonth/${month + 1}/$year):"
=======
>>>>>>> 0800574 (Versión más acutual)
=======
            binding.tvInfoLabel.text = "Detalle del día seleccionado ($dayOfMonth/${month + 1}/$year):"
>>>>>>> experimental
=======
            binding.tvInfoLabel.text = "Detalle del día seleccionado ($dayOfMonth/${month + 1}/$year):"
>>>>>>> 7ae96b5 (Versión más acutual)
            filterPrestamosByDate(year, month, dayOfMonth)
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
    private fun setupButtons() {
        binding.btnShowAll.setOnClickListener {
            binding.tvInfoLabel.text = "Todos mis préstamos:"
            adapter.submitList(allPrestamos)
            
            if (allPrestamos.isEmpty()) {
                binding.tvInfoLabel.text = "No se encontraron préstamos para tu cuenta."
            }
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
    private fun observeLoginAndPrestamos() {
        // Observamos el usuario logueado para reaccionar si el login tarda
        loginViewModel.usuarioLogueado.observe(viewLifecycleOwner) { user ->
            user?.let {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
                // Una vez tenemos al usuario, observamos sus préstamos a través del ViewModel
                viewModel.getPrestamosPorCorreoSocio(it.correo).observe(viewLifecycleOwner) { prestamos ->
                    allPrestamos = prestamos
                    // Mostramos todos los préstamos por defecto para que el usuario los vea inmediatamente
                    if (prestamos.isEmpty()) {
                        binding.tvInfoLabel.text = "Aún no tienes préstamos registrados."
                    } else {
                        binding.tvInfoLabel.text = "Todos mis préstamos:"
<<<<<<< HEAD
                    }
                    adapter.submitList(prestamos)
                }
<<<<<<< HEAD
=======
                val repository = (requireActivity().application as BookApplication).repository
                
                // Una vez tenemos al usuario, observamos sus préstamos
                repository.getPrestamosPorCorreoSocio(it.correo).asLiveData()
                    .observe(viewLifecycleOwner) { prestamos ->
                        allPrestamos = prestamos
                        // Mostramos todos los préstamos por defecto para que el usuario los vea inmediatamente
                        adapter.submitList(prestamos)
                    }
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
                    }
                    adapter.submitList(prestamos)
                }
>>>>>>> 7ae96b5 (Versión más acutual)
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
