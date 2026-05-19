package com.example.bookapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0012R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/bookapp/repository/SocioRepository;", "", "socioDao", "Lcom/example/bookapp/data/dao/SocioDao;", "(Lcom/example/bookapp/data/dao/SocioDao;)V", "allSocios", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/bookapp/data/entities/SocioEntity;", "getAllSocios", "()Lkotlinx/coroutines/flow/Flow;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "sociosCollection", "Lcom/google/firebase/firestore/CollectionReference;", "insertSocio", "", "socio", "(Lcom/example/bookapp/data/entities/SocioEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SocioRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.data.dao.SocioDao socioDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference sociosCollection = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.SocioEntity>> allSocios = null;
    
    public SocioRepository(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.dao.SocioDao socioDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.SocioEntity>> getAllSocios() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertSocio(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.SocioEntity socio, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}