package com.example.bookapp.ui.configuracion

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.bookapp.R

public class ConfiguracionFragmentDirections private constructor() {
  public companion object {
    public fun actionConfiguracionFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_configuracionFragment_to_loginFragment)

    public fun actionConfiguracionFragmentToMiPerfilFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_configuracionFragment_to_miPerfilFragment)
  }
}
