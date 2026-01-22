package com.seekho.animepilot.core.data.paging;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u000e\u0010\f\u001a\u00020\rH\u0096@\u00a2\u0006\u0002\u0010\u000eJ*\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0014H\u0082@\u00a2\u0006\u0002\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/seekho/animepilot/core/data/paging/AnimeRemoteMediator;", "Landroidx/paging/RemoteMediator;", "", "Lcom/seekho/animepilot/core/data/db/entity/AnimeEntity;", "jikanApi", "Lcom/seekho/animepilot/core/data/api/JikanApi;", "database", "Lcom/seekho/animepilot/core/data/db/AnimeDatabase;", "<init>", "(Lcom/seekho/animepilot/core/data/api/JikanApi;Lcom/seekho/animepilot/core/data/db/AnimeDatabase;)V", "animeDao", "Lcom/seekho/animepilot/core/data/db/dao/AnimeDao;", "initialize", "Landroidx/paging/RemoteMediator$InitializeAction;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "Landroidx/paging/RemoteMediator$MediatorResult;", "loadType", "Landroidx/paging/LoadType;", "state", "Landroidx/paging/PagingState;", "(Landroidx/paging/LoadType;Landroidx/paging/PagingState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRemoteKeyForLastItem", "Lcom/seekho/animepilot/core/data/db/entity/RemoteKeys;", "(Landroidx/paging/PagingState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core_release"})
@kotlin.OptIn(markerClass = {androidx.paging.ExperimentalPagingApi.class})
public final class AnimeRemoteMediator extends androidx.paging.RemoteMediator<java.lang.Integer, com.seekho.animepilot.core.data.db.entity.AnimeEntity> {
    @org.jetbrains.annotations.NotNull()
    private final com.seekho.animepilot.core.data.api.JikanApi jikanApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.seekho.animepilot.core.data.db.AnimeDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.seekho.animepilot.core.data.db.dao.AnimeDao animeDao = null;
    
    @javax.inject.Inject()
    public AnimeRemoteMediator(@org.jetbrains.annotations.NotNull()
    com.seekho.animepilot.core.data.api.JikanApi jikanApi, @org.jetbrains.annotations.NotNull()
    com.seekho.animepilot.core.data.db.AnimeDatabase database) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object initialize(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.paging.RemoteMediator.InitializeAction> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object load(@org.jetbrains.annotations.NotNull()
    androidx.paging.LoadType loadType, @org.jetbrains.annotations.NotNull()
    androidx.paging.PagingState<java.lang.Integer, com.seekho.animepilot.core.data.db.entity.AnimeEntity> state, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.paging.RemoteMediator.MediatorResult> $completion) {
        return null;
    }
    
    private final java.lang.Object getRemoteKeyForLastItem(androidx.paging.PagingState<java.lang.Integer, com.seekho.animepilot.core.data.db.entity.AnimeEntity> state, kotlin.coroutines.Continuation<? super com.seekho.animepilot.core.data.db.entity.RemoteKeys> $completion) {
        return null;
    }
}