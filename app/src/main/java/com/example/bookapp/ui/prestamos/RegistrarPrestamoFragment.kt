package com.example.bookapp.ui.prestamos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.R
import com.example.bookapp.data.database.AppDatabase
import com.example.bookapp.data.entities.PrestamoEntity
import com.example.bookapp.databinding.FragmentRegistrarPrestamoBinding
import com.example.bookapp.repository.BibliotecaRepository
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class RegistrarPrestamoFragment : Fragment() {

    private var _binding: FragmentRegistrarPrestamoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BibliotecaViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        ViewModelFactory(BibliotecaRepository(database.libroDao(), database.usuarioDao(), database.prestamoDao(), database.socioDao()))
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

        // Observe Socios (Readers) from DB
        viewModel.allSocios.observe(viewLifecycleOwner) { socios ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, socios.map { it.nombre })
            binding.spinnerUsuario.setAdapter(adapter)
            binding.spinnerUsuario.setOnItemClickListener { _, _, position, _ ->
                selectedSocioId = socios[position].id
            }
        }

        // Observe Books from DB
        viewModel.allLibros.observe(viewLifecycleOwner) { libros ->
            val librosDisponibles = libros.filter { it.disponible }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, librosDisponibles.map { it.titulo })
            binding.spinnerLibro.setAdapter(adapter)
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
            if (selectedSocioId != -1 && selectedLibroId != -1 && selectedDateMillis != 0L) {
                val prestamo = PrestamoEntity(
                    socioId = selectedSocioId,
                    libroId = selectedLibroId,
                    fechaPrestamo = System.currentTimeMillis(),
                    fechaDevolucionEsperada = selectedDateMillis
                )
                viewModel.registrarPrestamo(prestamo)
                Toast.makeText(context, "Préstamo registrado exitosamente", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.confirmacionPrestamoFragment)
            } else {
                Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
