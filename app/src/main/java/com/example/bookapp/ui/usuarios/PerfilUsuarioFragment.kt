package com.example.bookapp.ui.usuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentPerfilUsuarioBinding
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import java.util.*

/**
 * Fragmento que muestra el perfil de un usuario/socio, sus libros prestados y multas.
 */
class PerfilUsuarioFragment : Fragment() {

    private var _binding: FragmentPerfilUsuarioBinding? = null
    private val binding get() = _binding!!
    private val args: PerfilUsuarioFragmentArgs by navArgs()

    private val loginViewModel: LoginViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    private val bibliotecaViewModel: BibliotecaViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerfilUsuarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val usuarioId = args.usuarioId
        val socioId = args.socioId

        if (usuarioId != -1) {
            setupUserDetail(usuarioId)
        } else if (socioId != -1) {
            setupSocioDetail(socioId)
        }

        binding.btnPrestarLibroUser.setOnClickListener {
            findNavController().navigate(R.id.registrarPrestamoFragment)
        }

        binding.btnRegistrarDevolucionUser.setOnClickListener {
            findNavController().navigate(R.id.registrarDevolucionFragment)
        }
    }

    private fun setupUserDetail(id: Int) {
        loginViewModel.allUsuarios.observe(viewLifecycleOwner) { usuarios ->
            val user = usuarios.find { it.id == id }
            user?.let {
                binding.tvUserDetailName.text = it.nombre
                binding.tvUserDetailId.text = "ID Cuenta: #${it.id}"
                binding.tvUserDetailEmail.text = it.correo
                binding.tvUserDetailRole.text = it.rol
                
                if (it.rol != "LECTOR" && it.rol != "USUARIO") {
                    binding.cardUserStats.visibility = View.GONE
                    binding.btnPrestarLibroUser.visibility = View.GONE
                    binding.btnRegistrarDevolucionUser.visibility = View.GONE
                } else {
                    binding.cardUserStats.visibility = View.VISIBLE
                    binding.btnPrestarLibroUser.visibility = View.VISIBLE
                    binding.btnRegistrarDevolucionUser.visibility = View.VISIBLE
                    observeReaderStats(it.correo)
                }
            }
        }
    }

    private fun setupSocioDetail(id: Int) {
        bibliotecaViewModel.allSocios.observe(viewLifecycleOwner) { socios ->
            val socio = socios.find { it.id == id }
            socio?.let {
                binding.tvUserDetailName.text = it.nombre
                binding.tvUserDetailId.text = "ID Socio: #${it.id}"
                binding.tvUserDetailEmail.text = it.correo ?: "Sin correo"
                
                binding.tvLabelRole.visibility = View.GONE
                binding.tvUserDetailRole.visibility = View.GONE
                binding.roleDivider.visibility = View.GONE

                observeReaderStats(it.correo ?: "")
            }
        }
    }

    private fun observeReaderStats(correo: String) {
        if (correo.isEmpty()) return

        val repository = (requireActivity().application as BookApplication).repository
        repository.getPrestamosPorCorreoSocio(correo).asLiveData().observe(viewLifecycleOwner) { prestamos ->
            val activos = prestamos.filter { it.fechaEntregaReal == null }
            binding.tvUserLibrosActivos.text = "${activos.size} libros"
            
            val totalMulta = prestamos.sumOf { it.multa }
            binding.tvUserMultas.text = String.format(Locale.getDefault(), "$%.2f", totalMulta)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
