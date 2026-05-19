package com.example.bookapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bookapp.BookApplication
import com.example.bookapp.R
import com.example.bookapp.databinding.FragmentDashboardLectorBinding
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

/**
 * Pantalla principal para el rol de Lector (Usuario común).
 */
class DashboardLectorFragment : Fragment() {

    private var _binding: FragmentDashboardLectorBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardLectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        loginViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                binding.tvWelcome.text = "Hola, ${it.nombre}"
            }
        }

        binding.cardCatalogo.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardLectorFragment_to_catalogoLibrosFragment)
        }

        binding.cardPrestamos.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardLectorFragment_to_calendarioPrestamosFragment)
        }

        binding.cardConfiguracion.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardLectorFragment_to_configuracionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
