package com.example.bookapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\bH\u0082@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0013R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/bookapp/repository/LibroRepository;", "", "libroDao", "Lcom/example/bookapp/data/dao/LibroDao;", "(Lcom/example/bookapp/data/dao/LibroDao;)V", "allLibros", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/bookapp/data/entities/LibroEntity;", "getAllLibros", "()Lkotlinx/coroutines/flow/Flow;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "librosCollection", "Lcom/google/firebase/firestore/CollectionReference;", "adjustLibroStatus", "libro", "deleteLibro", "", "(Lcom/example/bookapp/data/entities/LibroEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLibroById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertLibro", "syncLibroToFirestore", "updateLibro", "app_debug"})
public final class LibroRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.data.dao.LibroDao libroDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference librosCollection = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.LibroEntity>> allLibros = null;
    
    public LibroRepository(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.dao.LibroDao libroDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.LibroEntity>> getAllLibros() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertLibro(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.LibroEntity libro, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateLibro(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.LibroEntity libro, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteLibro(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.LibroEntity libro, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final com.example.bookapp.data.entities.LibroEntity adjustLibroStatus(com.example.bookapp.data.entities.LibroEntity libro) {
        return null;
    }
    
    private final java.lang.Object syncLibroToFirestore(com.example.bookapp.data.entities.LibroEntity libro, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLibroById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.bookapp.data.entities.LibroEntity> $completion) {
        return null;
    }
}