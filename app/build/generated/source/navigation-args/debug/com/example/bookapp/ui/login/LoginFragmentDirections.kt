package com.example.bookapp.ui.login

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.bookapp.R

public class LoginFragmentDirections private constructor() {
  public companion object {
    public fun actionLoginFragmentToDashboardBibliotecarioFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_dashboardBibliotecarioFragment)

    public fun actionLoginFragmentToDashboardAdminFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_dashboardAdminFragment)

    public fun actionLoginFragmentToDashboardLectorFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_dashboardLectorFragment)

    public fun actionLoginFragmentToSignUpFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_signUpFragment)
  }
}
