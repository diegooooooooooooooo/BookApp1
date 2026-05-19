package com.example.bookapp.ui.reportes

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.bookapp.R

public class ReporteMensualFragmentDirections private constructor() {
  public companion object {
    public fun actionReporteMensualFragmentToComparacionMensualFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_reporteMensualFragment_to_comparacionMensualFragment)

    public fun actionReporteMensualFragmentToConfiguracionFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_reporteMensualFragment_to_configuracionFragment)
  }
}
