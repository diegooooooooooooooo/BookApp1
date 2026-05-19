package com.example.bookapp.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.bookapp.data.database.Converters;
import com.example.bookapp.data.entities.LibroEntity;
import com.example.bookapp.data.entities.LibroEstado;
import com.example.bookapp.data.entities.PrestamoConDetalles;
import com.example.bookapp.data.entities.PrestamoEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PrestamoDao_Impl implements PrestamoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PrestamoEntity> __insertionAdapterOfPrestamoEntity;

  private final EntityDeletionOrUpdateAdapter<PrestamoEntity> __updateAdapterOfPrestamoEntity;

  private final Converters __converters = new Converters();

  public PrestamoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPrestamoEntity = new EntityInsertionAdapter<PrestamoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `prestamos` (`id`,`socioId`,`libroId`,`fechaPrestamo`,`fechaDevolucionEsperada`,`fechaEntregaReal`,`multa`,`valorPrestamo`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PrestamoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getSocioId());
        statement.bindLong(3, entity.getLibroId());
        statement.bindLong(4, entity.getFechaPrestamo());
        statement.bindLong(5, entity.getFechaDevolucionEsperada());
        if (entity.getFechaEntregaReal() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getFechaEntregaReal());
        }
        statement.bindDouble(7, entity.getMulta());
        statement.bindDouble(8, entity.getValorPrestamo());
      }
    };
    this.__updateAdapterOfPrestamoEntity = new EntityDeletionOrUpdateAdapter<PrestamoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `prestamos` SET `id` = ?,`socioId` = ?,`libroId` = ?,`fechaPrestamo` = ?,`fechaDevolucionEsperada` = ?,`fechaEntregaReal` = ?,`multa` = ?,`valorPrestamo` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PrestamoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getSocioId());
        statement.bindLong(3, entity.getLibroId());
        statement.bindLong(4, entity.getFechaPrestamo());
        statement.bindLong(5, entity.getFechaDevolucionEsperada());
        if (entity.getFechaEntregaReal() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getFechaEntregaReal());
        }
        statement.bindDouble(7, entity.getMulta());
        statement.bindDouble(8, entity.getValorPrestamo());
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final PrestamoEntity prestamo,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPrestamoEntity.insertAndReturnId(prestamo);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final PrestamoEntity prestamo,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPrestamoEntity.handle(prestamo);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getPrestamoById(final int id,
      final Continuation<? super PrestamoEntity> $completion) {
    final String _sql = "SELECT * FROM prestamos WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PrestamoEntity>() {
      @Override
      @Nullable
      public PrestamoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSocioId = CursorUtil.getColumnIndexOrThrow(_cursor, "socioId");
          final int _cursorIndexOfLibroId = CursorUtil.getColumnIndexOrThrow(_cursor, "libroId");
          final int _cursorIndexOfFechaPrestamo = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaPrestamo");
          final int _cursorIndexOfFechaDevolucionEsperada = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaDevolucionEsperada");
          final int _cursorIndexOfFechaEntregaReal = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaEntregaReal");
          final int _cursorIndexOfMulta = CursorUtil.getColumnIndexOrThrow(_cursor, "multa");
          final int _cursorIndexOfValorPrestamo = CursorUtil.getColumnIndexOrThrow(_cursor, "valorPrestamo");
          final PrestamoEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpSocioId;
            _tmpSocioId = _cursor.getInt(_cursorIndexOfSocioId);
            final int _tmpLibroId;
            _tmpLibroId = _cursor.getInt(_cursorIndexOfLibroId);
            final long _tmpFechaPrestamo;
            _tmpFechaPrestamo = _cursor.getLong(_cursorIndexOfFechaPrestamo);
            final long _tmpFechaDevolucionEsperada;
            _tmpFechaDevolucionEsperada = _cursor.getLong(_cursorIndexOfFechaDevolucionEsperada);
            final Long _tmpFechaEntregaReal;
            if (_cursor.isNull(_cursorIndexOfFechaEntregaReal)) {
              _tmpFechaEntregaReal = null;
            } else {
              _tmpFechaEntregaReal = _cursor.getLong(_cursorIndexOfFechaEntregaReal);
            }
            final double _tmpMulta;
            _tmpMulta = _cursor.getDouble(_cursorIndexOfMulta);
            final double _tmpValorPrestamo;
            _tmpValorPrestamo = _cursor.getDouble(_cursorIndexOfValorPrestamo);
            _result = new PrestamoEntity(_tmpId,_tmpSocioId,_tmpLibroId,_tmpFechaPrestamo,_tmpFechaDevolucionEsperada,_tmpFechaEntregaReal,_tmpMulta,_tmpValorPrestamo);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PrestamoEntity>> getAllPrestamos() {
    final String _sql = "SELECT * FROM prestamos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prestamos"}, new Callable<List<PrestamoEntity>>() {
      @Override
      @NonNull
      public List<PrestamoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSocioId = CursorUtil.getColumnIndexOrThrow(_cursor, "socioId");
          final int _cursorIndexOfLibroId = CursorUtil.getColumnIndexOrThrow(_cursor, "libroId");
          final int _cursorIndexOfFechaPrestamo = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaPrestamo");
          final int _cursorIndexOfFechaDevolucionEsperada = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaDevolucionEsperada");
          final int _cursorIndexOfFechaEntregaReal = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaEntregaReal");
          final int _cursorIndexOfMulta = CursorUtil.getColumnIndexOrThrow(_cursor, "multa");
          final int _cursorIndexOfValorPrestamo = CursorUtil.getColumnIndexOrThrow(_cursor, "valorPrestamo");
          final List<PrestamoEntity> _result = new ArrayList<PrestamoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrestamoEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpSocioId;
            _tmpSocioId = _cursor.getInt(_cursorIndexOfSocioId);
            final int _tmpLibroId;
            _tmpLibroId = _cursor.getInt(_cursorIndexOfLibroId);
            final long _tmpFechaPrestamo;
            _tmpFechaPrestamo = _cursor.getLong(_cursorIndexOfFechaPrestamo);
            final long _tmpFechaDevolucionEsperada;
            _tmpFechaDevolucionEsperada = _cursor.getLong(_cursorIndexOfFechaDevolucionEsperada);
            final Long _tmpFechaEntregaReal;
            if (_cursor.isNull(_cursorIndexOfFechaEntregaReal)) {
              _tmpFechaEntregaReal = null;
            } else {
              _tmpFechaEntregaReal = _cursor.getLong(_cursorIndexOfFechaEntregaReal);
            }
            final double _tmpMulta;
            _tmpMulta = _cursor.getDouble(_cursorIndexOfMulta);
            final double _tmpValorPrestamo;
            _tmpValorPrestamo = _cursor.getDouble(_cursorIndexOfValorPrestamo);
            _item = new PrestamoEntity(_tmpId,_tmpSocioId,_tmpLibroId,_tmpFechaPrestamo,_tmpFechaDevolucionEsperada,_tmpFechaEntregaReal,_tmpMulta,_tmpValorPrestamo);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<PrestamoEntity>> getPrestamosDelMes(final String mes) {
    final String _sql = "SELECT * FROM prestamos WHERE strftime('%m', datetime(fechaEntregaReal/1000, 'unixepoch')) = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (mes == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, mes);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prestamos"}, new Callable<List<PrestamoEntity>>() {
      @Override
      @NonNull
      public List<PrestamoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSocioId = CursorUtil.getColumnIndexOrThrow(_cursor, "socioId");
          final int _cursorIndexOfLibroId = CursorUtil.getColumnIndexOrThrow(_cursor, "libroId");
          final int _cursorIndexOfFechaPrestamo = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaPrestamo");
          final int _cursorIndexOfFechaDevolucionEsperada = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaDevolucionEsperada");
          final int _cursorIndexOfFechaEntregaReal = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaEntregaReal");
          final int _cursorIndexOfMulta = CursorUtil.getColumnIndexOrThrow(_cursor, "multa");
          final int _cursorIndexOfValorPrestamo = CursorUtil.getColumnIndexOrThrow(_cursor, "valorPrestamo");
          final List<PrestamoEntity> _result = new ArrayList<PrestamoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrestamoEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpSocioId;
            _tmpSocioId = _cursor.getInt(_cursorIndexOfSocioId);
            final int _tmpLibroId;
            _tmpLibroId = _cursor.getInt(_cursorIndexOfLibroId);
            final long _tmpFechaPrestamo;
            _tmpFechaPrestamo = _cursor.getLong(_cursorIndexOfFechaPrestamo);
            final long _tmpFechaDevolucionEsperada;
            _tmpFechaDevolucionEsperada = _cursor.getLong(_cursorIndexOfFechaDevolucionEsperada);
            final Long _tmpFechaEntregaReal;
            if (_cursor.isNull(_cursorIndexOfFechaEntregaReal)) {
              _tmpFechaEntregaReal = null;
            } else {
              _tmpFechaEntregaReal = _cursor.getLong(_cursorIndexOfFechaEntregaReal);
            }
            final double _tmpMulta;
            _tmpMulta = _cursor.getDouble(_cursorIndexOfMulta);
            final double _tmpValorPrestamo;
            _tmpValorPrestamo = _cursor.getDouble(_cursorIndexOfValorPrestamo);
            _item = new PrestamoEntity(_tmpId,_tmpSocioId,_tmpLibroId,_tmpFechaPrestamo,_tmpFechaDevolucionEsperada,_tmpFechaEntregaReal,_tmpMulta,_tmpValorPrestamo);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Double> getTotalIngresosDelMes(final String mes) {
    final String _sql = "SELECT SUM(multa + valorPrestamo) FROM prestamos WHERE strftime('%m', datetime(fechaPrestamo/1000, 'unixepoch')) = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (mes == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, mes);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prestamos"}, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Double> getTotalIngresosDelAnio(final String anio) {
    final String _sql = "SELECT SUM(multa + valorPrestamo) FROM prestamos WHERE strftime('%Y', datetime(fechaPrestamo/1000, 'unixepoch')) = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (anio == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, anio);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prestamos"}, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<PrestamoEntity>> getPrestamosActivos() {
    final String _sql = "SELECT * FROM prestamos WHERE fechaEntregaReal IS NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prestamos"}, new Callable<List<PrestamoEntity>>() {
      @Override
      @NonNull
      public List<PrestamoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSocioId = CursorUtil.getColumnIndexOrThrow(_cursor, "socioId");
          final int _cursorIndexOfLibroId = CursorUtil.getColumnIndexOrThrow(_cursor, "libroId");
          final int _cursorIndexOfFechaPrestamo = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaPrestamo");
          final int _cursorIndexOfFechaDevolucionEsperada = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaDevolucionEsperada");
          final int _cursorIndexOfFechaEntregaReal = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaEntregaReal");
          final int _cursorIndexOfMulta = CursorUtil.getColumnIndexOrThrow(_cursor, "multa");
          final int _cursorIndexOfValorPrestamo = CursorUtil.getColumnIndexOrThrow(_cursor, "valorPrestamo");
          final List<PrestamoEntity> _result = new ArrayList<PrestamoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrestamoEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpSocioId;
            _tmpSocioId = _cursor.getInt(_cursorIndexOfSocioId);
            final int _tmpLibroId;
            _tmpLibroId = _cursor.getInt(_cursorIndexOfLibroId);
            final long _tmpFechaPrestamo;
            _tmpFechaPrestamo = _cursor.getLong(_cursorIndexOfFechaPrestamo);
            final long _tmpFechaDevolucionEsperada;
            _tmpFechaDevolucionEsperada = _cursor.getLong(_cursorIndexOfFechaDevolucionEsperada);
            final Long _tmpFechaEntregaReal;
            if (_cursor.isNull(_cursorIndexOfFechaEntregaReal)) {
              _tmpFechaEntregaReal = null;
            } else {
              _tmpFechaEntregaReal = _cursor.getLong(_cursorIndexOfFechaEntregaReal);
            }
            final double _tmpMulta;
            _tmpMulta = _cursor.getDouble(_cursorIndexOfMulta);
            final double _tmpValorPrestamo;
            _tmpValorPrestamo = _cursor.getDouble(_cursorIndexOfValorPrestamo);
            _item = new PrestamoEntity(_tmpId,_tmpSocioId,_tmpLibroId,_tmpFechaPrestamo,_tmpFechaDevolucionEsperada,_tmpFechaEntregaReal,_tmpMulta,_tmpValorPrestamo);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<PrestamoConDetalles>> getPrestamosActivosConDetalles() {
    final String _sql = "\n"
            + "        SELECT prestamos.id, socios.nombre as socioNombre, libros.titulo as libroTitulo, \n"
            + "               prestamos.fechaPrestamo, prestamos.fechaDevolucionEsperada, \n"
            + "               prestamos.fechaEntregaReal, prestamos.multa, prestamos.valorPrestamo \n"
            + "        FROM prestamos \n"
            + "        INNER JOIN socios ON prestamos.socioId = socios.id \n"
            + "        INNER JOIN libros ON prestamos.libroId = libros.id \n"
            + "        WHERE prestamos.fechaEntregaReal IS NULL\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prestamos", "socios",
        "libros"}, new Callable<List<PrestamoConDetalles>>() {
      @Override
      @NonNull
      public List<PrestamoConDetalles> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfSocioNombre = 1;
          final int _cursorIndexOfLibroTitulo = 2;
          final int _cursorIndexOfFechaPrestamo = 3;
          final int _cursorIndexOfFechaDevolucionEsperada = 4;
          final int _cursorIndexOfFechaEntregaReal = 5;
          final int _cursorIndexOfMulta = 6;
          final int _cursorIndexOfValorPrestamo = 7;
          final List<PrestamoConDetalles> _result = new ArrayList<PrestamoConDetalles>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrestamoConDetalles _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpSocioNombre;
            if (_cursor.isNull(_cursorIndexOfSocioNombre)) {
              _tmpSocioNombre = null;
            } else {
              _tmpSocioNombre = _cursor.getString(_cursorIndexOfSocioNombre);
            }
            final String _tmpLibroTitulo;
            if (_cursor.isNull(_cursorIndexOfLibroTitulo)) {
              _tmpLibroTitulo = null;
            } else {
              _tmpLibroTitulo = _cursor.getString(_cursorIndexOfLibroTitulo);
            }
            final long _tmpFechaPrestamo;
            _tmpFechaPrestamo = _cursor.getLong(_cursorIndexOfFechaPrestamo);
            final long _tmpFechaDevolucionEsperada;
            _tmpFechaDevolucionEsperada = _cursor.getLong(_cursorIndexOfFechaDevolucionEsperada);
            final Long _tmpFechaEntregaReal;
            if (_cursor.isNull(_cursorIndexOfFechaEntregaReal)) {
              _tmpFechaEntregaReal = null;
            } else {
              _tmpFechaEntregaReal = _cursor.getLong(_cursorIndexOfFechaEntregaReal);
            }
            final double _tmpMulta;
            _tmpMulta = _cursor.getDouble(_cursorIndexOfMulta);
            final double _tmpValorPrestamo;
            _tmpValorPrestamo = _cursor.getDouble(_cursorIndexOfValorPrestamo);
            _item = new PrestamoConDetalles(_tmpId,_tmpSocioNombre,_tmpLibroTitulo,_tmpFechaPrestamo,_tmpFechaDevolucionEsperada,_tmpFechaEntregaReal,_tmpMulta,_tmpValorPrestamo);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<PrestamoConDetalles>> getPrestamosPorCorreoSocio(final String correo) {
    final String _sql = "\n"
            + "        SELECT prestamos.id, socios.nombre as socioNombre, libros.titulo as libroTitulo, \n"
            + "               prestamos.fechaPrestamo, prestamos.fechaDevolucionEsperada, \n"
            + "               prestamos.fechaEntregaReal, prestamos.multa, prestamos.valorPrestamo \n"
            + "        FROM prestamos \n"
            + "        INNER JOIN socios ON prestamos.socioId = socios.id \n"
            + "        INNER JOIN libros ON prestamos.libroId = libros.id \n"
            + "        WHERE socios.correo = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (correo == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, correo);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prestamos", "socios",
        "libros"}, new Callable<List<PrestamoConDetalles>>() {
      @Override
      @NonNull
      public List<PrestamoConDetalles> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfSocioNombre = 1;
          final int _cursorIndexOfLibroTitulo = 2;
          final int _cursorIndexOfFechaPrestamo = 3;
          final int _cursorIndexOfFechaDevolucionEsperada = 4;
          final int _cursorIndexOfFechaEntregaReal = 5;
          final int _cursorIndexOfMulta = 6;
          final int _cursorIndexOfValorPrestamo = 7;
          final List<PrestamoConDetalles> _result = new ArrayList<PrestamoConDetalles>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrestamoConDetalles _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpSocioNombre;
            if (_cursor.isNull(_cursorIndexOfSocioNombre)) {
              _tmpSocioNombre = null;
            } else {
              _tmpSocioNombre = _cursor.getString(_cursorIndexOfSocioNombre);
            }
            final String _tmpLibroTitulo;
            if (_cursor.isNull(_cursorIndexOfLibroTitulo)) {
              _tmpLibroTitulo = null;
            } else {
              _tmpLibroTitulo = _cursor.getString(_cursorIndexOfLibroTitulo);
            }
            final long _tmpFechaPrestamo;
            _tmpFechaPrestamo = _cursor.getLong(_cursorIndexOfFechaPrestamo);
            final long _tmpFechaDevolucionEsperada;
            _tmpFechaDevolucionEsperada = _cursor.getLong(_cursorIndexOfFechaDevolucionEsperada);
            final Long _tmpFechaEntregaReal;
            if (_cursor.isNull(_cursorIndexOfFechaEntregaReal)) {
              _tmpFechaEntregaReal = null;
            } else {
              _tmpFechaEntregaReal = _cursor.getLong(_cursorIndexOfFechaEntregaReal);
            }
            final double _tmpMulta;
            _tmpMulta = _cursor.getDouble(_cursorIndexOfMulta);
            final double _tmpValorPrestamo;
            _tmpValorPrestamo = _cursor.getDouble(_cursorIndexOfValorPrestamo);
            _item = new PrestamoConDetalles(_tmpId,_tmpSocioNombre,_tmpLibroTitulo,_tmpFechaPrestamo,_tmpFechaDevolucionEsperada,_tmpFechaEntregaReal,_tmpMulta,_tmpValorPrestamo);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<PrestamoConDetalles>> getHistorialPrestamosConDetalles() {
    final String _sql = "\n"
            + "        SELECT prestamos.id, socios.nombre as socioNombre, libros.titulo as libroTitulo, \n"
            + "               prestamos.fechaPrestamo, prestamos.fechaDevolucionEsperada, \n"
            + "               prestamos.fechaEntregaReal, prestamos.multa, prestamos.valorPrestamo \n"
            + "        FROM prestamos \n"
            + "        INNER JOIN socios ON prestamos.socioId = socios.id \n"
            + "        INNER JOIN libros ON prestamos.libroId = libros.id \n"
            + "        ORDER BY prestamos.fechaPrestamo DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"prestamos", "socios",
        "libros"}, new Callable<List<PrestamoConDetalles>>() {
      @Override
      @NonNull
      public List<PrestamoConDetalles> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfSocioNombre = 1;
          final int _cursorIndexOfLibroTitulo = 2;
          final int _cursorIndexOfFechaPrestamo = 3;
          final int _cursorIndexOfFechaDevolucionEsperada = 4;
          final int _cursorIndexOfFechaEntregaReal = 5;
          final int _cursorIndexOfMulta = 6;
          final int _cursorIndexOfValorPrestamo = 7;
          final List<PrestamoConDetalles> _result = new ArrayList<PrestamoConDetalles>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrestamoConDetalles _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpSocioNombre;
            if (_cursor.isNull(_cursorIndexOfSocioNombre)) {
              _tmpSocioNombre = null;
            } else {
              _tmpSocioNombre = _cursor.getString(_cursorIndexOfSocioNombre);
            }
            final String _tmpLibroTitulo;
            if (_cursor.isNull(_cursorIndexOfLibroTitulo)) {
              _tmpLibroTitulo = null;
            } else {
              _tmpLibroTitulo = _cursor.getString(_cursorIndexOfLibroTitulo);
            }
            final long _tmpFechaPrestamo;
            _tmpFechaPrestamo = _cursor.getLong(_cursorIndexOfFechaPrestamo);
            final long _tmpFechaDevolucionEsperada;
            _tmpFechaDevolucionEsperada = _cursor.getLong(_cursorIndexOfFechaDevolucionEsperada);
            final Long _tmpFechaEntregaReal;
            if (_cursor.isNull(_cursorIndexOfFechaEntregaReal)) {
              _tmpFechaEntregaReal = null;
            } else {
              _tmpFechaEntregaReal = _cursor.getLong(_cursorIndexOfFechaEntregaReal);
            }
            final double _tmpMulta;
            _tmpMulta = _cursor.getDouble(_cursorIndexOfMulta);
            final double _tmpValorPrestamo;
            _tmpValorPrestamo = _cursor.getDouble(_cursorIndexOfValorPrestamo);
            _item = new PrestamoConDetalles(_tmpId,_tmpSocioNombre,_tmpLibroTitulo,_tmpFechaPrestamo,_tmpFechaDevolucionEsperada,_tmpFechaEntregaReal,_tmpMulta,_tmpValorPrestamo);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<PrestamoDao.LibroConConteo>> getTop5LibrosMasPrestados() {
    final String _sql = "\n"
            + "        SELECT libros.*, COUNT(prestamos.id) as prestamosCount \n"
            + "        FROM libros \n"
            + "        INNER JOIN prestamos ON libros.id = prestamos.libroId \n"
            + "        GROUP BY libros.id \n"
            + "        ORDER BY prestamosCount DESC \n"
            + "        LIMIT 5\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"libros",
        "prestamos"}, new Callable<List<PrestamoDao.LibroConConteo>>() {
      @Override
      @NonNull
      public List<PrestamoDao.LibroConConteo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfAutor = CursorUtil.getColumnIndexOrThrow(_cursor, "autor");
          final int _cursorIndexOfIsbn = CursorUtil.getColumnIndexOrThrow(_cursor, "isbn");
          final int _cursorIndexOfCategoria = CursorUtil.getColumnIndexOrThrow(_cursor, "categoria");
          final int _cursorIndexOfEditorial = CursorUtil.getColumnIndexOrThrow(_cursor, "editorial");
          final int _cursorIndexOfEjemplares = CursorUtil.getColumnIndexOrThrow(_cursor, "ejemplares");
          final int _cursorIndexOfValor = CursorUtil.getColumnIndexOrThrow(_cursor, "valor");
          final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
          final int _cursorIndexOfPrestamosCount = CursorUtil.getColumnIndexOrThrow(_cursor, "prestamosCount");
          final List<PrestamoDao.LibroConConteo> _result = new ArrayList<PrestamoDao.LibroConConteo>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PrestamoDao.LibroConConteo _item;
            final int _tmpPrestamosCount;
            _tmpPrestamosCount = _cursor.getInt(_cursorIndexOfPrestamosCount);
            final LibroEntity _tmpLibro;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitulo;
            if (_cursor.isNull(_cursorIndexOfTitulo)) {
              _tmpTitulo = null;
            } else {
              _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            }
            final String _tmpAutor;
            if (_cursor.isNull(_cursorIndexOfAutor)) {
              _tmpAutor = null;
            } else {
              _tmpAutor = _cursor.getString(_cursorIndexOfAutor);
            }
            final String _tmpIsbn;
            if (_cursor.isNull(_cursorIndexOfIsbn)) {
              _tmpIsbn = null;
            } else {
              _tmpIsbn = _cursor.getString(_cursorIndexOfIsbn);
            }
            final String _tmpCategoria;
            if (_cursor.isNull(_cursorIndexOfCategoria)) {
              _tmpCategoria = null;
            } else {
              _tmpCategoria = _cursor.getString(_cursorIndexOfCategoria);
            }
            final String _tmpEditorial;
            if (_cursor.isNull(_cursorIndexOfEditorial)) {
              _tmpEditorial = null;
            } else {
              _tmpEditorial = _cursor.getString(_cursorIndexOfEditorial);
            }
            final int _tmpEjemplares;
            _tmpEjemplares = _cursor.getInt(_cursorIndexOfEjemplares);
            final double _tmpValor;
            _tmpValor = _cursor.getDouble(_cursorIndexOfValor);
            final LibroEstado _tmpEstado;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfEstado)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfEstado);
            }
            _tmpEstado = __converters.toLibroEstado(_tmp);
            _tmpLibro = new LibroEntity(_tmpId,_tmpTitulo,_tmpAutor,_tmpIsbn,_tmpCategoria,_tmpEditorial,_tmpEjemplares,_tmpValor,_tmpEstado);
            _item = new PrestamoDao.LibroConConteo(_tmpLibro,_tmpPrestamosCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
