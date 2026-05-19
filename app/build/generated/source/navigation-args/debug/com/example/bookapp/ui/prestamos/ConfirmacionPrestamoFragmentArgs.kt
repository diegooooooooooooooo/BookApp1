package com.example.bookapp.ui.prestamos

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.String
import kotlin.jvm.JvmStatic

public data class ConfirmacionPrestamoFragmentArgs(
  public val socioNombre: String,
  public val libroTitulo: String,
  public val fechaLimite: Long,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("socioNombre", this.socioNombre)
    result.putString("libroTitulo", this.libroTitulo)
    result.putLong("fechaLimite", this.fechaLimite)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("socioNombre", this.socioNombre)
    result.set("libroTitulo", this.libroTitulo)
    result.set("fechaLimite", this.fechaLimite)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): ConfirmacionPrestamoFragmentArgs {
      bundle.setClassLoader(ConfirmacionPrestamoFragmentArgs::class.java.classLoader)
      val __socioNombre : String?
      if (bundle.containsKey("socioNombre")) {
        __socioNombre = bundle.getString("socioNombre")
        if (__socioNombre == null) {
          throw IllegalArgumentException("Argument \"socioNombre\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"socioNombre\" is missing and does not have an android:defaultValue")
      }
      val __libroTitulo : String?
      if (bundle.containsKey("libroTitulo")) {
        __libroTitulo = bundle.getString("libroTitulo")
        if (__libroTitulo == null) {
          throw IllegalArgumentException("Argument \"libroTitulo\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"libroTitulo\" is missing and does not have an android:defaultValue")
      }
      val __fechaLimite : Long
      if (bundle.containsKey("fechaLimite")) {
        __fechaLimite = bundle.getLong("fechaLimite")
      } else {
        throw IllegalArgumentException("Required argument \"fechaLimite\" is missing and does not have an android:defaultValue")
      }
      return ConfirmacionPrestamoFragmentArgs(__socioNombre, __libroTitulo, __fechaLimite)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        ConfirmacionPrestamoFragmentArgs {
      val __socioNombre : String?
      if (savedStateHandle.contains("socioNombre")) {
        __socioNombre = savedStateHandle["socioNombre"]
        if (__socioNombre == null) {
          throw IllegalArgumentException("Argument \"socioNombre\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"socioNombre\" is missing and does not have an android:defaultValue")
      }
      val __libroTitulo : String?
      if (savedStateHandle.contains("libroTitulo")) {
        __libroTitulo = savedStateHandle["libroTitulo"]
        if (__libroTitulo == null) {
          throw IllegalArgumentException("Argument \"libroTitulo\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"libroTitulo\" is missing and does not have an android:defaultValue")
      }
      val __fechaLimite : Long?
      if (savedStateHandle.contains("fechaLimite")) {
        __fechaLimite = savedStateHandle["fechaLimite"]
        if (__fechaLimite == null) {
          throw IllegalArgumentException("Argument \"fechaLimite\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"fechaLimite\" is missing and does not have an android:defaultValue")
      }
      return ConfirmacionPrestamoFragmentArgs(__socioNombre, __libroTitulo, __fechaLimite)
    }
  }
}
