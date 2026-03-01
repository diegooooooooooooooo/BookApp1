package com.example.bookapp.ui.reportes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookapp.databinding.FragmentComparacionMensualBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

/**
 * Fragmento que muestra la comparación de ingresos entre el mes actual y el anterior.
 * Incluye el cálculo del porcentaje de crecimiento y una gráfica comparativa.
 */
class ComparacionMensualFragment : Fragment() {

    private var _binding: FragmentComparacionMensualBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComparacionMensualBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupComparisonChart()
    }

    private fun setupComparisonChart() {
        // Datos representativos (Febrero vs Marzo)
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 3100f)) // Mes Anterior (Febrero)
        entries.add(BarEntry(1f, 4250f)) // Mes Actual (Marzo)

        val dataSet = BarDataSet(entries, "Comparativa de Ventas")
        dataSet.colors = ColorTemplate.JOYFUL_COLORS.toList()
        
        val barData = BarData(dataSet)
        binding.barChartComparativo.data = barData
        binding.barChartComparativo.description.isEnabled = false
        binding.barChartComparativo.xAxis.isEnabled = false // Ocultar eje X para simplicidad
        binding.barChartComparativo.animateY(1000)
        binding.barChartComparativo.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
