package com.seekho.animepilot.core.data.local;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0096@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u000fH\u0016J\u000e\u0010\u0010\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0015J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@\u00a2\u0006\u0002\u0010\u0019J\u0018\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/seekho/animepilot/core/data/local/LocalDataSourceImpl;", "Lcom/seekho/animepilot/core/data/local/LocalDataSource;", "animeDao", "Lcom/seekho/animepilot/core/data/db/dao/AnimeDao;", "animeDetailDao", "Lcom/seekho/animepilot/core/data/db/dao/AnimeDetailDao;", "<init>", "(Lcom/seekho/animepilot/core/data/db/dao/AnimeDao;Lcom/seekho/animepilot/core/data/db/dao/AnimeDetailDao;)V", "saveAnimeList", "", "entities", "", "Lcom/seekho/animepilot/core/data/db/entity/AnimeEntity;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAnimeList", "Lkotlinx/coroutines/flow/Flow;", "clearAnimeList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveAnimeDetail", "entity", "Lcom/seekho/animepilot/core/data/db/entity/AnimeDetailEntity;", "(Lcom/seekho/animepilot/core/data/db/entity/AnimeDetailEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAnimeDetail", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAnimeDetail", "core_release"})
public final class LocalDataSourceImpl implements com.seekho.animepilot.core.data.local.LocalDataSource {
    @org.jetbrains.annotations.NotNull()
    private final com.seekho.animepilot.core.data.db.dao.AnimeDao animeDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.seekho.animepilot.core.data.db.dao.AnimeDetailDao animeDetailDao = null;
    
    @javax.inject.Inject()
    public LocalDataSourceImpl(@org.jetbrains.annotations.NotNull()
    com.seekho.animepilot.core.data.db.dao.AnimeDao animeDao, @org.jetbrains.annotations.NotNull()
    com.seekho.animepilot.core.data.db.dao.AnimeDetailDao animeDetailDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveAnimeList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.seekho.animepilot.core.data.db.entity.AnimeEntity> entities, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.seekho.animepilot.core.data.db.entity.AnimeEntity>> observeAnimeList() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object clearAnimeList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveAnimeDetail(@org.jetbrains.annotations.NotNull()
    com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAnimeDetail(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity> observeAnimeDetail(int id) {
        return null;
    }
}