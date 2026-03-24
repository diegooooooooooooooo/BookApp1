package com.example.bookapp.ui.configuracion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.R
import com.example.bookapp.data.database.AppDatabase
import com.example.bookapp.databinding.FragmentConfiguracionBinding
import com.example.bookapp.repository.BibliotecaRepository
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

class ConfiguracionFragment : Fragment() {

    private var _binding: FragmentConfiguracionBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        ViewModelFactory(BibliotecaRepository(database.libroDao(), database.usuarioDao(), database.prestamoDao(), database.socioDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfiguracionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCerrarSesion.setOnClickListener {
            loginViewModel.logout()
            findNavController().navigate(R.id.loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
