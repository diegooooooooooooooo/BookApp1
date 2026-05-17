package com.example.bookapp.ui.libros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.bookapp.R
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.data.entities.LibroEntity
import com.example.bookapp.data.entities.LibroEstado
import com.example.bookapp.databinding.ItemLibroBinding
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat

class LibroAdapter(
    private val onLibroClick: (LibroEntity) -> Unit,
    private val onEditClick: (LibroEntity) -> Unit,
    private val onDeleteClick: (LibroEntity) -> Unit
) : ListAdapter<LibroEntity, LibroAdapter.LibroViewHolder>(LibroDiffCallback()) {

    private var isAdminMode = false

    fun setAdminMode(enabled: Boolean) {
        isAdminMode = enabled
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val binding = ItemLibroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LibroViewHolder(private val binding: ItemLibroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(libro: LibroEntity) {
            binding.tvTitulo.text = libro.titulo
            binding.tvAutor.text = libro.autor
            
            // Cargar portada con Coil
            binding.ivPortada.load(libro.portadaUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_book)
                error(R.drawable.ic_book)
                listener(
                    onSuccess = { _, _ -> 
                        binding.ivPortada.imageTintList = null 
                    },
                    onError = { _, _ -> 
                        binding.ivPortada.imageTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(binding.root.context, R.color.blue_slate)
                        )
                    }
                )
            }

            when (libro.estado) {
                LibroEstado.DISPONIBLE -> {
                    binding.chipEstado.text = "Disponible"
                    binding.chipEstado.setChipBackgroundColorResource(android.R.color.holo_green_light)
                }
                LibroEstado.PRESTADO -> {
                    binding.chipEstado.text = "Prestado"
                    binding.chipEstado.setChipBackgroundColorResource(android.R.color.holo_red_light)
                }
                LibroEstado.NO_DISPONIBLE -> {
                    binding.chipEstado.text = "No Disponible"
                    binding.chipEstado.setChipBackgroundColorResource(android.R.color.darker_gray)
                }
            }

            // Mostrar u ocultar acciones admin
            if (isAdminMode && !libro.isPlaceholder) {
                binding.llAccionesAdmin.visibility = View.VISIBLE
                binding.btnEditLibro.setOnClickListener { onEditClick(libro) }
                binding.btnDeleteLibro.setOnClickListener { onDeleteClick(libro) }
            } else {
                binding.llAccionesAdmin.visibility = View.GONE
            }

            if (libro.isPlaceholder) {
                binding.chipEstado.text = "Simulación"
                binding.chipEstado.setChipBackgroundColorResource(android.R.color.darker_gray)
            }

            binding.root.setOnClickListener { onLibroClick(libro) }
        }
    }

    class LibroDiffCallback : DiffUtil.ItemCallback<LibroEntity>() {
        override fun areItemsTheSame(oldItem: LibroEntity, newItem: LibroEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LibroEntity, newItem: LibroEntity): Boolean {
            return oldItem == newItem
        }
    }
}
