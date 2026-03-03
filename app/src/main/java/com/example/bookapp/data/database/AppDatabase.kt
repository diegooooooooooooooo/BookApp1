package com.example.bookapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bookapp.data.dao.LibroDao
import com.example.bookapp.data.dao.PrestamoDao
import com.example.bookapp.data.dao.UsuarioDao
import com.example.bookapp.data.dao.SocioDao
import com.example.bookapp.data.entities.LibroEntity
import com.example.bookapp.data.entities.PrestamoEntity
import com.example.bookapp.data.entities.UsuarioEntity
import com.example.bookapp.data.entities.SocioEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Base de datos principal de la aplicación utilizando Room.
 */
@Database(entities = [UsuarioEntity::class, LibroEntity::class, PrestamoEntity::class, SocioEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun libroDao(): LibroDao
    abstract fun prestamoDao(): PrestamoDao
    abstract fun socioDao(): SocioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "biblioteca_database"
                )
                .fallbackToDestructiveMigration()
                .addCallback(DatabaseCallback(context))
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        seedDatabase(database.usuarioDao())
                    }
                }
            }

            suspend fun seedDatabase(usuarioDao: UsuarioDao) {
                // Admin por defecto
                val admin = UsuarioEntity(
                    nombre = "Administrador",
                    correo = "admin@booknook.com",
                    contrasena = "admin123",
                    rol = "ADMIN"
                )
                usuarioDao.insert(admin)
                
                // Bibliotecario por defecto
                val bibliotecario = UsuarioEntity(
                    nombre = "Bibliotecario",
                    correo = "biblio@booknook.com",
                    contrasena = "biblio123",
                    rol = "BIBLIOTECARIO"
                )
                usuarioDao.insert(bibliotecario)
            }
        }
    }
}
