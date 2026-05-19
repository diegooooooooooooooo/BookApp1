package com.example.bookapp.ui.usuarios

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.bookapp.R
import kotlin.Int

public class ListaUsuariosFragmentDirections private constructor() {
  private data class ActionListaUsuariosFragmentToPerfilUsuarioFragment(
    public val usuarioId: Int = -1,
    public val socioId: Int = -1,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_listaUsuariosFragment_to_perfilUsuarioFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("usuarioId", this.usuarioId)
        result.putInt("socioId", this.socioId)
        return result
      }
  }

  public companion object {
    public fun actionListaUsuariosFragmentToRegistrarUsuarioFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_listaUsuariosFragment_to_registrarUsuarioFragment)

    public fun actionListaUsuariosFragmentToRegistrarSocioFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_listaUsuariosFragment_to_registrarSocioFragment)

    public fun actionListaUsuariosFragmentToPerfilUsuarioFragment(usuarioId: Int = -1, socioId: Int
        = -1): NavDirections = ActionListaUsuariosFragmentToPerfilUsuarioFragment(usuarioId,
        socioId)

    public fun actionListaUsuariosFragmentToConfiguracionFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_listaUsuariosFragment_to_configuracionFragment)
  }
}
