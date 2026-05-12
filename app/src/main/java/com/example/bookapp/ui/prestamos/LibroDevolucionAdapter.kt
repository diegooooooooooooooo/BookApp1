package com.example.bookapp.ui.prestamos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.data.entities.PrestamoConDetalles
import com.example.bookapp.databinding.ItemLibroDevolucionBinding
import java.text.SimpleDateFormat
import java.util.*

class LibroDevolucionAdapter(
    private val onSelectionChanged: (List<PrestamoConDetalles>) -> Unit
) : ListAdapter<PrestamoConDetalles, LibroDevolucionAdapter.ViewHolder>(DiffCallback) {

    private val selectedItems = mutableSetOf<Int>()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val MULTA_DIARIA = 50.0

    inner class ViewHolder(private val binding: ItemLibroDevolucionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(prestamo: PrestamoConDetalles) {
            binding.tvLibroTitulo.text = prestamo.libroTitulo
            binding.tvFechaLimite.text = "Límite: ${dateFormat.format(Date(prestamo.fechaDevolucionEsperada))}"
            
            val hoy = System.currentTimeMillis()
            val multa = calcularMulta(prestamo.fechaDevolucionEsperada, hoy)
            binding.tvMulta.text = "$${String.format("%.2f", multa)}"
            
            binding.cbSeleccionar.setOnCheckedChangeListener(null)
            binding.cbSeleccionar.isChecked = selectedItems.contains(prestamo.id)
            
            binding.cbSeleccionar.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedItems.add(prestamo.id)
                } else {
                    selectedItems.remove(prestamo.id)
                }
                onSelectionChanged(getSelectedPrestamos())
            }
            
            binding.root.setOnClickListener {
                binding.cbSeleccionar.toggle()
            }
        }
    }

    private fun calcularMulta(fechaEsperada: Long, fechaReal: Long): Double {
        if (fechaReal <= fechaEsperada) return 0.0
        val diff = fechaReal - fechaEsperada
        val dias = (diff / (1000 * 60 * 60 * 24)).toInt()
        return dias * MULTA_DIARIA
    }

    fun getSelectedPrestamos(): List<PrestamoConDetalles> {
        return currentList.filter { selectedItems.contains(it.id) }
    }

    fun clearSelection() {
        selectedItems.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLibroDevolucionBinding.inflate(
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
