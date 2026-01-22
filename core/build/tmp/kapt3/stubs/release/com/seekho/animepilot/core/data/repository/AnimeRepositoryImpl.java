package com.seekho.animepilot.core.data.repository;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B_\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u001d\u0010\b\u001a\u0019\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n0\t\u00a2\u0006\u0002\b\r\u0012\u001d\u0010\u000e\u001a\u0019\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u000f0\t\u00a2\u0006\u0002\b\r\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0014\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016H\u0016J\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u00162\u0006\u0010\u001c\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R-\u0010\b\u001a\u0019\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n0\t\u00a2\u0006\u0002\b\r8\u0002X\u0083\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0013R-\u0010\u000e\u001a\u0019\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u000f0\t\u00a2\u0006\u0002\b\r8\u0002X\u0083\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0013\u00a8\u0006\u001d"}, d2 = {"Lcom/seekho/animepilot/core/data/repository/AnimeRepositoryImpl;", "Lcom/seekho/animepilot/core/domain/repository/AnimeRepository;", "remoteDataSource", "Lcom/seekho/animepilot/core/data/remote/RemoteDataSource;", "localDataSource", "Lcom/seekho/animepilot/core/data/local/LocalDataSource;", "connectivityObserver", "Lcom/seekho/animepilot/core/util/ConnectivityObserver;", "remoteMediatorFactory", "Lkotlin/Function0;", "Landroidx/paging/RemoteMediator;", "", "Lcom/seekho/animepilot/core/data/db/entity/AnimeEntity;", "Lkotlin/jvm/JvmSuppressWildcards;", "pagingSourceFactory", "Landroidx/paging/PagingSource;", "<init>", "(Lcom/seekho/animepilot/core/data/remote/RemoteDataSource;Lcom/seekho/animepilot/core/data/local/LocalDataSource;Lcom/seekho/animepilot/core/util/ConnectivityObserver;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getRemoteMediatorFactory$annotations", "()V", "getPagingSourceFactory$annotations", "getTopAnimePagingFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "Lcom/seekho/animepilot/core/domain/model/Anime;", "getAnimeDetail", "Lcom/seekho/animepilot/core/domain/model/Result;", "Lcom/seekho/animepilot/core/domain/model/AnimeDetail;", "id", "core_release"})
public final class AnimeRepositoryImpl implements com.seekho.animepilot.core.domain.repository.AnimeRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.seekho.animepilot.core.data.remote.RemoteDataSource remoteDataSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.seekho.animepilot.core.data.local.LocalDataSource localDataSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.seekho.animepilot.core.util.ConnectivityObserver connectivityObserver = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function0<androidx.paging.RemoteMediator<java.lang.Integer, com.seekho.animepilot.core.data.db.entity.AnimeEntity>> remoteMediatorFactory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function0<androidx.paging.PagingSource<java.lang.Integer, com.seekho.animepilot.core.data.db.entity.AnimeEntity>> pagingSourceFactory = null;
    
    @javax.inject.Inject()
    @kotlin.OptIn(markerClass = {androidx.paging.ExperimentalPagingApi.class})
    public AnimeRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.seekho.animepilot.core.data.remote.RemoteDataSource remoteDataSource, @org.jetbrains.annotations.NotNull()
    com.seekho.animepilot.core.data.local.LocalDataSource localDataSource, @org.jetbrains.annotations.NotNull()
    com.seekho.animepilot.core.util.ConnectivityObserver connectivityObserver, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<androidx.paging.RemoteMediator<java.lang.Integer, com.seekho.animepilot.core.data.db.entity.AnimeEntity>> remoteMediatorFactory, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<androidx.paging.PagingSource<java.lang.Integer, com.seekho.animepilot.core.data.db.entity.AnimeEntity>> pagingSourceFactory) {
        super();
    }
    
    @kotlin.jvm.JvmSuppressWildcards()
    @java.lang.Deprecated()
    private static void getRemoteMediatorFactory$annotations() {
    }
    
    @kotlin.jvm.JvmSuppressWildcards()
    @java.lang.Deprecated()
    private static void getPagingSourceFactory$annotations() {
    }
    
    @java.lang.Override()
    @kotlin.OptIn(markerClass = {androidx.paging.ExperimentalPagingApi.class})
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.seekho.animepilot.core.domain.model.Anime>> getTopAnimePagingFlow() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.seekho.animepilot.core.domain.model.Result<com.seekho.animepilot.core.domain.model.AnimeDetail>> getAnimeDetail(int id) {
        return null;
    }
}