package com.example.bookapp.ui.reportes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.data.entities.PrestamoConDetalles
import com.example.bookapp.databinding.ItemHistorialIngresosBinding
import java.util.*

class HistorialIngresosAdapter(private var list: List<PrestamoConDetalles>) :
    RecyclerView.Adapter<HistorialIngresosAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHistorialIngresosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistorialIngresosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val gananciaTotal = item.valorPrestamo + item.multa
        
        holder.binding.apply {
            tvLibroTitulo.text = item.libroTitulo
            tvSocioNombre.text = item.socioNombre
            tvGanancia.text = String.format(Locale.getDefault(), "$%.2f", gananciaTotal)
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<PrestamoConDetalles>) {
        list = newList
        notifyDataSetChanged()
    }
}
