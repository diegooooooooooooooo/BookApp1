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
import com.example.bookapp.data.entities.UsuarioEntity;
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
public final class UsuarioDao_Impl implements UsuarioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UsuarioEntity> __insertionAdapterOfUsuarioEntity;

  private final EntityDeletionOrUpdateAdapter<UsuarioEntity> __updateAdapterOfUsuarioEntity;

  public UsuarioDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsuarioEntity = new EntityInsertionAdapter<UsuarioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `usuarios` (`id`,`nombre`,`correo`,`contrasena`,`rol`,`fotoId`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UsuarioEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNombre() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNombre());
        }
        if (entity.getCorreo() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCorreo());
        }
        if (entity.getContrasena() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getContrasena());
        }
        if (entity.getRol() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getRol());
        }
        if (entity.getFotoId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getFotoId());
        }
      }
    };
    this.__updateAdapterOfUsuarioEntity = new EntityDeletionOrUpdateAdapter<UsuarioEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `usuarios` SET `id` = ?,`nombre` = ?,`correo` = ?,`contrasena` = ?,`rol` = ?,`fotoId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UsuarioEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNombre() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNombre());
        }
        if (entity.getCorreo() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCorreo());
        }
        if (entity.getContrasena() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getContrasena());
        }
        if (entity.getRol() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getRol());
        }
        if (entity.getFotoId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getFotoId());
        }
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final UsuarioEntity usuario, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUsuarioEntity.insertAndReturnId(usuario);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final UsuarioEntity usuario, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUsuarioEntity.handle(usuario);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<UsuarioEntity>> getAllUsuarios() {
    final String _sql = "SELECT * FROM usuarios";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"usuarios"}, new Callable<List<UsuarioEntity>>() {
      @Override
      @NonNull
      public List<UsuarioEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfCorreo = CursorUtil.getColumnIndexOrThrow(_cursor, "correo");
          final int _cursorIndexOfContrasena = CursorUtil.getColumnIndexOrThrow(_cursor, "contrasena");
          final int _cursorIndexOfRol = CursorUtil.getColumnIndexOrThrow(_cursor, "rol");
          final int _cursorIndexOfFotoId = CursorUtil.getColumnIndexOrThrow(_cursor, "fotoId");
          final List<UsuarioEntity> _result = new ArrayList<UsuarioEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UsuarioEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            if (_cursor.isNull(_cursorIndexOfNombre)) {
              _tmpNombre = null;
            } else {
              _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            }
            final String _tmpCorreo;
            if (_cursor.isNull(_cursorIndexOfCorreo)) {
              _tmpCorreo = null;
            } else {
              _tmpCorreo = _cursor.getString(_cursorIndexOfCorreo);
            }
            final String _tmpContrasena;
            if (_cursor.isNull(_cursorIndexOfContrasena)) {
              _tmpContrasena = null;
            } else {
              _tmpContrasena = _cursor.getString(_cursorIndexOfContrasena);
            }
            final String _tmpRol;
            if (_cursor.isNull(_cursorIndexOfRol)) {
              _tmpRol = null;
            } else {
              _tmpRol = _cursor.getString(_cursorIndexOfRol);
            }
            final String _tmpFotoId;
            if (_cursor.isNull(_cursorIndexOfFotoId)) {
              _tmpFotoId = null;
            } else {
              _tmpFotoId = _cursor.getString(_cursorIndexOfFotoId);
            }
            _item = new UsuarioEntity(_tmpId,_tmpNombre,_tmpCorreo,_tmpContrasena,_tmpRol,_tmpFotoId);
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
  public Object getUsuarioByCorreo(final String correo,
      final Continuation<? super UsuarioEntity> $completion) {
    final String _sql = "SELECT * FROM usuarios WHERE correo = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (correo == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, correo);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UsuarioEntity>() {
      @Override
      @Nullable
      public UsuarioEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfCorreo = CursorUtil.getColumnIndexOrThrow(_cursor, "correo");
          final int _cursorIndexOfContrasena = CursorUtil.getColumnIndexOrThrow(_cursor, "contrasena");
          final int _cursorIndexOfRol = CursorUtil.getColumnIndexOrThrow(_cursor, "rol");
          final int _cursorIndexOfFotoId = CursorUtil.getColumnIndexOrThrow(_cursor, "fotoId");
          final UsuarioEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            if (_cursor.isNull(_cursorIndexOfNombre)) {
              _tmpNombre = null;
            } else {
              _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            }
            final String _tmpCorreo;
            if (_cursor.isNull(_cursorIndexOfCorreo)) {
              _tmpCorreo = null;
            } else {
              _tmpCorreo = _cursor.getString(_cursorIndexOfCorreo);
            }
            final String _tmpContrasena;
            if (_cursor.isNull(_cursorIndexOfContrasena)) {
              _tmpContrasena = null;
            } else {
              _tmpContrasena = _cursor.getString(_cursorIndexOfContrasena);
            }
            final String _tmpRol;
            if (_cursor.isNull(_cursorIndexOfRol)) {
              _tmpRol = null;
            } else {
              _tmpRol = _cursor.getString(_cursorIndexOfRol);
            }
            final String _tmpFotoId;
            if (_cursor.isNull(_cursorIndexOfFotoId)) {
              _tmpFotoId = null;
            } else {
              _tmpFotoId = _cursor.getString(_cursorIndexOfFotoId);
            }
            _result = new UsuarioEntity(_tmpId,_tmpNombre,_tmpCorreo,_tmpContrasena,_tmpRol,_tmpFotoId);
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
  public Object getUsuarioById(final int id,
      final Continuation<? super UsuarioEntity> $completion) {
    final String _sql = "SELECT * FROM usuarios WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UsuarioEntity>() {
      @Override
      @Nullable
      public UsuarioEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfCorreo = CursorUtil.getColumnIndexOrThrow(_cursor, "correo");
          final int _cursorIndexOfContrasena = CursorUtil.getColumnIndexOrThrow(_cursor, "contrasena");
          final int _cursorIndexOfRol = CursorUtil.getColumnIndexOrThrow(_cursor, "rol");
          final int _cursorIndexOfFotoId = CursorUtil.getColumnIndexOrThrow(_cursor, "fotoId");
          final UsuarioEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            if (_cursor.isNull(_cursorIndexOfNombre)) {
              _tmpNombre = null;
            } else {
              _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            }
            final String _tmpCorreo;
            if (_cursor.isNull(_cursorIndexOfCorreo)) {
              _tmpCorreo = null;
            } else {
              _tmpCorreo = _cursor.getString(_cursorIndexOfCorreo);
            }
            final String _tmpContrasena;
            if (_cursor.isNull(_cursorIndexOfContrasena)) {
              _tmpContrasena = null;
            } else {
              _tmpContrasena = _cursor.getString(_cursorIndexOfContrasena);
            }
            final String _tmpRol;
            if (_cursor.isNull(_cursorIndexOfRol)) {
              _tmpRol = null;
            } else {
              _tmpRol = _cursor.getString(_cursorIndexOfRol);
            }
            final String _tmpFotoId;
            if (_cursor.isNull(_cursorIndexOfFotoId)) {
              _tmpFotoId = null;
            } else {
              _tmpFotoId = _cursor.getString(_cursorIndexOfFotoId);
            }
            _result = new UsuarioEntity(_tmpId,_tmpNombre,_tmpCorreo,_tmpContrasena,_tmpRol,_tmpFotoId);
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
