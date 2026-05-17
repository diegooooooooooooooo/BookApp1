package com.example.bookapp.ui.prestamos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.data.entities.LibroEstado
import com.example.bookapp.data.entities.PrestamoEntity
import com.example.bookapp.data.model.UserRole
import com.example.bookapp.databinding.FragmentRegistrarPrestamoBinding
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

import androidx.navigation.fragment.navArgs

class RegistrarPrestamoFragment : Fragment() {

    private var _binding: FragmentRegistrarPrestamoBinding? = null
    private val binding get() = _binding!!
    private val args: RegistrarPrestamoFragmentArgs by navArgs()

    private val viewModel: BibliotecaViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    private val loginViewModel: LoginViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    private var selectedSocioId: Int = -1
    private var selectedLibroId: Int = -1
    private var selectedDateMillis: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrarPrestamoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize selectedLibroId from args immediately
        if (args.libroId != -1) {
            selectedLibroId = args.libroId
            binding.tilLibro.isVisible = false
        }
        
        // Initialize selectedSocioId from args if provided
        if (args.socioId != -1) {
            selectedSocioId = args.socioId
        }

        // Hide user selection if it's a reader
        loginViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            val isLector = usuario?.rol == UserRole.LECTOR || usuario?.rol == UserRole.USUARIO
            binding.tilUsuario.isVisible = !isLector
            
            if (isLector) {
                // Pre-select the current user as the socio
                viewModel.allSocios.observe(viewLifecycleOwner) { socios ->
                    val currentSocio = socios.find { it.correo == usuario.correo }
                    currentSocio?.let {
                        selectedSocioId = it.id
                        binding.spinnerUsuario.setText(it.nombre, false)
                    }
                }
            }
        }

        // Observe Socios (Readers) from DB
        viewModel.allSocios.observe(viewLifecycleOwner) { socios ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, socios.map { it.nombre })
            binding.spinnerUsuario.setAdapter(adapter)

            // Auto-select socio if passed via Safe Args
            if (args.socioId != -1 && selectedSocioId == -1) {
                val socioPreseleccionado = socios.find { it.id == args.socioId }
                socioPreseleccionado?.let {
                    selectedSocioId = it.id
                    binding.spinnerUsuario.setText(it.nombre, false)
                }
            }

            binding.spinnerUsuario.setOnItemClickListener { _, _, position, _ ->
                selectedSocioId = socios[position].id
            }
        }

        // Observe Books from DB (Combined with Simulation)
        viewModel.allLibrosCombined.observe(viewLifecycleOwner) { libros ->
            val librosDisponibles = libros.filter { it.estado == LibroEstado.DISPONIBLE }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, librosDisponibles.map { it.titulo })
            binding.spinnerLibro.setAdapter(adapter)
            
            // Update UI if book is pre-selected (searching in all books, not just available)
            if (selectedLibroId != -1) {
                val libroPreseleccionado = libros.find { it.id == selectedLibroId }
                libroPreseleccionado?.let {
                    binding.spinnerLibro.setText(it.titulo, false)
                    
                    // Update UI to show what book is selected if the field is hidden
                    if (!binding.tilLibro.isVisible) {
                        binding.tilLibro.isVisible = true
                        binding.tilLibro.isEnabled = false
                        binding.spinnerLibro.isEnabled = false
                    }
                }
            }

            binding.spinnerLibro.setOnItemClickListener { _, _, position, _ ->
                selectedLibroId = librosDisponibles[position].id
            }
        }

        binding.etFechaDevolucion.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Fecha de devolución")
                .build()

            datePicker.addOnPositiveButtonClickListener { selection ->
                selectedDateMillis = selection
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.etFechaDevolucion.setText(sdf.format(Date(selection)))
            }
            datePicker.show(parentFragmentManager, "DATE_PICKER")
        }

        binding.btnConfirmarPrestamo.setOnClickListener {
            when {
                selectedSocioId == -1 -> Toast.makeText(context, "Por favor, seleccione un usuario", Toast.LENGTH_SHORT).show()
                selectedLibroId == -1 -> Toast.makeText(context, "Por favor, seleccione un libro", Toast.LENGTH_SHORT).show()
                selectedDateMillis == 0L -> Toast.makeText(context, "Por favor, seleccione una fecha de devolución", Toast.LENGTH_SHORT).show()
                else -> {
                    val prestamo = PrestamoEntity(
                        socioId = selectedSocioId,
                        libroId = selectedLibroId,
                        fechaPrestamo = System.currentTimeMillis(),
                        fechaDevolucionEsperada = selectedDateMillis
                    )
                    viewModel.registrarPrestamo(prestamo)
                    Toast.makeText(context, "Préstamo registrado exitosamente", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.confirmacionPrestamoFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
