package com.example.bookapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bJ\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u00062\u0006\u0010\u001e\u001a\u00020\u001fJ\u0016\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u00062\u0006\u0010!\u001a\u00020\u001fJ\u0018\u0010\"\u001a\u0004\u0018\u00010\u00122\u0006\u0010#\u001a\u00020$H\u0086@\u00a2\u0006\u0002\u0010%J\u001a\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u00062\u0006\u0010!\u001a\u00020\u001fJ\u000e\u0010\'\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bJ\u000e\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020\fJ\u000e\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u0012J\u000e\u0010,\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u0012J\u0006\u0010-\u001a\u00020\u001aJ\u000e\u0010.\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\nR\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\n\u00a8\u0006/"}, d2 = {"Lcom/example/bookapp/viewmodel/BibliotecaViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/bookapp/repository/BibliotecaRepository;", "(Lcom/example/bookapp/repository/BibliotecaRepository;)V", "allLibros", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/bookapp/data/entities/LibroEntity;", "getAllLibros", "()Landroidx/lifecycle/LiveData;", "allSocios", "Lcom/example/bookapp/data/entities/SocioEntity;", "getAllSocios", "historialPrestamos", "Lcom/example/bookapp/data/entities/PrestamoConDetalles;", "getHistorialPrestamos", "prestamosActivos", "Lcom/example/bookapp/data/entities/PrestamoEntity;", "getPrestamosActivos", "prestamosActivosConDetalles", "getPrestamosActivosConDetalles", "top5LibrosMasPrestados", "Lcom/example/bookapp/data/dao/PrestamoDao$LibroConConteo;", "getTop5LibrosMasPrestados", "deleteLibro", "Lkotlinx/coroutines/Job;", "libro", "getIngresosAnio", "", "anio", "", "getIngresosMes", "mes", "getPrestamoById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPrestamosMes", "insertLibro", "insertSocio", "socio", "registrarDevolucion", "prestamo", "registrarPrestamo", "syncFromFirestore", "updateLibro", "app_release"})
public final class BibliotecaViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.repository.BibliotecaRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.entities.LibroEntity>> allLibros = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.entities.SocioEntity>> allSocios = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> prestamosActivos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> prestamosActivosConDetalles = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.dao.PrestamoDao.LibroConConteo>> top5LibrosMasPrestados = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> historialPrestamos = null;
    
    public BibliotecaViewModel(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.repository.BibliotecaRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.entities.LibroEntity>> getAllLibros() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.entities.SocioEntity>> getAllSocios() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> getPrestamosActivos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> getPrestamosActivosConDetalles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.dao.PrestamoDao.LibroConConteo>> getTop5LibrosMasPrestados() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> getHistorialPrestamos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job syncFromFirestore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insertLibro(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.LibroEntity libro) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job updateLibro(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.LibroEntity libro) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteLibro(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.LibroEntity libro) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insertSocio(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.SocioEntity socio) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job registrarPrestamo(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.PrestamoEntity prestamo) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job registrarDevolucion(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.PrestamoEntity prestamo) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPrestamoById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.bookapp.data.entities.PrestamoEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getIngresosMes(@org.jetbrains.annotations.NotNull()
    java.lang.String mes) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getIngresosAnio(@org.jetbrains.annotations.NotNull()
    java.lang.String anio) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> getPrestamosMes(@org.jetbrains.annotations.NotNull()
    java.lang.String mes) {
        return null;
    }
}