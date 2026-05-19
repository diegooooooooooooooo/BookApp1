package com.example.bookapp.ui.dashboard

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.bookapp.R

public class DashboardAdminFragmentDirections private constructor() {
  public companion object {
    public fun actionDashboardAdminFragmentToListaUsuariosFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardAdminFragment_to_listaUsuariosFragment)

    public fun actionDashboardAdminFragmentToReporteMensualFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardAdminFragment_to_reporteMensualFragment)

    public fun actionDashboardAdminFragmentToConfiguracionFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardAdminFragment_to_configuracionFragment)

    public fun actionDashboardAdminFragmentToTopLibrosFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardAdminFragment_to_topLibrosFragment)

    public fun actionDashboardAdminFragmentToListaPendientesFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_dashboardAdminFragment_to_listaPendientesFragment)
  }
}
