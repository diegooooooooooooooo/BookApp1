package com.example.bookapp.ui.usuarios

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class PerfilUsuarioFragmentArgs(
  public val usuarioId: Int = -1,
  public val socioId: Int = -1,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("usuarioId", this.usuarioId)
    result.putInt("socioId", this.socioId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("usuarioId", this.usuarioId)
    result.set("socioId", this.socioId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): PerfilUsuarioFragmentArgs {
      bundle.setClassLoader(PerfilUsuarioFragmentArgs::class.java.classLoader)
      val __usuarioId : Int
      if (bundle.containsKey("usuarioId")) {
        __usuarioId = bundle.getInt("usuarioId")
      } else {
        __usuarioId = -1
      }
      val __socioId : Int
      if (bundle.containsKey("socioId")) {
        __socioId = bundle.getInt("socioId")
      } else {
        __socioId = -1
      }
      return PerfilUsuarioFragmentArgs(__usuarioId, __socioId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): PerfilUsuarioFragmentArgs {
      val __usuarioId : Int?
      if (savedStateHandle.contains("usuarioId")) {
        __usuarioId = savedStateHandle["usuarioId"]
        if (__usuarioId == null) {
          throw IllegalArgumentException("Argument \"usuarioId\" of type integer does not support null values")
        }
      } else {
        __usuarioId = -1
      }
      val __socioId : Int?
      if (savedStateHandle.contains("socioId")) {
        __socioId = savedStateHandle["socioId"]
        if (__socioId == null) {
          throw IllegalArgumentException("Argument \"socioId\" of type integer does not support null values")
        }
      } else {
        __socioId = -1
      }
      return PerfilUsuarioFragmentArgs(__usuarioId, __socioId)
    }
  }
}
