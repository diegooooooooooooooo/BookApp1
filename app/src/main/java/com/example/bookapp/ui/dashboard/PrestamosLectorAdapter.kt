package com.example.bookapp.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.bookapp.R
=======
>>>>>>> 0800574 (Versión más acutual)
=======
import com.example.bookapp.R
>>>>>>> experimental
=======
import com.example.bookapp.R
>>>>>>> 7ae96b5 (Versión más acutual)
import com.example.bookapp.data.entities.PrestamoConDetalles
import com.example.bookapp.databinding.ItemPrestamoLectorBinding
import java.text.SimpleDateFormat
import java.util.*

<<<<<<< HEAD
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
=======
class PrestamosLectorAdapter(
    private val onLibroClick: (Int) -> Unit
) : ListAdapter<PrestamoConDetalles, PrestamosLectorAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(private val binding: ItemPrestamoLectorBinding) : RecyclerView.ViewHolder(binding.root) {
>>>>>>> 7ae96b5 (Versión más acutual)
        private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        fun bind(prestamo: PrestamoConDetalles) {
            binding.tvLibroTitulo.text = prestamo.libroTitulo
            binding.tvFechaDevolucion.text = dateFormat.format(Date(prestamo.fechaDevolucionEsperada))
            
            val hoy = System.currentTimeMillis()
            if (prestamo.fechaEntregaReal != null) {
                binding.tvEstado.text = "Devuelto"
<<<<<<< HEAD
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
=======
                binding.tvEstado.setChipBackgroundColorResource(android.R.color.holo_green_light)
                binding.tvEstado.setTextColor(binding.root.context.getColor(android.R.color.black))
>>>>>>> 7ae96b5 (Versión más acutual)
            } else if (hoy > prestamo.fechaDevolucionEsperada) {
                binding.tvEstado.text = "Atrasado"
                binding.tvEstado.setChipBackgroundColorResource(android.R.color.holo_red_light)
                binding.tvEstado.setTextColor(binding.root.context.getColor(android.R.color.black))
            } else {
                binding.tvEstado.text = "Pendiente"
<<<<<<< HEAD
                binding.tvEstado.setTextColor(binding.root.context.getColor(android.R.color.holo_orange_dark))
>>>>>>> 0800574 (Versión más acutual)
=======
>>>>>>> experimental
=======
                binding.tvEstado.setChipBackgroundColorResource(R.color.primary_light_brown)
                binding.tvEstado.setTextColor(binding.root.context.getColor(android.R.color.black))
            }

            binding.root.setOnClickListener {
                onLibroClick(prestamo.libroId)
>>>>>>> 7ae96b5 (Versión más acutual)
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
