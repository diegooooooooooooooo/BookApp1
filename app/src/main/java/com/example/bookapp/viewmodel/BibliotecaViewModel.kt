package com.example.bookapp.viewmodel

import androidx.lifecycle.*
import com.example.bookapp.data.entities.LibroEntity
import com.example.bookapp.data.entities.SocioEntity
import com.example.bookapp.data.entities.PrestamoEntity
import com.example.bookapp.repository.BibliotecaRepository
import kotlinx.coroutines.launch

class BibliotecaViewModel(private val repository: BibliotecaRepository) : ViewModel() {

    val allLibros: LiveData<List<LibroEntity>> = repository.allLibros.asLiveData()
    val allSocios: LiveData<List<SocioEntity>> = repository.allSocios.asLiveData()
    val prestamosActivos: LiveData<List<PrestamoEntity>> = repository.prestamosActivos.asLiveData()
    val prestamosActivosConDetalles: LiveData<List<com.example.bookapp.data.entities.PrestamoConDetalles>> = 
        repository.prestamosActivosConDetalles.asLiveData()
    val top5LibrosMasPrestados = repository.getTop5Libros().asLiveData()

    init {
        // Sincronización inicial al abrir la app
        syncFromFirestore()
    }

    fun syncFromFirestore() = viewModelScope.launch {
        repository.syncFromFirestore()
    }

    fun insertLibro(libro: LibroEntity) = viewModelScope.launch {
        repository.insertLibro(libro)
    }

    fun updateLibro(libro: LibroEntity) = viewModelScope.launch {
        repository.updateLibro(libro)
    }

    fun deleteLibro(libro: LibroEntity) = viewModelScope.launch {
        repository.deleteLibro(libro)
    }

    fun insertSocio(socio: SocioEntity) = viewModelScope.launch {
        repository.insertSocio(socio)
    }

    fun registrarPrestamo(prestamo: PrestamoEntity) = viewModelScope.launch {
        repository.registrarPrestamo(prestamo)
    }

    fun registrarDevolucion(prestamo: PrestamoEntity) = viewModelScope.launch {
        repository.registrarDevolucion(prestamo)
    }

    suspend fun getPrestamoById(id: Int): PrestamoEntity? = repository.getPrestamoById(id)

    fun getIngresosMes(mes: String): LiveData<Double?> = repository.getIngresosPorMes(mes).asLiveData()
    
    fun getIngresosAnio(anio: String): LiveData<Double?> = repository.getIngresosPorAnio(anio).asLiveData()
    
    fun getPrestamosMes(mes: String): LiveData<List<PrestamoEntity>> = repository.getPrestamosPorMes(mes).asLiveData()
}
