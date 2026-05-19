package com.example.bookapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0082@\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010\u001b\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/example/bookapp/repository/UsuarioRepository;", "", "usuarioDao", "Lcom/example/bookapp/data/dao/UsuarioDao;", "socioDao", "Lcom/example/bookapp/data/dao/SocioDao;", "(Lcom/example/bookapp/data/dao/UsuarioDao;Lcom/example/bookapp/data/dao/SocioDao;)V", "allUsuarios", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/bookapp/data/entities/UsuarioEntity;", "getAllUsuarios", "()Lkotlinx/coroutines/flow/Flow;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "usuariosCollection", "Lcom/google/firebase/firestore/CollectionReference;", "ensureLectorIsSocio", "", "usuario", "(Lcom/example/bookapp/data/entities/UsuarioEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUsuarioByCorreo", "correo", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertUsuario", "syncUsuarioToFirestore", "syncUsuariosFromFirestore", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUsuario", "app_debug"})
public final class UsuarioRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.data.dao.UsuarioDao usuarioDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.data.dao.SocioDao socioDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference usuariosCollection = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.UsuarioEntity>> allUsuarios = null;
    
    public UsuarioRepository(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.dao.UsuarioDao usuarioDao, @org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.dao.SocioDao socioDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.UsuarioEntity>> getAllUsuarios() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertUsuario(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.UsuarioEntity usuario, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateUsuario(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.UsuarioEntity usuario, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object syncUsuarioToFirestore(com.example.bookapp.data.entities.UsuarioEntity usuario, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUsuarioByCorreo(@org.jetbrains.annotations.NotNull()
    java.lang.String correo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.bookapp.data.entities.UsuarioEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object ensureLectorIsSocio(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.UsuarioEntity usuario, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncUsuariosFromFirestore(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}