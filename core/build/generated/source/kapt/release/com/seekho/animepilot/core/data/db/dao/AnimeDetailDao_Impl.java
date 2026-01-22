package com.seekho.animepilot.core.data.db.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class AnimeDetailDao_Impl implements AnimeDetailDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<AnimeDetailEntity> __insertAdapterOfAnimeDetailEntity;

  public AnimeDetailDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfAnimeDetailEntity = new EntityInsertAdapter<AnimeDetailEntity>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `anime_detail` (`id`,`title`,`synopsis`,`genres`,`cast`,`trailerUrl`,`rating`,`episodes`,`posterUrl`,`lastUpdated`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final AnimeDetailEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getTitle());
        }
        if (entity.getSynopsis() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getSynopsis());
        }
        if (entity.getGenres() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getGenres());
        }
        if (entity.getCast() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getCast());
        }
        if (entity.getTrailerUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getTrailerUrl());
        }
        if (entity.getRating() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getRating());
        }
        if (entity.getEpisodes() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getEpisodes());
        }
        if (entity.getPosterUrl() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getPosterUrl());
        }
        statement.bindLong(10, entity.getLastUpdated());
      }
    };
  }

  @Override
  public Object insertDetail(final AnimeDetailEntity detail,
      final Continuation<? super Unit> $completion) {
    if (detail == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfAnimeDetailEntity.insert(_connection, detail);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object getDetailById(final int id,
      final Continuation<? super AnimeDetailEntity> $completion) {
    final String _sql = "SELECT * FROM anime_detail WHERE id = ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfSynopsis = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "synopsis");
        final int _columnIndexOfGenres = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "genres");
        final int _columnIndexOfCast = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cast");
        final int _columnIndexOfTrailerUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "trailerUrl");
        final int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
        final int _columnIndexOfEpisodes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "episodes");
        final int _columnIndexOfPosterUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "posterUrl");
        final int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastUpdated");
        final AnimeDetailEntity _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpSynopsis;
          if (_stmt.isNull(_columnIndexOfSynopsis)) {
            _tmpSynopsis = null;
          } else {
            _tmpSynopsis = _stmt.getText(_columnIndexOfSynopsis);
          }
          final String _tmpGenres;
          if (_stmt.isNull(_columnIndexOfGenres)) {
            _tmpGenres = null;
          } else {
            _tmpGenres = _stmt.getText(_columnIndexOfGenres);
          }
          final String _tmpCast;
          if (_stmt.isNull(_columnIndexOfCast)) {
            _tmpCast = null;
          } else {
            _tmpCast = _stmt.getText(_columnIndexOfCast);
          }
          final String _tmpTrailerUrl;
          if (_stmt.isNull(_columnIndexOfTrailerUrl)) {
            _tmpTrailerUrl = null;
          } else {
            _tmpTrailerUrl = _stmt.getText(_columnIndexOfTrailerUrl);
          }
          final Double _tmpRating;
          if (_stmt.isNull(_columnIndexOfRating)) {
            _tmpRating = null;
          } else {
            _tmpRating = _stmt.getDouble(_columnIndexOfRating);
          }
          final Integer _tmpEpisodes;
          if (_stmt.isNull(_columnIndexOfEpisodes)) {
            _tmpEpisodes = null;
          } else {
            _tmpEpisodes = (int) (_stmt.getLong(_columnIndexOfEpisodes));
          }
          final String _tmpPosterUrl;
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null;
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl);
          }
          final long _tmpLastUpdated;
          _tmpLastUpdated = _stmt.getLong(_columnIndexOfLastUpdated);
          _result = new AnimeDetailEntity(_tmpId,_tmpTitle,_tmpSynopsis,_tmpGenres,_tmpCast,_tmpTrailerUrl,_tmpRating,_tmpEpisodes,_tmpPosterUrl,_tmpLastUpdated);
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
  public Flow<AnimeDetailEntity> observeDetailById(final int id) {
    final String _sql = "SELECT * FROM anime_detail WHERE id = ?";
    return FlowUtil.createFlow(__db, false, new String[] {"anime_detail"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTitle = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "title");
        final int _columnIndexOfSynopsis = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "synopsis");
        final int _columnIndexOfGenres = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "genres");
        final int _columnIndexOfCast = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "cast");
        final int _columnIndexOfTrailerUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "trailerUrl");
        final int _columnIndexOfRating = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "rating");
        final int _columnIndexOfEpisodes = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "episodes");
        final int _columnIndexOfPosterUrl = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "posterUrl");
        final int _columnIndexOfLastUpdated = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "lastUpdated");
        final AnimeDetailEntity _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpTitle;
          if (_stmt.isNull(_columnIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _stmt.getText(_columnIndexOfTitle);
          }
          final String _tmpSynopsis;
          if (_stmt.isNull(_columnIndexOfSynopsis)) {
            _tmpSynopsis = null;
          } else {
            _tmpSynopsis = _stmt.getText(_columnIndexOfSynopsis);
          }
          final String _tmpGenres;
          if (_stmt.isNull(_columnIndexOfGenres)) {
            _tmpGenres = null;
          } else {
            _tmpGenres = _stmt.getText(_columnIndexOfGenres);
          }
          final String _tmpCast;
          if (_stmt.isNull(_columnIndexOfCast)) {
            _tmpCast = null;
          } else {
            _tmpCast = _stmt.getText(_columnIndexOfCast);
          }
          final String _tmpTrailerUrl;
          if (_stmt.isNull(_columnIndexOfTrailerUrl)) {
            _tmpTrailerUrl = null;
          } else {
            _tmpTrailerUrl = _stmt.getText(_columnIndexOfTrailerUrl);
          }
          final Double _tmpRating;
          if (_stmt.isNull(_columnIndexOfRating)) {
            _tmpRating = null;
          } else {
            _tmpRating = _stmt.getDouble(_columnIndexOfRating);
          }
          final Integer _tmpEpisodes;
          if (_stmt.isNull(_columnIndexOfEpisodes)) {
            _tmpEpisodes = null;
          } else {
            _tmpEpisodes = (int) (_stmt.getLong(_columnIndexOfEpisodes));
          }
          final String _tmpPosterUrl;
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null;
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl);
          }
          final long _tmpLastUpdated;
          _tmpLastUpdated = _stmt.getLong(_columnIndexOfLastUpdated);
          _result = new AnimeDetailEntity(_tmpId,_tmpTitle,_tmpSynopsis,_tmpGenres,_tmpCast,_tmpTrailerUrl,_tmpRating,_tmpEpisodes,_tmpPosterUrl,_tmpLastUpdated);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
