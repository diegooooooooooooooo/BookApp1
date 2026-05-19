package com.example.bookapp.ui.configuracion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.example.bookapp.R
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
                            listener(onSuccess = { _, _ -> 
                                binding.ivProfileId.imageTintList = null 
                                binding.ivProfileId.setOnClickListener {
                                    showFullScreenImage(file)
                                }
                            })
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

    private fun showFullScreenImage(file: File) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_full_image, null)
        val imageView = dialogView.findViewById<ImageView>(R.id.ivFullImage)
        val btnClose = dialogView.findViewById<ImageButton>(R.id.btnClose)
        
        // Use fitCenter and ensure crossfade doesn't interfere
        imageView.load(file) {
            crossfade(true)
        }

        val dialog = AlertDialog.Builder(requireContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            .setView(dialogView)
            .create()

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        // Set window background to black to avoid white flashes
        dialog.window?.setBackgroundDrawableResource(android.R.color.black)

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
