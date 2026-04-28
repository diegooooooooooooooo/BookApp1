package com.example.bookapp.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.data.model.PrestamoPendiente
import com.example.bookapp.databinding.ItemPrestamoPendienteBinding

class PrestamosPendientesAdapter(private val items: List<PrestamoPendiente>) : 
    RecyclerView.Adapter<PrestamosPendientesAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPrestamoPendienteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPrestamoPendienteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvSocioName.text = item.socioNombre
            tvLibroPrestado.text = item.libroTitulo
            
            val fechaFormateada = android.text.format.DateFormat.format("dd/MM/yyyy", item.fechaVencimiento)
            tvFechaVencimiento.text = "Vencimiento: $fechaFormateada"
            
            badgeRetraso.text = "${item.diasRetraso} días de retraso"
            
            // Iniciales
            val initials = item.socioNombre.split(" ")
                .filter { it.isNotEmpty() }
                .take(2)
                .map { it[0] }
                .joinToString("")
                .uppercase()
            tvUserInitials.text = initials
        }
    }

    override fun getItemCount() = items.size
}
