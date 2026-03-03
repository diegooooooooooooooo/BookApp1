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

    fun insertLibro(libro: LibroEntity) = viewModelScope.launch {
        repository.insertLibro(libro)
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

    fun getIngresosMes(mes: String): LiveData<Double?> = repository.getMultasPorMes(mes).asLiveData()
    
    fun getPrestamosMes(mes: String): LiveData<List<PrestamoEntity>> = repository.getPrestamosPorMes(mes).asLiveData()
}
