package com.example.bookapp.ui.reportes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookapp.BookApplication
import com.example.bookapp.databinding.FragmentTopLibrosBinding
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory

class TopLibrosFragment : Fragment() {

    private var _binding: FragmentTopLibrosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BibliotecaViewModel by activityViewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    private lateinit var adapter: TopLibrosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopLibrosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TopLibrosAdapter(emptyList())
        binding.rvTopLibros.layoutManager = LinearLayoutManager(context)
        binding.rvTopLibros.adapter = adapter

        viewModel.top5LibrosMasPrestados.observe(viewLifecycleOwner) { list ->
            adapter.updateList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
