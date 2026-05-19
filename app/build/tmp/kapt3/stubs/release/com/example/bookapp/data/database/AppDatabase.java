package com.example.bookapp.data.database;

/**
 * Base de datos principal de la aplicación utilizando Room.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\f"}, d2 = {"Lcom/example/bookapp/data/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "libroDao", "Lcom/example/bookapp/data/dao/LibroDao;", "prestamoDao", "Lcom/example/bookapp/data/dao/PrestamoDao;", "socioDao", "Lcom/example/bookapp/data/dao/SocioDao;", "usuarioDao", "Lcom/example/bookapp/data/dao/UsuarioDao;", "Companion", "app_release"})
@androidx.room.Database(entities = {com.example.bookapp.data.entities.UsuarioEntity.class, com.example.bookapp.data.entities.LibroEntity.class, com.example.bookapp.data.entities.PrestamoEntity.class, com.example.bookapp.data.entities.SocioEntity.class}, version = 5, exportSchema = false)
@androidx.room.TypeConverters(value = {com.example.bookapp.data.database.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.example.bookapp.data.database.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.bookapp.data.database.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.bookapp.data.dao.UsuarioDao usuarioDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.bookapp.data.dao.LibroDao libroDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.bookapp.data.dao.PrestamoDao prestamoDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.bookapp.data.dao.SocioDao socioDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\bB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/bookapp/data/database/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/example/bookapp/data/database/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "DatabaseCallback", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.bookapp.data.database.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/example/bookapp/data/database/AppDatabase$Companion$DatabaseCallback;", "Landroidx/room/RoomDatabase$Callback;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onCreate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "seedDatabase", "usuarioDao", "Lcom/example/bookapp/data/dao/UsuarioDao;", "(Lcom/example/bookapp/data/dao/UsuarioDao;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
        static final class DatabaseCallback extends androidx.room.RoomDatabase.Callback {
            @org.jetbrains.annotations.NotNull()
            private final android.content.Context context = null;
            
            public DatabaseCallback(@org.jetbrains.annotations.NotNull()
            android.content.Context context) {
                super();
            }
            
            @java.lang.Override()
            public void onCreate(@org.jetbrains.annotations.NotNull()
            androidx.sqlite.db.SupportSQLiteDatabase db) {
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Object seedDatabase(@org.jetbrains.annotations.NotNull()
            com.example.bookapp.data.dao.UsuarioDao usuarioDao, @org.jetbrains.annotations.NotNull()
            kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
                return null;
            }
        }
    }
}