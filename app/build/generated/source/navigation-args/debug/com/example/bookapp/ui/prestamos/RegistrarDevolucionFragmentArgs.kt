package com.example.bookapp.ui.prestamos

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class RegistrarDevolucionFragmentArgs(
  public val socioId: Int = -1,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("socioId", this.socioId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("socioId", this.socioId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): RegistrarDevolucionFragmentArgs {
      bundle.setClassLoader(RegistrarDevolucionFragmentArgs::class.java.classLoader)
      val __socioId : Int
      if (bundle.containsKey("socioId")) {
        __socioId = bundle.getInt("socioId")
      } else {
        __socioId = -1
      }
      return RegistrarDevolucionFragmentArgs(__socioId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        RegistrarDevolucionFragmentArgs {
      val __socioId : Int?
      if (savedStateHandle.contains("socioId")) {
        __socioId = savedStateHandle["socioId"]
        if (__socioId == null) {
          throw IllegalArgumentException("Argument \"socioId\" of type integer does not support null values")
        }
      } else {
        __socioId = -1
      }
      return RegistrarDevolucionFragmentArgs(__socioId)
    }
  }
}
