package com.example.bookapp.ui.prestamos

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.bookapp.R

public class ConfirmacionPrestamoFragmentDirections private constructor() {
  public companion object {
    public fun actionConfirmacionPrestamoFragmentToDashboardLectorFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_confirmacionPrestamoFragment_to_dashboardLectorFragment)

    public fun actionConfirmacionPrestamoFragmentToDashboardBibliotecarioFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_confirmacionPrestamoFragment_to_dashboardBibliotecarioFragment)

    public fun actionConfirmacionPrestamoFragmentToDashboardAdminFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_confirmacionPrestamoFragment_to_dashboardAdminFragment)
  }
}
