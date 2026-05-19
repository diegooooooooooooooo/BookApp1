package com.example.bookapp.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.bookapp.data.dao.LibroDao;
import com.example.bookapp.data.dao.LibroDao_Impl;
import com.example.bookapp.data.dao.PrestamoDao;
import com.example.bookapp.data.dao.PrestamoDao_Impl;
import com.example.bookapp.data.dao.SocioDao;
import com.example.bookapp.data.dao.SocioDao_Impl;
import com.example.bookapp.data.dao.UsuarioDao;
import com.example.bookapp.data.dao.UsuarioDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UsuarioDao _usuarioDao;

  private volatile LibroDao _libroDao;

  private volatile PrestamoDao _prestamoDao;

  private volatile SocioDao _socioDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `usuarios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT NOT NULL, `correo` TEXT NOT NULL, `contrasena` TEXT NOT NULL, `rol` TEXT NOT NULL, `fotoId` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `libros` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `titulo` TEXT NOT NULL, `autor` TEXT NOT NULL, `isbn` TEXT NOT NULL, `categoria` TEXT NOT NULL, `editorial` TEXT NOT NULL, `ejemplares` INTEGER NOT NULL, `valor` REAL NOT NULL, `estado` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `prestamos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `socioId` INTEGER NOT NULL, `libroId` INTEGER NOT NULL, `fechaPrestamo` INTEGER NOT NULL, `fechaDevolucionEsperada` INTEGER NOT NULL, `fechaEntregaReal` INTEGER, `multa` REAL NOT NULL, `valorPrestamo` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `socios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT NOT NULL, `dni` TEXT NOT NULL, `telefono` TEXT NOT NULL, `correo` TEXT NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_socios_correo` ON `socios` (`correo`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4f4c9657447628e462747fa25bcef539')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `usuarios`");
        db.execSQL("DROP TABLE IF EXISTS `libros`");
        db.execSQL("DROP TABLE IF EXISTS `prestamos`");
        db.execSQL("DROP TABLE IF EXISTS `socios`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsuarios = new HashMap<String, TableInfo.Column>(6);
        _columnsUsuarios.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("correo", new TableInfo.Column("correo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("contrasena", new TableInfo.Column("contrasena", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("rol", new TableInfo.Column("rol", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("fotoId", new TableInfo.Column("fotoId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsuarios = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsuarios = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsuarios = new TableInfo("usuarios", _columnsUsuarios, _foreignKeysUsuarios, _indicesUsuarios);
        final TableInfo _existingUsuarios = TableInfo.read(db, "usuarios");
        if (!_infoUsuarios.equals(_existingUsuarios)) {
          return new RoomOpenHelper.ValidationResult(false, "usuarios(com.example.bookapp.data.entities.UsuarioEntity).\n"
                  + " Expected:\n" + _infoUsuarios + "\n"
                  + " Found:\n" + _existingUsuarios);
        }
        final HashMap<String, TableInfo.Column> _columnsLibros = new HashMap<String, TableInfo.Column>(9);
        _columnsLibros.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLibros.put("titulo", new TableInfo.Column("titulo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLibros.put("autor", new TableInfo.Column("autor", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLibros.put("isbn", new TableInfo.Column("isbn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLibros.put("categoria", new TableInfo.Column("categoria", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLibros.put("editorial", new TableInfo.Column("editorial", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLibros.put("ejemplares", new TableInfo.Column("ejemplares", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLibros.put("valor", new TableInfo.Column("valor", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLibros.put("estado", new TableInfo.Column("estado", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLibros = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLibros = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLibros = new TableInfo("libros", _columnsLibros, _foreignKeysLibros, _indicesLibros);
        final TableInfo _existingLibros = TableInfo.read(db, "libros");
        if (!_infoLibros.equals(_existingLibros)) {
          return new RoomOpenHelper.ValidationResult(false, "libros(com.example.bookapp.data.entities.LibroEntity).\n"
                  + " Expected:\n" + _infoLibros + "\n"
                  + " Found:\n" + _existingLibros);
        }
        final HashMap<String, TableInfo.Column> _columnsPrestamos = new HashMap<String, TableInfo.Column>(8);
        _columnsPrestamos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrestamos.put("socioId", new TableInfo.Column("socioId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrestamos.put("libroId", new TableInfo.Column("libroId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrestamos.put("fechaPrestamo", new TableInfo.Column("fechaPrestamo", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrestamos.put("fechaDevolucionEsperada", new TableInfo.Column("fechaDevolucionEsperada", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrestamos.put("fechaEntregaReal", new TableInfo.Column("fechaEntregaReal", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrestamos.put("multa", new TableInfo.Column("multa", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPrestamos.put("valorPrestamo", new TableInfo.Column("valorPrestamo", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPrestamos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPrestamos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPrestamos = new TableInfo("prestamos", _columnsPrestamos, _foreignKeysPrestamos, _indicesPrestamos);
        final TableInfo _existingPrestamos = TableInfo.read(db, "prestamos");
        if (!_infoPrestamos.equals(_existingPrestamos)) {
          return new RoomOpenHelper.ValidationResult(false, "prestamos(com.example.bookapp.data.entities.PrestamoEntity).\n"
                  + " Expected:\n" + _infoPrestamos + "\n"
                  + " Found:\n" + _existingPrestamos);
        }
        final HashMap<String, TableInfo.Column> _columnsSocios = new HashMap<String, TableInfo.Column>(5);
        _columnsSocios.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSocios.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSocios.put("dni", new TableInfo.Column("dni", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSocios.put("telefono", new TableInfo.Column("telefono", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSocios.put("correo", new TableInfo.Column("correo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSocios = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSocios = new HashSet<TableInfo.Index>(1);
        _indicesSocios.add(new TableInfo.Index("index_socios_correo", true, Arrays.asList("correo"), Arrays.asList("ASC")));
        final TableInfo _infoSocios = new TableInfo("socios", _columnsSocios, _foreignKeysSocios, _indicesSocios);
        final TableInfo _existingSocios = TableInfo.read(db, "socios");
        if (!_infoSocios.equals(_existingSocios)) {
          return new RoomOpenHelper.ValidationResult(false, "socios(com.example.bookapp.data.entities.SocioEntity).\n"
                  + " Expected:\n" + _infoSocios + "\n"
                  + " Found:\n" + _existingSocios);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "4f4c9657447628e462747fa25bcef539", "122a041cba4740156e490a0edb82a2e8");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "usuarios","libros","prestamos","socios");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `usuarios`");
      _db.execSQL("DELETE FROM `libros`");
      _db.execSQL("DELETE FROM `prestamos`");
      _db.execSQL("DELETE FROM `socios`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UsuarioDao.class, UsuarioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LibroDao.class, LibroDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PrestamoDao.class, PrestamoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SocioDao.class, SocioDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UsuarioDao usuarioDao() {
    if (_usuarioDao != null) {
      return _usuarioDao;
    } else {
      synchronized(this) {
        if(_usuarioDao == null) {
          _usuarioDao = new UsuarioDao_Impl(this);
        }
        return _usuarioDao;
      }
    }
  }

  @Override
  public LibroDao libroDao() {
    if (_libroDao != null) {
      return _libroDao;
    } else {
      synchronized(this) {
        if(_libroDao == null) {
          _libroDao = new LibroDao_Impl(this);
        }
        return _libroDao;
      }
    }
  }

  @Override
  public PrestamoDao prestamoDao() {
    if (_prestamoDao != null) {
      return _prestamoDao;
    } else {
      synchronized(this) {
        if(_prestamoDao == null) {
          _prestamoDao = new PrestamoDao_Impl(this);
        }
        return _prestamoDao;
      }
    }
  }

  @Override
  public SocioDao socioDao() {
    if (_socioDao != null) {
      return _socioDao;
    } else {
      synchronized(this) {
        if(_socioDao == null) {
          _socioDao = new SocioDao_Impl(this);
        }
        return _socioDao;
      }
    }
  }
}
