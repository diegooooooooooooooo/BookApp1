package com.example.bookapp.ui.reportes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.data.dao.PrestamoDao.LibroConConteo
import com.example.bookapp.databinding.ItemLibroTopBinding

class TopLibrosAdapter(private var list: List<LibroConConteo>) :
    RecyclerView.Adapter<TopLibrosAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemLibroTopBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLibroTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            tvRank.text = "#${position + 1}"
            tvTitulo.text = item.libro.titulo
            tvAutor.text = item.libro.autor
            tvCount.text = "${item.prestamosCount} préstamos"
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<LibroConConteo>) {
        list = newList
        notifyDataSetChanged()
    }
}
