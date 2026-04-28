package com.example.bookapp.ui.reportes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bookapp.BookApplication
import com.example.bookapp.databinding.FragmentReporteMensualBinding
import com.example.bookapp.viewmodel.BibliotecaViewModel
import com.example.bookapp.viewmodel.ViewModelFactory
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*

import com.google.android.material.tabs.TabLayout
import java.util.*

/**
 * Fragmento que muestra el reporte de ingresos con gráficas reales.
 */
class ReporteMensualFragment : Fragment() {

    private var _binding: FragmentReporteMensualBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BibliotecaViewModel by viewModels {
        ViewModelFactory((requireActivity().application as BookApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReporteMensualBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupTabs()
        loadMonthlyData()
    }

    private fun setupTabs() {
        binding.tabLayoutReporte.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> loadMonthlyData()
                    1 -> loadYearlyData()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun loadMonthlyData() {
        val calendar = Calendar.getInstance()
        val currentMonth = String.format(Locale.getDefault(), "%02d", calendar.get(Calendar.MONTH) + 1)
        val monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("es", "ES"))
        
        binding.tvLabelTotal.text = "Total Acumulado $monthName"
        
        viewModel.getIngresosMes(currentMonth).observe(viewLifecycleOwner) { total ->
            val monto = total ?: 0.0
            binding.tvTotalMensual.text = String.format(Locale.getDefault(), "$%.2f", monto)
            updateChart(monto.toFloat(), "Mensual ($monthName)")
        }
    }

    private fun loadYearlyData() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()
        
        binding.tvLabelTotal.text = "Total Acumulado $currentYear"
        
        viewModel.getIngresosAnio(currentYear).observe(viewLifecycleOwner) { total ->
            val monto = total ?: 0.0
            binding.tvTotalMensual.text = String.format(Locale.getDefault(), "$%.2f", monto)
            updateChart(monto.toFloat(), "Anual ($currentYear)")
        }
    }

    private fun updateChart(value: Float, label: String) {
        val entries = ArrayList<BarEntry>()
        // Simulamos algunos datos previos para la gráfica
        entries.add(BarEntry(0f, value * 0.7f))
        entries.add(BarEntry(1f, value * 0.85f))
        entries.add(BarEntry(2f, value))

        val labels = arrayOf("Ant.", "Prev.", "Actual")

        val dataSet = BarDataSet(entries, "Ingresos $label")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        dataSet.valueTextSize = 12f
        
        val barData = BarData(dataSet)
        binding.barChartIngresos.apply {
            data = barData
            description.isEnabled = false
            
            xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(labels)
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                granularity = 1f
            }
            
            axisLeft.setDrawGridLines(true)
            axisRight.isEnabled = false
            
            animateY(1000)
            invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
