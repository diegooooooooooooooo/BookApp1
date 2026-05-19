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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.bookapp.data.entities.LibroEntity
=======
>>>>>>> 0800574 (Versión más acutual)
=======
import com.example.bookapp.data.entities.LibroEntity
>>>>>>> experimental
=======
import com.example.bookapp.data.entities.LibroEntity
>>>>>>> 7ae96b5 (Versión más acutual)
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private var selectedLibro: LibroEntity? = null
=======
>>>>>>> 0800574 (Versión más acutual)
=======
    private var selectedLibro: LibroEntity? = null
>>>>>>> experimental
=======
    private var selectedLibro: LibroEntity? = null
>>>>>>> 7ae96b5 (Versión más acutual)
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
            
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
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
        }

        // Observe Socios (Readers) from DB
        viewModel.allSocios.observe(viewLifecycleOwner) { socios ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, socios.map { it.nombre })
            binding.spinnerUsuario.setAdapter(adapter)

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
            // Auto-select socio if it's a lector
            val currentUser = loginViewModel.usuarioLogueado.value
            if (currentUser?.rol == UserRole.LECTOR || currentUser?.rol == UserRole.USUARIO) {
                val currentSocio = socios.find { it.correo?.lowercase() == currentUser.correo.lowercase() }
                currentSocio?.let {
                    selectedSocioId = it.id
                    binding.spinnerUsuario.setText(it.nombre, false)
                } ?: run {
                    Toast.makeText(context, "Error: No se encontró tu perfil de lector. Contacta al administrador.", Toast.LENGTH_LONG).show()
                }
            } else if (args.socioId != -1 && selectedSocioId == -1) {
                // Auto-select socio if passed via Safe Args (for librarian flow)
<<<<<<< HEAD
<<<<<<< HEAD
=======
            // Auto-select socio if passed via Safe Args
            if (args.socioId != -1 && selectedSocioId == -1) {
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                if (libroPreseleccionado != null) {
                    selectedLibro = libroPreseleccionado
                    binding.spinnerLibro.setText(libroPreseleccionado.titulo, false)
=======
                libroPreseleccionado?.let {
                    binding.spinnerLibro.setText(it.titulo, false)
>>>>>>> 0800574 (Versión más acutual)
=======
                if (libroPreseleccionado != null) {
                    selectedLibro = libroPreseleccionado
                    binding.spinnerLibro.setText(libroPreseleccionado.titulo, false)
>>>>>>> experimental
=======
                if (libroPreseleccionado != null) {
                    selectedLibro = libroPreseleccionado
                    binding.spinnerLibro.setText(libroPreseleccionado.titulo, false)
>>>>>>> 7ae96b5 (Versión más acutual)
                    
                    // Update UI to show what book is selected if the field is hidden
                    if (!binding.tilLibro.isVisible) {
                        binding.tilLibro.isVisible = true
                        binding.tilLibro.isEnabled = false
                        binding.spinnerLibro.isEnabled = false
                    }
                }
            }

            binding.spinnerLibro.setOnItemClickListener { _, _, position, _ ->
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                val libro = librosDisponibles[position]
                selectedLibroId = libro.id
                selectedLibro = libro
=======
                selectedLibroId = librosDisponibles[position].id
>>>>>>> 0800574 (Versión más acutual)
=======
                val libro = librosDisponibles[position]
                selectedLibroId = libro.id
                selectedLibro = libro
>>>>>>> experimental
=======
                val libro = librosDisponibles[position]
                selectedLibroId = libro.id
                selectedLibro = libro
>>>>>>> 7ae96b5 (Versión más acutual)
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
=======
>>>>>>> 7ae96b5 (Versión más acutual)
                    
                    binding.btnConfirmarPrestamo.isEnabled = false
                    binding.progressBar.isVisible = true
                    viewModel.registrarPrestamo(prestamo, selectedLibro)
<<<<<<< HEAD
                }
            }
        }

        viewModel.prestamoResult.observe(viewLifecycleOwner) { result ->
            if (result == null) return@observe
            
            binding.btnConfirmarPrestamo.isEnabled = true
            binding.progressBar.isVisible = false
            
            if (result.isSuccess) {
                Toast.makeText(context, "Préstamo registrado exitosamente", Toast.LENGTH_SHORT).show()
                
                val socioNombre = binding.spinnerUsuario.text.toString()
                val libroTitulo = binding.spinnerLibro.text.toString()
                
                val action = RegistrarPrestamoFragmentDirections.actionRegistrarPrestamoFragmentToConfirmacionPrestamoFragment(
                    socioNombre = socioNombre,
                    libroTitulo = libroTitulo,
                    fechaLimite = selectedDateMillis
                )
                viewModel.clearPrestamoResult()
                findNavController().navigate(action)
            } else {
                val error = result.exceptionOrNull()?.message ?: "Error desconocido"
                Toast.makeText(context, "Error: $error", Toast.LENGTH_LONG).show()
                viewModel.clearPrestamoResult()
            }
        }
<<<<<<< HEAD
=======
                    viewModel.registrarPrestamo(prestamo)
                    Toast.makeText(context, "Préstamo registrado exitosamente", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.confirmacionPrestamoFragment)
                }
            }
        }
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
                }
            }
        }

        viewModel.prestamoResult.observe(viewLifecycleOwner) { result ->
            if (result == null) return@observe
            
            binding.btnConfirmarPrestamo.isEnabled = true
            binding.progressBar.isVisible = false
            
            if (result.isSuccess) {
                Toast.makeText(context, "Préstamo registrado exitosamente", Toast.LENGTH_SHORT).show()
                
                val socioNombre = binding.spinnerUsuario.text.toString()
                val libroTitulo = binding.spinnerLibro.text.toString()
                
                val action = RegistrarPrestamoFragmentDirections.actionRegistrarPrestamoFragmentToConfirmacionPrestamoFragment(
                    socioNombre = socioNombre,
                    libroTitulo = libroTitulo,
                    fechaLimite = selectedDateMillis
                )
                viewModel.clearPrestamoResult()
                findNavController().navigate(action)
            } else {
                val error = result.exceptionOrNull()?.message ?: "Error desconocido"
                Toast.makeText(context, "Error: $error", Toast.LENGTH_LONG).show()
                viewModel.clearPrestamoResult()
            }
        }
>>>>>>> 7ae96b5 (Versión más acutual)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
