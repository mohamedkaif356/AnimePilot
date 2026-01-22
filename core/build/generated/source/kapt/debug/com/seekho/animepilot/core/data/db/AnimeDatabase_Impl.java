package com.seekho.animepilot.core.data.db;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.seekho.animepilot.core.data.db.dao.AnimeDao;
import com.seekho.animepilot.core.data.db.dao.AnimeDao_Impl;
import com.seekho.animepilot.core.data.db.dao.AnimeDetailDao;
import com.seekho.animepilot.core.data.db.dao.AnimeDetailDao_Impl;
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
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class AnimeDatabase_Impl extends AnimeDatabase {
  private volatile AnimeDao _animeDao;

  private volatile AnimeDetailDao _animeDetailDao;

  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(2, "857261f58ccd5bffdec77b121df4c96f", "56a4992062c72182215e01cb1fb6915c") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `anime` (`id` INTEGER NOT NULL, `title` TEXT NOT NULL, `posterUrl` TEXT, `episodes` INTEGER, `rating` REAL, `lastUpdated` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_anime_rating` ON `anime` (`rating`)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `anime_detail` (`id` INTEGER NOT NULL, `title` TEXT NOT NULL, `synopsis` TEXT NOT NULL, `genres` TEXT NOT NULL, `cast` TEXT NOT NULL, `trailerUrl` TEXT, `rating` REAL, `episodes` INTEGER, `posterUrl` TEXT, `lastUpdated` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `remote_keys` (`page` INTEGER NOT NULL, `prevKey` INTEGER, `nextKey` INTEGER, PRIMARY KEY(`page`))");
        SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_remote_keys_page` ON `remote_keys` (`page`)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '857261f58ccd5bffdec77b121df4c96f')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `anime`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `anime_detail`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `remote_keys`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsAnime = new HashMap<String, TableInfo.Column>(6);
        _columnsAnime.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("posterUrl", new TableInfo.Column("posterUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("episodes", new TableInfo.Column("episodes", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("rating", new TableInfo.Column("rating", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("lastUpdated", new TableInfo.Column("lastUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysAnime = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesAnime = new HashSet<TableInfo.Index>(1);
        _indicesAnime.add(new TableInfo.Index("index_anime_rating", false, Arrays.asList("rating"), Arrays.asList("ASC")));
        final TableInfo _infoAnime = new TableInfo("anime", _columnsAnime, _foreignKeysAnime, _indicesAnime);
        final TableInfo _existingAnime = TableInfo.read(connection, "anime");
        if (!_infoAnime.equals(_existingAnime)) {
          return new RoomOpenDelegate.ValidationResult(false, "anime(com.seekho.animepilot.core.data.db.entity.AnimeEntity).\n"
                  + " Expected:\n" + _infoAnime + "\n"
                  + " Found:\n" + _existingAnime);
        }
        final Map<String, TableInfo.Column> _columnsAnimeDetail = new HashMap<String, TableInfo.Column>(10);
        _columnsAnimeDetail.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeDetail.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeDetail.put("synopsis", new TableInfo.Column("synopsis", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeDetail.put("genres", new TableInfo.Column("genres", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeDetail.put("cast", new TableInfo.Column("cast", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeDetail.put("trailerUrl", new TableInfo.Column("trailerUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeDetail.put("rating", new TableInfo.Column("rating", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeDetail.put("episodes", new TableInfo.Column("episodes", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeDetail.put("posterUrl", new TableInfo.Column("posterUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnimeDetail.put("lastUpdated", new TableInfo.Column("lastUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysAnimeDetail = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesAnimeDetail = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAnimeDetail = new TableInfo("anime_detail", _columnsAnimeDetail, _foreignKeysAnimeDetail, _indicesAnimeDetail);
        final TableInfo _existingAnimeDetail = TableInfo.read(connection, "anime_detail");
        if (!_infoAnimeDetail.equals(_existingAnimeDetail)) {
          return new RoomOpenDelegate.ValidationResult(false, "anime_detail(com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity).\n"
                  + " Expected:\n" + _infoAnimeDetail + "\n"
                  + " Found:\n" + _existingAnimeDetail);
        }
        final Map<String, TableInfo.Column> _columnsRemoteKeys = new HashMap<String, TableInfo.Column>(3);
        _columnsRemoteKeys.put("page", new TableInfo.Column("page", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRemoteKeys.put("prevKey", new TableInfo.Column("prevKey", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRemoteKeys.put("nextKey", new TableInfo.Column("nextKey", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysRemoteKeys = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesRemoteKeys = new HashSet<TableInfo.Index>(1);
        _indicesRemoteKeys.add(new TableInfo.Index("index_remote_keys_page", true, Arrays.asList("page"), Arrays.asList("ASC")));
        final TableInfo _infoRemoteKeys = new TableInfo("remote_keys", _columnsRemoteKeys, _foreignKeysRemoteKeys, _indicesRemoteKeys);
        final TableInfo _existingRemoteKeys = TableInfo.read(connection, "remote_keys");
        if (!_infoRemoteKeys.equals(_existingRemoteKeys)) {
          return new RoomOpenDelegate.ValidationResult(false, "remote_keys(com.seekho.animepilot.core.data.db.entity.RemoteKeys).\n"
                  + " Expected:\n" + _infoRemoteKeys + "\n"
                  + " Found:\n" + _existingRemoteKeys);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "anime", "anime_detail", "remote_keys");
  }

  @Override
  public void clearAllTables() {
    super.performClear(false, "anime", "anime_detail", "remote_keys");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AnimeDao.class, AnimeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AnimeDetailDao.class, AnimeDetailDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
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
  public AnimeDao animeDao() {
    if (_animeDao != null) {
      return _animeDao;
    } else {
      synchronized(this) {
        if(_animeDao == null) {
          _animeDao = new AnimeDao_Impl(this);
        }
        return _animeDao;
      }
    }
  }

  @Override
  public AnimeDetailDao animeDetailDao() {
    if (_animeDetailDao != null) {
      return _animeDetailDao;
    } else {
      synchronized(this) {
        if(_animeDetailDao == null) {
          _animeDetailDao = new AnimeDetailDao_Impl(this);
        }
        return _animeDetailDao;
      }
    }
  }
}
