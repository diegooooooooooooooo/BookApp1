package com.example.bookapp.ui.prestamos

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.bookapp.R
import kotlin.Int
import kotlin.Long
import kotlin.String

public class RegistrarPrestamoFragmentDirections private constructor() {
  private data class ActionRegistrarPrestamoFragmentToConfirmacionPrestamoFragment(
    public val socioNombre: String,
    public val libroTitulo: String,
    public val fechaLimite: Long,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_registrarPrestamoFragment_to_confirmacionPrestamoFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("socioNombre", this.socioNombre)
        result.putString("libroTitulo", this.libroTitulo)
        result.putLong("fechaLimite", this.fechaLimite)
        return result
      }
  }

  public companion object {
    public fun actionRegistrarPrestamoFragmentToConfirmacionPrestamoFragment(
      socioNombre: String,
      libroTitulo: String,
      fechaLimite: Long,
    ): NavDirections = ActionRegistrarPrestamoFragmentToConfirmacionPrestamoFragment(socioNombre,
        libroTitulo, fechaLimite)

    public fun actionRegistrarPrestamoFragmentToConfiguracionFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_registrarPrestamoFragment_to_configuracionFragment)
  }
}
