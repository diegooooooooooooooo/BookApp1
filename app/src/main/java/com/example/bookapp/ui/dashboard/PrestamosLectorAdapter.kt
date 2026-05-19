package com.example.bookapp.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.bookapp.R
=======
>>>>>>> 0800574 (Versión más acutual)
=======
import com.example.bookapp.R
>>>>>>> experimental
import com.example.bookapp.data.entities.PrestamoConDetalles
import com.example.bookapp.databinding.ItemPrestamoLectorBinding
import java.text.SimpleDateFormat
import java.util.*

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
class PrestamosLectorAdapter(
    private val onLibroClick: (Int) -> Unit
) : ListAdapter<PrestamoConDetalles, PrestamosLectorAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(private val binding: ItemPrestamoLectorBinding) : RecyclerView.ViewHolder(binding.root) {
<<<<<<< HEAD
=======
class PrestamosLectorAdapter : ListAdapter<PrestamoConDetalles, PrestamosLectorAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: ItemPrestamoLectorBinding) : RecyclerView.ViewHolder(binding.root) {
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
        private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        fun bind(prestamo: PrestamoConDetalles) {
            binding.tvLibroTitulo.text = prestamo.libroTitulo
            binding.tvFechaDevolucion.text = dateFormat.format(Date(prestamo.fechaDevolucionEsperada))
            
            val hoy = System.currentTimeMillis()
            if (prestamo.fechaEntregaReal != null) {
                binding.tvEstado.text = "Devuelto"
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> experimental
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
<<<<<<< HEAD
=======
                binding.tvEstado.setTextColor(binding.root.context.getColor(android.R.color.holo_green_dark))
            } else if (hoy > prestamo.fechaDevolucionEsperada) {
                binding.tvEstado.text = "Atrasado"
                binding.tvEstado.setTextColor(binding.root.context.getColor(android.R.color.holo_red_dark))
            } else {
                binding.tvEstado.text = "Pendiente"
                binding.tvEstado.setTextColor(binding.root.context.getColor(android.R.color.holo_orange_dark))
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
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
