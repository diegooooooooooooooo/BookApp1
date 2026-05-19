package com.example.bookapp.ui.configuracion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.bookapp.BookApplication
import com.example.bookapp.databinding.FragmentMiPerfilBinding
import com.example.bookapp.viewmodel.LoginViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import java.io.File

class MiPerfilFragment : Fragment() {

    private var _binding: FragmentMiPerfilBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMiPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.usuarioLogueado.observe(viewLifecycleOwner) { usuario ->
            usuario?.let {
                binding.tvProfileNombre.text = it.nombre
                binding.tvProfileEmail.text = it.correo
                binding.tvProfilePassword.text = it.contrasena
                
                if (it.fotoId != null) {
                    val file = File(it.fotoId)
                    if (file.exists()) {
                        binding.ivProfileId.isVisible = true
                        binding.tvProfileIdStatus.isVisible = false
                        binding.ivProfileId.load(file) {
                            crossfade(true)
                            // Remove tint when image is loaded
                            listener(onSuccess = { _, _ -> binding.ivProfileId.imageTintList = null })
                        }
                    } else {
                        binding.ivProfileId.isVisible = false
                        binding.tvProfileIdStatus.isVisible = true
                        binding.tvProfileIdStatus.text = "Imagen no encontrada"
                    }
                } else {
                    binding.ivProfileId.isVisible = false
                    binding.tvProfileIdStatus.isVisible = true
                    binding.tvProfileIdStatus.text = "No disponible"
                }
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
