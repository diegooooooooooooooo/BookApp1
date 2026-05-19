package com.example.bookapp.data.dao;

/**
 * Acceso a datos para la tabla de préstamos.
 * Incluye lógica para reportes administrativos.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001:\u0001!J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0003H\'J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0003H\'J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00032\u0006\u0010\u000f\u001a\u00020\nH\'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\'J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00032\u0006\u0010\u0014\u001a\u00020\u0012H\'J\u0014\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00040\u0003H\'J\u0018\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00032\u0006\u0010\u0019\u001a\u00020\u0012H\'J\u0018\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\'J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u001e\u00a8\u0006\""}, d2 = {"Lcom/example/bookapp/data/dao/PrestamoDao;", "", "getAllPrestamos", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/bookapp/data/entities/PrestamoEntity;", "getHistorialPrestamosConDetalles", "Lcom/example/bookapp/data/entities/PrestamoConDetalles;", "getPrestamoById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPrestamosActivos", "getPrestamosActivosConDetalles", "getPrestamosActivosPorSocio", "socioId", "getPrestamosDelMes", "mes", "", "getPrestamosPorCorreoSocio", "correo", "getTop5LibrosMasPrestados", "Lcom/example/bookapp/data/dao/PrestamoDao$LibroConConteo;", "getTotalIngresosDelAnio", "", "anio", "getTotalIngresosDelMes", "insert", "", "prestamo", "(Lcom/example/bookapp/data/entities/PrestamoEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "LibroConConteo", "app_debug"})
@androidx.room.Dao()
public abstract interface PrestamoDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.PrestamoEntity prestamo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.example.bookapp.data.entities.PrestamoEntity prestamo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM prestamos WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPrestamoById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.bookapp.data.entities.PrestamoEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM prestamos")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> getAllPrestamos();
    
    @androidx.room.Query(value = "SELECT * FROM prestamos WHERE strftime(\'%m\', datetime(fechaEntregaReal/1000, \'unixepoch\')) = :mes")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> getPrestamosDelMes(@org.jetbrains.annotations.NotNull()
    java.lang.String mes);
    
    @androidx.room.Query(value = "SELECT SUM(multa + valorPrestamo) FROM prestamos WHERE strftime(\'%m\', datetime(fechaPrestamo/1000, \'unixepoch\')) = :mes")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Double> getTotalIngresosDelMes(@org.jetbrains.annotations.NotNull()
    java.lang.String mes);
    
    @androidx.room.Query(value = "SELECT SUM(multa + valorPrestamo) FROM prestamos WHERE strftime(\'%Y\', datetime(fechaPrestamo/1000, \'unixepoch\')) = :anio")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Double> getTotalIngresosDelAnio(@org.jetbrains.annotations.NotNull()
    java.lang.String anio);
    
    @androidx.room.Query(value = "SELECT * FROM prestamos WHERE fechaEntregaReal IS NULL")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoEntity>> getPrestamosActivos();
    
    @androidx.room.Query(value = "\n        SELECT prestamos.id, socios.nombre as socioNombre, libros.titulo as libroTitulo, prestamos.libroId,\n               prestamos.fechaPrestamo, prestamos.fechaDevolucionEsperada, \n               prestamos.fechaEntregaReal, prestamos.multa, prestamos.valorPrestamo \n        FROM prestamos \n        INNER JOIN socios ON prestamos.socioId = socios.id \n        INNER JOIN libros ON prestamos.libroId = libros.id \n        WHERE prestamos.fechaEntregaReal IS NULL\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> getPrestamosActivosConDetalles();
    
    @androidx.room.Query(value = "\n        SELECT prestamos.id, socios.nombre as socioNombre, libros.titulo as libroTitulo, prestamos.libroId,\n               prestamos.fechaPrestamo, prestamos.fechaDevolucionEsperada, \n               prestamos.fechaEntregaReal, prestamos.multa, prestamos.valorPrestamo \n        FROM prestamos \n        INNER JOIN socios ON prestamos.socioId = socios.id \n        INNER JOIN libros ON prestamos.libroId = libros.id \n        WHERE prestamos.fechaEntregaReal IS NULL AND prestamos.socioId = :socioId\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> getPrestamosActivosPorSocio(int socioId);
    
    @androidx.room.Query(value = "\n        SELECT prestamos.id, socios.nombre as socioNombre, libros.titulo as libroTitulo, prestamos.libroId,\n               prestamos.fechaPrestamo, prestamos.fechaDevolucionEsperada, \n               prestamos.fechaEntregaReal, prestamos.multa, prestamos.valorPrestamo \n        FROM prestamos \n        INNER JOIN socios ON prestamos.socioId = socios.id \n        INNER JOIN libros ON prestamos.libroId = libros.id \n        WHERE LOWER(socios.correo) = LOWER(:correo)\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> getPrestamosPorCorreoSocio(@org.jetbrains.annotations.NotNull()
    java.lang.String correo);
    
    @androidx.room.Query(value = "\n        SELECT prestamos.id, socios.nombre as socioNombre, libros.titulo as libroTitulo, prestamos.libroId,\n               prestamos.fechaPrestamo, prestamos.fechaDevolucionEsperada, \n               prestamos.fechaEntregaReal, prestamos.multa, prestamos.valorPrestamo \n        FROM prestamos \n        INNER JOIN socios ON prestamos.socioId = socios.id \n        INNER JOIN libros ON prestamos.libroId = libros.id \n        ORDER BY prestamos.fechaPrestamo DESC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.entities.PrestamoConDetalles>> getHistorialPrestamosConDetalles();
    
    @androidx.room.Query(value = "\n        SELECT libros.*, COUNT(prestamos.id) as prestamosCount \n        FROM libros \n        INNER JOIN prestamos ON libros.id = prestamos.libroId \n        GROUP BY libros.id \n        ORDER BY prestamosCount DESC \n        LIMIT 5\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.bookapp.data.dao.PrestamoDao.LibroConConteo>> getTop5LibrosMasPrestados();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/example/bookapp/data/dao/PrestamoDao$LibroConConteo;", "", "libro", "Lcom/example/bookapp/data/entities/LibroEntity;", "prestamosCount", "", "(Lcom/example/bookapp/data/entities/LibroEntity;I)V", "getLibro", "()Lcom/example/bookapp/data/entities/LibroEntity;", "getPrestamosCount", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
    public static final class LibroConConteo {
        @androidx.room.Embedded()
        @org.jetbrains.annotations.NotNull()
        private final com.example.bookapp.data.entities.LibroEntity libro = null;
        private final int prestamosCount = 0;
        
        public LibroConConteo(@org.jetbrains.annotations.NotNull()
        com.example.bookapp.data.entities.LibroEntity libro, int prestamosCount) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.bookapp.data.entities.LibroEntity getLibro() {
            return null;
        }
        
        public final int getPrestamosCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.bookapp.data.entities.LibroEntity component1() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.bookapp.data.dao.PrestamoDao.LibroConConteo copy(@org.jetbrains.annotations.NotNull()
        com.example.bookapp.data.entities.LibroEntity libro, int prestamosCount) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}