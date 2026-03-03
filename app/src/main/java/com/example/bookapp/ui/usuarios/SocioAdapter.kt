package com.example.bookapp.ui.usuarios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.data.entities.SocioEntity
import com.example.bookapp.databinding.ItemUsuarioBinding

class SocioAdapter(private val onSocioClick: (SocioEntity) -> Unit) :
    ListAdapter<SocioEntity, SocioAdapter.SocioViewHolder>(SocioDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocioViewHolder {
        val binding = ItemUsuarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SocioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SocioViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SocioViewHolder(private val binding: ItemUsuarioBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(socio: SocioEntity) {
            binding.tvUserName.text = socio.nombre
            binding.tvUserEmail.text = socio.correo
            binding.root.setOnClickListener { onSocioClick(socio) }
        }
    }

    class SocioDiffCallback : DiffUtil.ItemCallback<SocioEntity>() {
        override fun areItemsTheSame(oldItem: SocioEntity, newItem: SocioEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SocioEntity, newItem: SocioEntity): Boolean {
            return oldItem == newItem
        }
    }
}
