package com.seekho.animepilot.core.data.db.dao;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\f2\u0006\u0010\b\u001a\u00020\tH\'\u00a8\u0006\r\u00c0\u0006\u0003"}, d2 = {"Lcom/seekho/animepilot/core/data/db/dao/AnimeDetailDao;", "", "insertDetail", "", "detail", "Lcom/seekho/animepilot/core/data/db/entity/AnimeDetailEntity;", "(Lcom/seekho/animepilot/core/data/db/entity/AnimeDetailEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDetailById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeDetailById", "Lkotlinx/coroutines/flow/Flow;", "core_release"})
@androidx.room.Dao()
public abstract interface AnimeDetailDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertDetail(@org.jetbrains.annotations.NotNull()
    com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity detail, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM anime_detail WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDetailById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM anime_detail WHERE id = :id")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity> observeDetailById(int id);
}