package com.seekho.animepilot.core.data.db.dao;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\tH\'J\u0014\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\u000bH\'J\u000e\u0010\r\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u0016\u00c0\u0006\u0003"}, d2 = {"Lcom/seekho/animepilot/core/data/db/dao/AnimeDao;", "", "insertAll", "", "animes", "", "Lcom/seekho/animepilot/core/data/db/entity/AnimeEntity;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAnime", "Lkotlinx/coroutines/flow/Flow;", "getAllAnimePaged", "Landroidx/paging/PagingSource;", "", "clearAllAnime", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertRemoteKeys", "remoteKeys", "Lcom/seekho/animepilot/core/data/db/entity/RemoteKeys;", "getRemoteKeyByPage", "page", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearRemoteKeys", "core_debug"})
@androidx.room.Dao()
public abstract interface AnimeDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.seekho.animepilot.core.data.db.entity.AnimeEntity> animes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM anime ORDER BY rating DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.seekho.animepilot.core.data.db.entity.AnimeEntity>> getAllAnime();
    
    @androidx.room.Query(value = "SELECT * FROM anime ORDER BY rating DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.paging.PagingSource<java.lang.Integer, com.seekho.animepilot.core.data.db.entity.AnimeEntity> getAllAnimePaged();
    
    @androidx.room.Query(value = "DELETE FROM anime")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearAllAnime(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRemoteKeys(@org.jetbrains.annotations.NotNull()
    java.util.List<com.seekho.animepilot.core.data.db.entity.RemoteKeys> remoteKeys, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM remote_keys WHERE page = :page")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRemoteKeyByPage(int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.seekho.animepilot.core.data.db.entity.RemoteKeys> $completion);
    
    @androidx.room.Query(value = "DELETE FROM remote_keys")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearRemoteKeys(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}