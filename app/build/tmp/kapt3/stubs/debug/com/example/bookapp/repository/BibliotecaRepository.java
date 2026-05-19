package com.example.bookapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00c4\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010AJ\u0016\u0010B\u001a\u00020?2\u0006\u0010C\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010DJ\u0012\u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\r0\fJ\u0016\u0010F\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010G0\f2\u0006\u0010H\u001a\u00020IJ\u0016\u0010J\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010G0\f2\u0006\u0010K\u001a\u00020IJ\u0018\u0010L\u001a\u0004\u0018\u00010\u000e2\u0006\u0010M\u001a\u00020NH\u0086@\u00a2\u0006\u0002\u0010OJ\u0018\u0010P\u001a\u0004\u0018\u00010\u00122\u0006\u0010M\u001a\u00020NH\u0086@\u00a2\u0006\u0002\u0010OJ\u001a\u0010Q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\r0\f2\u0006\u0010R\u001a\u00020NJ\u001a\u0010S\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\r0\f2\u0006\u0010T\u001a\u00020IJ\u001a\u0010U\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r0\f2\u0006\u0010K\u001a\u00020IJ\u0012\u0010V\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020W0\r0\fJ\u0018\u0010X\u001a\u0004\u0018\u00010\u00182\u0006\u0010T\u001a\u00020IH\u0086@\u00a2\u0006\u0002\u0010YJ\u0016\u0010Z\u001a\u00020N2\u0006\u0010@\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010AJ\u0016\u0010[\u001a\u00020?2\u0006\u0010\\\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010]J\u0016\u0010^\u001a\u00020?2\u0006\u0010C\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010DJ\u0016\u0010_\u001a\u00020?2\u0006\u0010`\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010aJ$\u0010b\u001a\b\u0012\u0004\u0012\u00020?0c2\u0006\u0010`\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bd\u0010aJ\u001a\u0010e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010f\u001a\u00020IJ\u0016\u0010g\u001a\u00020h2\u0006\u0010f\u001a\u00020IH\u0086@\u00a2\u0006\u0002\u0010YJ\u0006\u0010i\u001a\u00020?J\u000e\u0010j\u001a\u00020?H\u0086@\u00a2\u0006\u0002\u0010kJ\u000e\u0010l\u001a\u00020?H\u0086@\u00a2\u0006\u0002\u0010kJ\u0016\u0010m\u001a\u00020?2\u0006\u0010@\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010AJ\u0016\u0010n\u001a\u00020?2\u0006\u0010C\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010DR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010 \u001a\u00020!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\"\u001a\u00020#\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010(\u001a\u00020)\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001d\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0010R\u001d\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0010R\u000e\u00101\u001a\u00020\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u00104\u001a\u000205\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u000e\u00108\u001a\u00020\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u00109\u001a\u00020:\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u000e\u0010=\u001a\u00020\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006o"}, d2 = {"Lcom/example/bookapp/repository/BibliotecaRepository;", "", "libroDao", "Lcom/example/bookapp/data/dao/LibroDao;", "usuarioDao", "Lcom/example/bookapp/data/dao/UsuarioDao;", "prestamoDao", "Lcom/example/bookapp/data/dao/PrestamoDao;", "socioDao", "Lcom/example/bookapp/data/dao/SocioDao;", "(Lcom/example/bookapp/data/dao/LibroDao;Lcom/example/bookapp/data/dao/UsuarioDao;Lcom/example/bookapp/data/dao/PrestamoDao;Lcom/example/bookapp/data/dao/SocioDao;)V", "allLibros", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/bookapp/data/entities/LibroEntity;", "getAllLibros", "()Lkotlinx/coroutines/flow/Flow;", "allPrestamos", "Lcom/example/bookapp/data/entities/PrestamoEntity;", "getAllPrestamos", "allSocios", "Lcom/example/bookapp/data/entities/SocioEntity;", "getAllSocios", "allUsuarios", "Lcom/example/bookapp/data/entities/UsuarioEntity;", "getAllUsuarios", "apiService", "Lcom/example/bookapp/data/api/OpenLibraryService;", "getApiService", "()Lcom/example/bookapp/data/api/OpenLibraryService;", "apiService$delegate", "Lkotlin/Lazy;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "libroRepository", "Lcom/example/bookapp/repository/LibroRepository;", "getLibroRepository", "()Lcom/example/bookapp/repository/LibroRepository;", "librosCollection", "Lcom/google/firebase/firestore/CollectionReference;", "prestamoRepository", "Lcom/example/bookapp/repository/PrestamoRepository;", "getPrestamoRepository", "()Lcom/example/bookapp/repository/PrestamoRepository;", "prestamosActivos", "getPrestamosActivos", "prestamosActivosConDetalles", "Lcom/example/bookapp/data/entities/PrestamoConDetalles;", "getPrestamosActivosConDetalles", "prestamosCollection", "repositoryScope", "Lkotlinx/coroutines/CoroutineScope;", "socioRepository", "Lcom/example/bookapp/repository/SocioRepository;", "getSocioRepository", "()Lcom/example/bookapp/repository/SocioRepository;", "sociosCollection", "usuarioRepository", "Lcom/example/bookapp/repository/UsuarioRepository;", "getUsuarioRepository", "()Lcom/example/bookapp/repository/UsuarioRepository;", "usuariosCollection", "deleteLibro", "", "libro", "(Lcom/example/bookapp/data/entities/LibroEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ensureLectorIsSocio", "usuario", "(Lcom/example/bookapp/data/entities/UsuarioEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHistorialPrestamosConDetalles", "getIngresosPorAnio", "", "anio", "", "getIngresosPorMes", "mes", "getLibroById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPrestamoById", "getPrestamosActivosPorSocio", "socioId", "getPrestamosPorCorreoSocio", "correo", "getPrestamosPorMes", "getTop5Libros", "Lcom/example/bookapp/data/dao/PrestamoDao$LibroConConteo;", "getUsuarioByCorreo", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertLibro", "insertSocio", "socio", "(Lcom/example/bookapp/data/entities/SocioEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertUsuario", "registrarDevolucion", "prestamo", "(Lcom/example/bookapp/data/entities/PrestamoEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registrarPrestamo", "Lkotlin/Result;", "registrarPrestamo-gIAlu-s", "searchLibros", "query", "searchOnline", "Lcom/example/bookapp/data/api/SearchResponse;", "startRealtimeSync", "syncFromFirestore", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncUsuariosFromFirestore", "updateLibro", "updateUsuario", "app_debug"})
public final class BibliotecaRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.data.dao.LibroDao libroDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.data.dao.UsuarioDao usuarioDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.data.dao.PrestamoDao prestamoDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.data.dao.SocioDao socioDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope repositoryScope = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.repository.LibroRepository libroRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.repository.UsuarioRepository usuarioRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.repository.PrestamoRepository prestamoRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.bookapp.repository.SocioRepository socioRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference librosCollection = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference usuariosCollection = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference sociosCollection = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference prestamosCollection = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy apiService$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.LibroEntity>> allLibros = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.UsuarioEntity>> allUsuarios = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.SocioEntity>> allSocios = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> allPrestamos = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> prestamosActivos = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> prestamosActivosConDetalles = null;
    
    public BibliotecaRepository(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.dao.LibroDao libroDao, @org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.dao.UsuarioDao usuarioDao, @org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.dao.PrestamoDao prestamoDao, @org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.dao.SocioDao socioDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.bookapp.repository.LibroRepository getLibroRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.bookapp.repository.UsuarioRepository getUsuarioRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.bookapp.repository.PrestamoRepository getPrestamoRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.bookapp.repository.SocioRepository getSocioRepository() {
        return null;
    }
    
    private final com.example.bookapp.data.api.OpenLibraryService getApiService() {
        return null;
    }
    
    /**
     * Inicia la sincronización en tiempo real desde Firestore hacia Room.
     * Cualquier cambio realizado por otro usuario en la nube se reflejará localmente.
     */
    public final void startRealtimeSync() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.LibroEntity>> searchLibros(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLibroById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.bookapp.data.entities.LibroEntity> $completion) {
        return null;
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
    public final java.lang.Object getUsuarioByCorreo(@org.jetbrains.annotations.NotNull()
    java.lang.String correo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.bookapp.data.entities.UsuarioEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateUsuario(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.UsuarioEntity usuario, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
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
    public final java.lang.Object getPrestamoById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.bookapp.data.entities.PrestamoEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object registrarDevolucion(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.PrestamoEntity prestamo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchOnline(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.bookapp.data.api.SearchResponse> $completion) {
        return null;
    }
    
    /**
     * Función para descargar datos de Firestore a Room (Sincronización inicial o manual)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncFromFirestore(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}