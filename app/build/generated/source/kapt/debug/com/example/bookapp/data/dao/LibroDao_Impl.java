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
public final class LibroDao_Impl implements LibroDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LibroEntity> __insertionAdapterOfLibroEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<LibroEntity> __deletionAdapterOfLibroEntity;

  private final EntityDeletionOrUpdateAdapter<LibroEntity> __updateAdapterOfLibroEntity;

  public LibroDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLibroEntity = new EntityInsertionAdapter<LibroEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `libros` (`id`,`titulo`,`autor`,`isbn`,`categoria`,`editorial`,`ejemplares`,`valor`,`estado`,`portadaUrl`,`isPlaceholder`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LibroEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitulo() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitulo());
        }
        if (entity.getAutor() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAutor());
        }
        if (entity.getIsbn() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIsbn());
        }
        if (entity.getCategoria() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCategoria());
        }
        if (entity.getEditorial() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEditorial());
        }
        statement.bindLong(7, entity.getEjemplares());
        statement.bindDouble(8, entity.getValor());
        final String _tmp = __converters.fromLibroEstado(entity.getEstado());
        if (_tmp == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp);
        }
        if (entity.getPortadaUrl() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getPortadaUrl());
        }
        final int _tmp_1 = entity.isPlaceholder() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
      }
    };
    this.__deletionAdapterOfLibroEntity = new EntityDeletionOrUpdateAdapter<LibroEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `libros` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LibroEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfLibroEntity = new EntityDeletionOrUpdateAdapter<LibroEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `libros` SET `id` = ?,`titulo` = ?,`autor` = ?,`isbn` = ?,`categoria` = ?,`editorial` = ?,`ejemplares` = ?,`valor` = ?,`estado` = ?,`portadaUrl` = ?,`isPlaceholder` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LibroEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitulo() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitulo());
        }
        if (entity.getAutor() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAutor());
        }
        if (entity.getIsbn() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIsbn());
        }
        if (entity.getCategoria() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCategoria());
        }
        if (entity.getEditorial() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEditorial());
        }
        statement.bindLong(7, entity.getEjemplares());
        statement.bindDouble(8, entity.getValor());
        final String _tmp = __converters.fromLibroEstado(entity.getEstado());
        if (_tmp == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp);
        }
        if (entity.getPortadaUrl() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getPortadaUrl());
        }
        final int _tmp_1 = entity.isPlaceholder() ? 1 : 0;
        statement.bindLong(11, _tmp_1);
        statement.bindLong(12, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final LibroEntity libro, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfLibroEntity.insertAndReturnId(libro);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object delete(final LibroEntity libro, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfLibroEntity.handle(libro);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object update(final LibroEntity libro, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLibroEntity.handle(libro);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<LibroEntity>> getAllLibros() {
    final String _sql = "SELECT * FROM libros";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"libros"}, new Callable<List<LibroEntity>>() {
      @Override
      @NonNull
      public List<LibroEntity> call() throws Exception {
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
          final int _cursorIndexOfPortadaUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "portadaUrl");
          final int _cursorIndexOfIsPlaceholder = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlaceholder");
          final List<LibroEntity> _result = new ArrayList<LibroEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LibroEntity _item;
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
            final String _tmpPortadaUrl;
            if (_cursor.isNull(_cursorIndexOfPortadaUrl)) {
              _tmpPortadaUrl = null;
            } else {
              _tmpPortadaUrl = _cursor.getString(_cursorIndexOfPortadaUrl);
            }
            final boolean _tmpIsPlaceholder;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlaceholder);
            _tmpIsPlaceholder = _tmp_1 != 0;
            _item = new LibroEntity(_tmpId,_tmpTitulo,_tmpAutor,_tmpIsbn,_tmpCategoria,_tmpEditorial,_tmpEjemplares,_tmpValor,_tmpEstado,_tmpPortadaUrl,_tmpIsPlaceholder);
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
  public Object getLibroById(final int id, final Continuation<? super LibroEntity> arg1) {
    final String _sql = "SELECT * FROM libros WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<LibroEntity>() {
      @Override
      @Nullable
      public LibroEntity call() throws Exception {
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
          final int _cursorIndexOfPortadaUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "portadaUrl");
          final int _cursorIndexOfIsPlaceholder = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlaceholder");
          final LibroEntity _result;
          if (_cursor.moveToFirst()) {
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
            final String _tmpPortadaUrl;
            if (_cursor.isNull(_cursorIndexOfPortadaUrl)) {
              _tmpPortadaUrl = null;
            } else {
              _tmpPortadaUrl = _cursor.getString(_cursorIndexOfPortadaUrl);
            }
            final boolean _tmpIsPlaceholder;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlaceholder);
            _tmpIsPlaceholder = _tmp_1 != 0;
            _result = new LibroEntity(_tmpId,_tmpTitulo,_tmpAutor,_tmpIsbn,_tmpCategoria,_tmpEditorial,_tmpEjemplares,_tmpValor,_tmpEstado,_tmpPortadaUrl,_tmpIsPlaceholder);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<LibroEntity>> searchLibros(final String query) {
    final String _sql = "SELECT * FROM libros WHERE titulo LIKE '%' || ? || '%' OR autor LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 2;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"libros"}, new Callable<List<LibroEntity>>() {
      @Override
      @NonNull
      public List<LibroEntity> call() throws Exception {
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
          final int _cursorIndexOfPortadaUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "portadaUrl");
          final int _cursorIndexOfIsPlaceholder = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlaceholder");
          final List<LibroEntity> _result = new ArrayList<LibroEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LibroEntity _item;
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
            final String _tmpPortadaUrl;
            if (_cursor.isNull(_cursorIndexOfPortadaUrl)) {
              _tmpPortadaUrl = null;
            } else {
              _tmpPortadaUrl = _cursor.getString(_cursorIndexOfPortadaUrl);
            }
            final boolean _tmpIsPlaceholder;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlaceholder);
            _tmpIsPlaceholder = _tmp_1 != 0;
            _item = new LibroEntity(_tmpId,_tmpTitulo,_tmpAutor,_tmpIsbn,_tmpCategoria,_tmpEditorial,_tmpEjemplares,_tmpValor,_tmpEstado,_tmpPortadaUrl,_tmpIsPlaceholder);
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
