package com.example.bookapp.ui.libros

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.R
import com.example.bookapp.data.entities.LibroEntity
import com.example.bookapp.databinding.ItemLibroBinding

class LibroAdapter(private val onLibroClick: (LibroEntity) -> Unit) :
    ListAdapter<LibroEntity, LibroAdapter.LibroViewHolder>(LibroDiffCallback()) {

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
            
            if (libro.disponible) {
                binding.chipEstado.text = "Disponible"
                binding.chipEstado.setChipBackgroundColorResource(android.R.color.holo_green_light)
            } else {
                binding.chipEstado.text = "Prestado"
                binding.chipEstado.setChipBackgroundColorResource(android.R.color.holo_red_light)
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
