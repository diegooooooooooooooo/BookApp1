package com.example.bookapp.ui.dashboard

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.bookapp.R
import kotlin.Int

public class DashboardLectorFragmentDirections private constructor() {
  private data class ActionDashboardLectorFragmentToDetalleLibroFragment(
    public val libroId: Int = -1,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_dashboardLectorFragment_to_detalleLibroFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("libroId", this.libroId)
        return result
      }
  }

  public companion object {
    public fun actionDashboardLectorFragmentToCatalogoLibrosFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardLectorFragment_to_catalogoLibrosFragment)

    public fun actionDashboardLectorFragmentToCalendarioPrestamosFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardLectorFragment_to_calendarioPrestamosFragment)

    public fun actionDashboardLectorFragmentToConfiguracionFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardLectorFragment_to_configuracionFragment)

    public fun actionDashboardLectorFragmentToDetalleLibroFragment(libroId: Int = -1): NavDirections
        = ActionDashboardLectorFragmentToDetalleLibroFragment(libroId)
  }
}
