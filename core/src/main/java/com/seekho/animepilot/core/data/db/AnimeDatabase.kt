package com.seekho.animepilot.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seekho.animepilot.core.data.db.converter.StringListTypeConverter
import com.seekho.animepilot.core.data.db.dao.AnimeDao
import com.seekho.animepilot.core.data.db.dao.AnimeDetailDao
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity
import com.seekho.animepilot.core.data.db.entity.RemoteKeys

@Database(
    entities = [AnimeEntity::class, AnimeDetailEntity::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(StringListTypeConverter::class)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
    abstract fun animeDetailDao(): AnimeDetailDao
}