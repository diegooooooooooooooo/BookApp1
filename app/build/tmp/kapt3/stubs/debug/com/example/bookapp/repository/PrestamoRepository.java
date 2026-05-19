package com.example.bookapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\t0\bJ\u0016\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\b2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\b2\u0006\u0010\u001d\u001a\u00020\u001bJ\u0018\u0010\u001e\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001f\u001a\u00020 H\u0086@\u00a2\u0006\u0002\u0010!J\u001a\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\t0\b2\u0006\u0010#\u001a\u00020 J\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\t0\b2\u0006\u0010%\u001a\u00020\u001bJ\u001a\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u001d\u001a\u00020\u001bJ\u0012\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\t0\bJ\u0016\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010,J$\u0010-\u001a\b\u0012\u0004\u0012\u00020*0.2\u0006\u0010+\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b/\u0010,J\u0016\u00100\u001a\u00020*2\u0006\u00101\u001a\u000202H\u0082@\u00a2\u0006\u0002\u00103J\u0016\u00104\u001a\u00020*2\u0006\u0010+\u001a\u00020\nH\u0082@\u00a2\u0006\u0002\u0010,R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\fR\u000e\u0010\u0016\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00065"}, d2 = {"Lcom/example/bookapp/repository/PrestamoRepository;", "", "prestamoDao", "Lcom/example/bookapp/data/dao/PrestamoDao;", "libroDao", "Lcom/example/bookapp/data/dao/LibroDao;", "(Lcom/example/bookapp/data/dao/PrestamoDao;Lcom/example/bookapp/data/dao/LibroDao;)V", "allPrestamos", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/bookapp/data/entities/PrestamoEntity;", "getAllPrestamos", "()Lkotlinx/coroutines/flow/Flow;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "librosCollection", "Lcom/google/firebase/firestore/CollectionReference;", "prestamosActivos", "getPrestamosActivos", "prestamosActivosConDetalles", "Lcom/example/bookapp/data/entities/PrestamoConDetalles;", "getPrestamosActivosConDetalles", "prestamosCollection", "getHistorialPrestamosConDetalles", "getIngresosPorAnio", "", "anio", "", "getIngresosPorMes", "mes", "getPrestamoById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPrestamosActivosPorSocio", "socioId", "getPrestamosPorCorreoSocio", "correo", "getPrestamosPorMes", "getTop5Libros", "Lcom/example/bookapp/data/dao/PrestamoDao$LibroConConteo;", "registrarDevolucion", "", "prestamo", "(Lcom/example/bookapp/data/entities/PrestamoEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registrarPrestamo", "Lkotlin/Result;", "registrarPrestamo-gIAlu-s", "syncLibroToFirestore", "libro", "Lcom/example/bookapp/data/entities/LibroEntity;", "(Lcom/example/bookapp/data/entities/LibroEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncPrestamoToFirestore", "app_debug"})
public final class PrestamoRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.data.dao.PrestamoDao prestamoDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.data.dao.LibroDao libroDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference prestamosCollection = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference librosCollection = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> allPrestamos = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> prestamosActivos = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> prestamosActivosConDetalles = null;
    
    public PrestamoRepository(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.dao.PrestamoDao prestamoDao, @org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.dao.LibroDao libroDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> getAllPrestamos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> getPrestamosActivos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> getPrestamosActivosConDetalles() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object registrarDevolucion(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.PrestamoEntity prestamo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object syncLibroToFirestore(com.example.bookapp.data.entities.LibroEntity libro, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object syncPrestamoToFirestore(com.example.bookapp.data.entities.PrestamoEntity prestamo, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPrestamoById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.bookapp.data.entities.PrestamoEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> getHistorialPrestamosConDetalles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Double> getIngresosPorMes(@org.jetbrains.annotations.NotNull()
    java.lang.String mes) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Double> getIngresosPorAnio(@org.jetbrains.annotations.NotNull()
    java.lang.String anio) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> getPrestamosPorMes(@org.jetbrains.annotations.NotNull()
    java.lang.String mes) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> getPrestamosPorCorreoSocio(@org.jetbrains.annotations.NotNull()
    java.lang.String correo) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> getPrestamosActivosPorSocio(int socioId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.dao.PrestamoDao.LibroConConteo>> getTop5Libros() {
        return null;
    }
}