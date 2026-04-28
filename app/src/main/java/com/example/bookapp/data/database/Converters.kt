package com.example.bookapp.data.database

import androidx.room.TypeConverter
import com.example.bookapp.data.entities.LibroEstado

class Converters {
    @TypeConverter
    fun fromLibroEstado(value: LibroEstado): String {
        return value.name
    }

    @TypeConverter
    fun toLibroEstado(value: String): LibroEstado {
        return LibroEstado.valueOf(value)
    }
}
