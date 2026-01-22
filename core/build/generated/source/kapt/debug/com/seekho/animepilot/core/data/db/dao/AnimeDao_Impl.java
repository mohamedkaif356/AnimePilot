package com.seekho.animepilot.core.data.db.dao;

import androidx.annotation.NonNull;
import androidx.paging.PagingSource;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomRawQuery;
import androidx.room.coroutines.FlowUtil;
import androidx.room.paging.LimitOffsetPagingSource;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.seekho.animepilot.core.data.db.entity.AnimeEntity;
import com.seekho.animepilot.core.data.db.entity.RemoteKeys;
import java.lang.Class;
import java.lang.Double;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class AnimeDao_Impl implements AnimeDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<AnimeEntity> __insertAdapterOfAnimeEntity;

  private final EntityInsertAdapter<RemoteKeys> __insertAdapterOfRemoteKeys;

  public AnimeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfAnimeEntity = new EntityInsertAdapter<AnimeEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `anime` (`id`,`title`,`posterUrl`,`episodes`,`rating`,`lastUpdated`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final AnimeEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getPosterUrl() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getPosterUrl());
        }
        if (entity.getEpisodes() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getEpisodes());
        }
        if (entity.getRating() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getRating());
        }
        statement.bindLong(6, entity.getLastUpdated());
      }
    };
    this.__insertAdapterOfRemoteKeys = new EntityInsertAdapter<RemoteKeys>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `remote_keys` (`page`,`prevKey`,`nextKey`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final RemoteKeys entity) {
        statement.bindLong(1, entity.getPage());
        if (entity.getPrevKey() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.getPrevKey());
        }
        if (entity.getNextKey() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getNextKey());
        }
      }
    };
  }

  @Override
  public Object insertAll(final List<AnimeEntity> animes,
      final Continuation<? super Unit> $completion) {
    if (animes == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfAnimeEntity.insert(_connection, animes);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object insertRemoteKeys(final List<RemoteKeys> remoteKeys,
      final Continuation<? super Unit> $completion) {
    if (remoteKeys == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfRemoteKeys.insert(_connection, remoteKeys);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<AnimeEntity>> getAllAnime() {
    final String _sql = "SELECT * FROM anime ORDER BY rating DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"anime"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfPosterUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "posterUrl");
        final int _columnIndexOfEpisodes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "episodes");
        final int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
        final int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastUpdated");
        final List<AnimeEntity> _result = new ArrayList<AnimeEntity>();
        while (_stmt.step()) {
          final AnimeEntity _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpPosterUrl;
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null;
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl);
          }
          final Integer _tmpEpisodes;
          if (_stmt.isNull(_columnIndexOfEpisodes)) {
            _tmpEpisodes = null;
          } else {
            _tmpEpisodes = (int) (_stmt.getLong(_columnIndexOfEpisodes));
          }
          final Double _tmpRating;
          if (_stmt.isNull(_columnIndexOfRating)) {
            _tmpRating = null;
          } else {
            _tmpRating = _stmt.getDouble(_columnIndexOfRating);
          }
          final long _tmpLastUpdated;
          _tmpLastUpdated = _stmt.getLong(_columnIndexOfLastUpdated);
          _item = new AnimeEntity(_tmpId,_tmpTitle,_tmpPosterUrl,_tmpEpisodes,_tmpRating,_tmpLastUpdated);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public PagingSource<Integer, AnimeEntity> getAllAnimePaged() {
    final String _sql = "SELECT * FROM anime ORDER BY rating DESC";
    final RoomRawQuery _rawQuery = new RoomRawQuery(_sql);
    return new LimitOffsetPagingSource<AnimeEntity>(_rawQuery, __db, "anime") {
      @Override
      protected Object convertRows(final RoomRawQuery limitOffsetQuery, final int itemCount,
          final Continuation<? super List<? extends AnimeEntity>> $completion) {
        return DBUtil.performSuspending(__db, true, false, (_connection) -> {
          final SQLiteStatement _stmt = _connection.prepare(limitOffsetQuery.getSql());
          limitOffsetQuery.getBindingFunction().invoke(_stmt);
          try {
            final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
            final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
            final int _columnIndexOfPosterUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "posterUrl");
            final int _columnIndexOfEpisodes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "episodes");
            final int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
            final int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastUpdated");
            final List<AnimeEntity> _result = new ArrayList<AnimeEntity>();
            while (_stmt.step()) {
              final AnimeEntity _item;
              final int _tmpId;
              _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
              final String _tmpTitle;
              if (_stmt.isNull(_columnIndexOfTitle)) {
                _tmpTitle = null;
              } else {
                _tmpTitle = _stmt.getText(_columnIndexOfTitle);
              }
              final String _tmpPosterUrl;
              if (_stmt.isNull(_columnIndexOfPosterUrl)) {
                _tmpPosterUrl = null;
              } else {
                _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl);
              }
              final Integer _tmpEpisodes;
              if (_stmt.isNull(_columnIndexOfEpisodes)) {
                _tmpEpisodes = null;
              } else {
                _tmpEpisodes = (int) (_stmt.getLong(_columnIndexOfEpisodes));
              }
              final Double _tmpRating;
              if (_stmt.isNull(_columnIndexOfRating)) {
                _tmpRating = null;
              } else {
                _tmpRating = _stmt.getDouble(_columnIndexOfRating);
              }
              final long _tmpLastUpdated;
              _tmpLastUpdated = _stmt.getLong(_columnIndexOfLastUpdated);
              _item = new AnimeEntity(_tmpId,_tmpTitle,_tmpPosterUrl,_tmpEpisodes,_tmpRating,_tmpLastUpdated);
              _result.add(_item);
            }
            return _result;
          } finally {
            _stmt.close();
          }
        }, $completion);
      }
    };
  }

  @Override
  public Object getRemoteKeyByPage(final int page,
      final Continuation<? super RemoteKeys> $completion) {
    final String _sql = "SELECT * FROM remote_keys WHERE page = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, page);
        final int _columnIndexOfPage = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "page");
        final int _columnIndexOfPrevKey = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "prevKey");
        final int _columnIndexOfNextKey = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nextKey");
        final RemoteKeys _result;
        if (_stmt.step()) {
          final int _tmpPage;
          _tmpPage = (int) (_stmt.getLong(_columnIndexOfPage));
          final Integer _tmpPrevKey;
          if (_stmt.isNull(_columnIndexOfPrevKey)) {
            _tmpPrevKey = null;
          } else {
            _tmpPrevKey = (int) (_stmt.getLong(_columnIndexOfPrevKey));
          }
          final Integer _tmpNextKey;
          if (_stmt.isNull(_columnIndexOfNextKey)) {
            _tmpNextKey = null;
          } else {
            _tmpNextKey = (int) (_stmt.getLong(_columnIndexOfNextKey));
          }
          _result = new RemoteKeys(_tmpPage,_tmpPrevKey,_tmpNextKey);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object clearAllAnime(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM anime";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object clearRemoteKeys(final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM remote_keys";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
