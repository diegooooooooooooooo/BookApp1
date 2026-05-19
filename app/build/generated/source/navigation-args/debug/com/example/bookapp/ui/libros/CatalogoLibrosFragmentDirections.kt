package com.example.bookapp.ui.libros

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.bookapp.R
import kotlin.Int

public class CatalogoLibrosFragmentDirections private constructor() {
  private data class ActionCatalogoLibrosFragmentToDetalleLibroFragment(
    public val libroId: Int = -1,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_catalogoLibrosFragment_to_detalleLibroFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("libroId", this.libroId)
        return result
      }
  }

  private data class ActionCatalogoLibrosFragmentToRegistrarLibroFragment(
    public val libroId: Int = -1,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_catalogoLibrosFragment_to_registrarLibroFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("libroId", this.libroId)
        return result
      }
  }

  public companion object {
    public fun actionCatalogoLibrosFragmentToDetalleLibroFragment(libroId: Int = -1): NavDirections
        = ActionCatalogoLibrosFragmentToDetalleLibroFragment(libroId)

    public fun actionCatalogoLibrosFragmentToRegistrarLibroFragment(libroId: Int = -1):
        NavDirections = ActionCatalogoLibrosFragmentToRegistrarLibroFragment(libroId)

    public fun actionCatalogoLibrosFragmentToConfiguracionFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_catalogoLibrosFragment_to_configuracionFragment)
  }
}
