package com.example.bookapp.viewmodel

import androidx.lifecycle.*
import com.example.bookapp.data.entities.LibroEntity
import com.example.bookapp.data.entities.LibroEstado
import com.example.bookapp.data.entities.SocioEntity
import com.example.bookapp.data.entities.PrestamoEntity
import com.example.bookapp.repository.BibliotecaRepository
import com.example.bookapp.data.api.WorkDocument
import kotlinx.coroutines.launch

class BibliotecaViewModel(private val repository: BibliotecaRepository) : ViewModel() {

    private val _onlineSearchResults = MutableLiveData<List<WorkDocument>>()
    private val onlineSearchResults: LiveData<List<WorkDocument>> = _onlineSearchResults

    private val _isSearchingOnline = MutableLiveData<Boolean>()
    val isSearchingOnline: LiveData<Boolean> = _isSearchingOnline

    private val _prestamoResult = MutableLiveData<Result<Unit>?>()
    val prestamoResult: LiveData<Result<Unit>?> = _prestamoResult

    private val localLibros: LiveData<List<LibroEntity>> = repository.allLibros.asLiveData()
    
    val allLibrosCombined = MediatorLiveData<List<LibroEntity>>().apply {
        addSource(localLibros) { local ->
            value = combineLibros(local, _onlineSearchResults.value ?: emptyList())
        }
        addSource(_onlineSearchResults) { online ->
            value = combineLibros(localLibros.value ?: emptyList(), online)
        }
    }

    private fun combineLibros(local: List<LibroEntity>, online: List<WorkDocument>): List<LibroEntity> {
        val onlineEntities = online.map { it.toLibroEntity() }
        // Merge lists, putting local books first
        return local + onlineEntities
    }

    private fun WorkDocument.toLibroEntity(): LibroEntity {
        return LibroEntity(
            id = this.key.hashCode(), // Temporary ID for RecyclerView
            titulo = this.title,
            autor = this.authorNames?.joinToString(", ") ?: "Autor Desconocido",
            isbn = this.isbn?.firstOrNull() ?: "",
            editorial = this.publisher?.firstOrNull() ?: "",
            portadaUrl = this.coverId?.let { "https://covers.openlibrary.org/b/id/$it-M.jpg" },
            estado = LibroEstado.DISPONIBLE,
            isPlaceholder = true
        )
    }

    val allLibros: LiveData<List<LibroEntity>> = localLibros
    val allSocios: LiveData<List<SocioEntity>> = repository.allSocios.asLiveData()
    val prestamosActivos: LiveData<List<PrestamoEntity>> = repository.prestamosActivos.asLiveData()
    
    val prestamosActivosConDetalles: LiveData<List<com.example.bookapp.data.entities.PrestamoConDetalles>> = 
        repository.prestamosActivosConDetalles.asLiveData()

    // Performance Optimization: Perform mapping/filtering in ViewModel, not in Fragment
    val prestamosPendientesCalculados: LiveData<List<com.example.bookapp.data.model.PrestamoPendiente>> = 
        prestamosActivosConDetalles.map { prestamos ->
            val currentTime = System.currentTimeMillis()
            prestamos.filter { it.fechaDevolucionEsperada < currentTime }
                .map { prestamo ->
                    val diasRetraso = ((currentTime - prestamo.fechaDevolucionEsperada) / (1000 * 60 * 60 * 24)).toInt()
                    com.example.bookapp.data.model.PrestamoPendiente(
                        socioNombre = prestamo.socioNombre,
                        libroTitulo = prestamo.libroTitulo,
                        fechaVencimiento = prestamo.fechaDevolucionEsperada,
                        diasRetraso = diasRetraso
                    )
                }
        }

    val top5LibrosMasPrestados = repository.getTop5Libros().asLiveData()
    val historialPrestamos = repository.getHistorialPrestamosConDetalles().asLiveData()

    init {
        // Sincronización inicial al abrir la app
        syncFromFirestore()
        // Load some initial placeholder books to simulate a larger database
        searchOnline("Classic Books")
    }

    fun syncFromFirestore() = viewModelScope.launch {
        repository.syncFromFirestore()
    }

    fun searchOnline(query: String) = viewModelScope.launch {
        if (query.isBlank()) {
            _onlineSearchResults.value = emptyList()
            return@launch
        }
        
        _isSearchingOnline.value = true
        try {
            val response = repository.searchOnline(query)
            _onlineSearchResults.value = response.docs
        } catch (e: Exception) {
            e.printStackTrace()
            _onlineSearchResults.value = emptyList()
        } finally {
            _isSearchingOnline.value = false
        }
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

    fun registrarPrestamo(prestamo: PrestamoEntity, libroParaImportar: LibroEntity? = null) = viewModelScope.launch {
        _prestamoResult.value = null // Reset previous state
        
        try {
            val prestamoFinal = if (libroParaImportar != null && libroParaImportar.isPlaceholder) {
                // First, import the book to the local database
                val libroReal = libroParaImportar.copy(
                    id = 0, // Let Room generate a real ID
                    isPlaceholder = false,
                    ejemplares = 5, // Default stock for imported books
                    estado = LibroEstado.DISPONIBLE
                )
                val newId = repository.insertLibro(libroReal)
                prestamo.copy(libroId = newId)
            } else {
                prestamo
            }

            val result = repository.registrarPrestamo(prestamoFinal)
            _prestamoResult.postValue(result)
        } catch (e: Exception) {
            _prestamoResult.postValue(Result.failure(e))
        }
    }

    fun clearPrestamoResult() {
        _prestamoResult.value = null
    }

    fun registrarDevolucion(prestamo: PrestamoEntity) = viewModelScope.launch {
        repository.registrarDevolucion(prestamo)
    }

    suspend fun getPrestamoById(id: Int): PrestamoEntity? = repository.getPrestamoById(id)

    fun getPrestamosActivosPorSocio(socioId: Int): LiveData<List<com.example.bookapp.data.entities.PrestamoConDetalles>> =
        repository.getPrestamosActivosPorSocio(socioId).asLiveData()

    fun getPrestamosPorCorreoSocio(correo: String): LiveData<List<com.example.bookapp.data.entities.PrestamoConDetalles>> =
        repository.getPrestamosPorCorreoSocio(correo).asLiveData()

    fun getIngresosMes(mes: String): LiveData<Double?> = repository.getIngresosPorMes(mes).asLiveData()
    
    fun getIngresosAnio(anio: String): LiveData<Double?> = repository.getIngresosPorAnio(anio).asLiveData()
    
    fun getPrestamosMes(mes: String): LiveData<List<PrestamoEntity>> = repository.getPrestamosPorMes(mes).asLiveData()
}
