package com.example.bookapp.ui.reportes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookapp.databinding.FragmentReporteMensualBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

/**
 * Fragmento que muestra el reporte mensual de ingresos con gráficas.
 */
class ReporteMensualFragment : Fragment() {

    private var _binding: FragmentReporteMensualBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReporteMensualBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart()
    }

    private fun setupChart() {
        // Datos de ejemplo para la gráfica de ingresos mensuales
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1f, 3100f)) // Enero
        entries.add(BarEntry(2f, 2800f)) // Febrero
        entries.add(BarEntry(3f, 4250f)) // Marzo

        val dataSet = BarDataSet(entries, "Ingresos por Mes")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        
        val barData = BarData(dataSet)
        binding.barChartIngresos.data = barData
        binding.barChartIngresos.description.isEnabled = false
        binding.barChartIngresos.animateY(1000)
        binding.barChartIngresos.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
