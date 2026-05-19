package com.example.bookapp.ui.libros

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.bookapp.R
import kotlin.Int

public class LibroDetalleFragmentDirections private constructor() {
  private data class ActionDetalleLibroFragmentToRegistrarPrestamoFragment(
    public val libroId: Int = -1,
    public val socioId: Int = -1,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_detalleLibroFragment_to_registrarPrestamoFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("libroId", this.libroId)
        result.putInt("socioId", this.socioId)
        return result
      }
  }

  public companion object {
    public fun actionDetalleLibroFragmentToRegistrarPrestamoFragment(libroId: Int = -1, socioId: Int
        = -1): NavDirections = ActionDetalleLibroFragmentToRegistrarPrestamoFragment(libroId,
        socioId)
  }
}
