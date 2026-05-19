package com.example.bookapp.ui.libros

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class RegistrarLibroFragmentArgs(
  public val libroId: Int = -1,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("libroId", this.libroId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("libroId", this.libroId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): RegistrarLibroFragmentArgs {
      bundle.setClassLoader(RegistrarLibroFragmentArgs::class.java.classLoader)
      val __libroId : Int
      if (bundle.containsKey("libroId")) {
        __libroId = bundle.getInt("libroId")
      } else {
        __libroId = -1
      }
      return RegistrarLibroFragmentArgs(__libroId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        RegistrarLibroFragmentArgs {
      val __libroId : Int?
      if (savedStateHandle.contains("libroId")) {
        __libroId = savedStateHandle["libroId"]
        if (__libroId == null) {
          throw IllegalArgumentException("Argument \"libroId\" of type integer does not support null values")
        }
      } else {
        __libroId = -1
      }
      return RegistrarLibroFragmentArgs(__libroId)
    }
  }
}
