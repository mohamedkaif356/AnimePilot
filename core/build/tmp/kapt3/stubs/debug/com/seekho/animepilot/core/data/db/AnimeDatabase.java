package com.seekho.animepilot.core.data.db;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/seekho/animepilot/core/data/db/AnimeDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "animeDao", "Lcom/seekho/animepilot/core/data/db/dao/AnimeDao;", "animeDetailDao", "Lcom/seekho/animepilot/core/data/db/dao/AnimeDetailDao;", "core_debug"})
@androidx.room.Database(entities = {com.seekho.animepilot.core.data.db.entity.AnimeEntity.class, com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity.class, com.seekho.animepilot.core.data.db.entity.RemoteKeys.class}, version = 2, exportSchema = false)
@androidx.room.TypeConverters(value = {com.seekho.animepilot.core.data.db.converter.StringListTypeConverter.class})
public abstract class AnimeDatabase extends androidx.room.RoomDatabase {
    
    public AnimeDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.seekho.animepilot.core.data.db.dao.AnimeDao animeDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.seekho.animepilot.core.data.db.dao.AnimeDetailDao animeDetailDao();
}