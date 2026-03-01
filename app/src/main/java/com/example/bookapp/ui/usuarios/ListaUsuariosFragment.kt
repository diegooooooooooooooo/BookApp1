package com.example.bookapp.ui.usuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentListaUsuariosBinding

/**
 * Pantalla que muestra la lista de socios/usuarios de la biblioteca.
 */
class ListaUsuariosFragment : Fragment() {

    private var _binding: FragmentListaUsuariosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaUsuariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddUsuario.setOnClickListener {
            // Navegar a agregar usuario (podría reutilizar un perfil vacío)
        }
        
        // Configurar RecyclerView de usuarios
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
