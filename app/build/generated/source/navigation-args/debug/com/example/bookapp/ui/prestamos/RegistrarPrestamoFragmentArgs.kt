package com.example.bookapp.ui.prestamos

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class RegistrarPrestamoFragmentArgs(
  public val libroId: Int = -1,
  public val socioId: Int = -1,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("libroId", this.libroId)
    result.putInt("socioId", this.socioId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("libroId", this.libroId)
    result.set("socioId", this.socioId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): RegistrarPrestamoFragmentArgs {
      bundle.setClassLoader(RegistrarPrestamoFragmentArgs::class.java.classLoader)
      val __libroId : Int
      if (bundle.containsKey("libroId")) {
        __libroId = bundle.getInt("libroId")
      } else {
        __libroId = -1
      }
      val __socioId : Int
      if (bundle.containsKey("socioId")) {
        __socioId = bundle.getInt("socioId")
      } else {
        __socioId = -1
      }
      return RegistrarPrestamoFragmentArgs(__libroId, __socioId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        RegistrarPrestamoFragmentArgs {
      val __libroId : Int?
      if (savedStateHandle.contains("libroId")) {
        __libroId = savedStateHandle["libroId"]
        if (__libroId == null) {
          throw IllegalArgumentException("Argument \"libroId\" of type integer does not support null values")
        }
      } else {
        __libroId = -1
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
      return RegistrarPrestamoFragmentArgs(__libroId, __socioId)
    }
  }
}
