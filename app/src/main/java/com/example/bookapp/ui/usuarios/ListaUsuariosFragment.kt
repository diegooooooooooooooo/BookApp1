package com.example.bookapp.ui.usuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.data.entities.SocioEntity
import com.example.bookapp.databinding.FragmentListaUsuariosBinding
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

class ListaUsuariosFragment : Fragment() {

    private var _binding: FragmentListaUsuariosBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    private val bibliotecaViewModel: BibliotecaViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaUsuariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUsuarios.layoutManager = LinearLayoutManager(requireContext())

        loginViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            if (usuario?.rol == "ADMIN") {
                setupAdminView()
            } else {
                setupBibliotecarioView()
            }
        }
    }

    private fun setupAdminView() {
        val adapter = SocioAdapter { /* Admin detail view */ }
        binding.rvUsuarios.adapter = adapter
        
        binding.fabAddUsuario.setOnClickListener {
            findNavController().navigate(R.id.action_listaUsuariosFragment_to_registrarUsuarioFragment)
        }

        loginViewModel.allUsuarios.observe(viewLifecycleOwner) { usuarios ->
            val data = usuarios.map { SocioEntity(id = it.id, nombre = it.nombre, correo = it.correo, dni = "", telefono = "") }
            adapter.submitList(data)
        }
    }

    private fun setupBibliotecarioView() {
        val adapter = SocioAdapter { /* Reader detail view */ }
        binding.rvUsuarios.adapter = adapter

        binding.fabAddUsuario.setOnClickListener {
            // Navigate to register Reader (Socio) fragment
            findNavController().navigate(R.id.registrarSocioFragment)
        }

        bibliotecaViewModel.allSocios.observe(viewLifecycleOwner) { socios ->
            adapter.submitList(socios)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
