package com.example.bookapp.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.R
import com.example.bookapp.data.entities.PrestamoConDetalles
import com.example.bookapp.databinding.ItemPrestamoLectorBinding
import java.text.SimpleDateFormat
import java.util.*

class PrestamosLectorAdapter(
    private val onLibroClick: (Int) -> Unit
) : ListAdapter<PrestamoConDetalles, PrestamosLectorAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(private val binding: ItemPrestamoLectorBinding) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        fun bind(prestamo: PrestamoConDetalles) {
            binding.tvLibroTitulo.text = prestamo.libroTitulo
            binding.tvFechaDevolucion.text = dateFormat.format(Date(prestamo.fechaDevolucionEsperada))
            
            val hoy = System.currentTimeMillis()
            if (prestamo.fechaEntregaReal != null) {
                binding.tvEstado.text = "Devuelto"
                binding.tvEstado.setChipBackgroundColorResource(android.R.color.holo_green_light)
                binding.tvEstado.setTextColor(binding.root.context.getColor(android.R.color.black))
            } else if (hoy > prestamo.fechaDevolucionEsperada) {
                binding.tvEstado.text = "Atrasado"
                binding.tvEstado.setChipBackgroundColorResource(android.R.color.holo_red_light)
                binding.tvEstado.setTextColor(binding.root.context.getColor(android.R.color.black))
            } else {
                binding.tvEstado.text = "Pendiente"
                binding.tvEstado.setChipBackgroundColorResource(R.color.primary_light_brown)
                binding.tvEstado.setTextColor(binding.root.context.getColor(android.R.color.black))
            }

            binding.root.setOnClickListener {
                onLibroClick(prestamo.libroId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPrestamoLectorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PrestamoConDetalles>() {
        override fun areItemsTheSame(oldItem: PrestamoConDetalles, newItem: PrestamoConDetalles): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PrestamoConDetalles, newItem: PrestamoConDetalles): Boolean {
            return oldItem == newItem
        }
    }
}
