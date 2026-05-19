package com.example.bookapp.ui.dashboard

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.bookapp.R
import kotlin.Int

public class DashboardBibliotecarioFragmentDirections private constructor() {
  private data class ActionDashboardBibliotecarioFragmentToRegistrarPrestamoFragment(
    public val libroId: Int = -1,
    public val socioId: Int = -1,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_dashboardBibliotecarioFragment_to_registrarPrestamoFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("libroId", this.libroId)
        result.putInt("socioId", this.socioId)
        return result
      }
  }

  private data class ActionDashboardBibliotecarioFragmentToRegistrarDevolucionFragment(
    public val socioId: Int = -1,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_dashboardBibliotecarioFragment_to_registrarDevolucionFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("socioId", this.socioId)
        return result
      }
  }

  public companion object {
    public fun actionDashboardBibliotecarioFragmentToCatalogoLibrosFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardBibliotecarioFragment_to_catalogoLibrosFragment)

    public fun actionDashboardBibliotecarioFragmentToRegistrarPrestamoFragment(libroId: Int = -1,
        socioId: Int = -1): NavDirections =
        ActionDashboardBibliotecarioFragmentToRegistrarPrestamoFragment(libroId, socioId)

    public fun actionDashboardBibliotecarioFragmentToRegistrarDevolucionFragment(socioId: Int = -1):
        NavDirections = ActionDashboardBibliotecarioFragmentToRegistrarDevolucionFragment(socioId)

    public fun actionDashboardBibliotecarioFragmentToConfiguracionFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardBibliotecarioFragment_to_configuracionFragment)
  }
}
