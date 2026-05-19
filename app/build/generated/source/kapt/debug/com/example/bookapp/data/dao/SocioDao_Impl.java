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
import com.example.bookapp.data.entities.SocioEntity;
import java.lang.Class;
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
public final class SocioDao_Impl implements SocioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SocioEntity> __insertionAdapterOfSocioEntity;

  private final EntityDeletionOrUpdateAdapter<SocioEntity> __deletionAdapterOfSocioEntity;

  private final EntityDeletionOrUpdateAdapter<SocioEntity> __updateAdapterOfSocioEntity;

  public SocioDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSocioEntity = new EntityInsertionAdapter<SocioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `socios` (`id`,`nombre`,`dni`,`telefono`,`correo`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SocioEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNombre() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNombre());
        }
        if (entity.getDni() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDni());
        }
        if (entity.getTelefono() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTelefono());
        }
        if (entity.getCorreo() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCorreo());
        }
      }
    };
    this.__deletionAdapterOfSocioEntity = new EntityDeletionOrUpdateAdapter<SocioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `socios` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SocioEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfSocioEntity = new EntityDeletionOrUpdateAdapter<SocioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `socios` SET `id` = ?,`nombre` = ?,`dni` = ?,`telefono` = ?,`correo` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SocioEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNombre() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNombre());
        }
        if (entity.getDni() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDni());
        }
        if (entity.getTelefono() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTelefono());
        }
        if (entity.getCorreo() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCorreo());
        }
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final SocioEntity socio, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSocioEntity.insertAndReturnId(socio);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final SocioEntity socio, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSocioEntity.handle(socio);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final SocioEntity socio, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSocioEntity.handle(socio);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<SocioEntity>> getAllSocios() {
    final String _sql = "SELECT * FROM socios";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"socios"}, new Callable<List<SocioEntity>>() {
      @Override
      @NonNull
      public List<SocioEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfDni = CursorUtil.getColumnIndexOrThrow(_cursor, "dni");
          final int _cursorIndexOfTelefono = CursorUtil.getColumnIndexOrThrow(_cursor, "telefono");
          final int _cursorIndexOfCorreo = CursorUtil.getColumnIndexOrThrow(_cursor, "correo");
          final List<SocioEntity> _result = new ArrayList<SocioEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SocioEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            if (_cursor.isNull(_cursorIndexOfNombre)) {
              _tmpNombre = null;
            } else {
              _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            }
            final String _tmpDni;
            if (_cursor.isNull(_cursorIndexOfDni)) {
              _tmpDni = null;
            } else {
              _tmpDni = _cursor.getString(_cursorIndexOfDni);
            }
            final String _tmpTelefono;
            if (_cursor.isNull(_cursorIndexOfTelefono)) {
              _tmpTelefono = null;
            } else {
              _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
            }
            final String _tmpCorreo;
            if (_cursor.isNull(_cursorIndexOfCorreo)) {
              _tmpCorreo = null;
            } else {
              _tmpCorreo = _cursor.getString(_cursorIndexOfCorreo);
            }
            _item = new SocioEntity(_tmpId,_tmpNombre,_tmpDni,_tmpTelefono,_tmpCorreo);
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
  public Object getSocioById(final int id, final Continuation<? super SocioEntity> $completion) {
    final String _sql = "SELECT * FROM socios WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SocioEntity>() {
      @Override
      @Nullable
      public SocioEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfDni = CursorUtil.getColumnIndexOrThrow(_cursor, "dni");
          final int _cursorIndexOfTelefono = CursorUtil.getColumnIndexOrThrow(_cursor, "telefono");
          final int _cursorIndexOfCorreo = CursorUtil.getColumnIndexOrThrow(_cursor, "correo");
          final SocioEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            if (_cursor.isNull(_cursorIndexOfNombre)) {
              _tmpNombre = null;
            } else {
              _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            }
            final String _tmpDni;
            if (_cursor.isNull(_cursorIndexOfDni)) {
              _tmpDni = null;
            } else {
              _tmpDni = _cursor.getString(_cursorIndexOfDni);
            }
            final String _tmpTelefono;
            if (_cursor.isNull(_cursorIndexOfTelefono)) {
              _tmpTelefono = null;
            } else {
              _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
            }
            final String _tmpCorreo;
            if (_cursor.isNull(_cursorIndexOfCorreo)) {
              _tmpCorreo = null;
            } else {
              _tmpCorreo = _cursor.getString(_cursorIndexOfCorreo);
            }
            _result = new SocioEntity(_tmpId,_tmpNombre,_tmpDni,_tmpTelefono,_tmpCorreo);
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
  public Object getSocioByCorreo(final String correo,
      final Continuation<? super SocioEntity> $completion) {
    final String _sql = "SELECT * FROM socios WHERE LOWER(correo) = LOWER(?) LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (correo == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, correo);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SocioEntity>() {
      @Override
      @Nullable
      public SocioEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfDni = CursorUtil.getColumnIndexOrThrow(_cursor, "dni");
          final int _cursorIndexOfTelefono = CursorUtil.getColumnIndexOrThrow(_cursor, "telefono");
          final int _cursorIndexOfCorreo = CursorUtil.getColumnIndexOrThrow(_cursor, "correo");
          final SocioEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            if (_cursor.isNull(_cursorIndexOfNombre)) {
              _tmpNombre = null;
            } else {
              _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            }
            final String _tmpDni;
            if (_cursor.isNull(_cursorIndexOfDni)) {
              _tmpDni = null;
            } else {
              _tmpDni = _cursor.getString(_cursorIndexOfDni);
            }
            final String _tmpTelefono;
            if (_cursor.isNull(_cursorIndexOfTelefono)) {
              _tmpTelefono = null;
            } else {
              _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
            }
            final String _tmpCorreo;
            if (_cursor.isNull(_cursorIndexOfCorreo)) {
              _tmpCorreo = null;
            } else {
              _tmpCorreo = _cursor.getString(_cursorIndexOfCorreo);
            }
            _result = new SocioEntity(_tmpId,_tmpNombre,_tmpDni,_tmpTelefono,_tmpCorreo);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
