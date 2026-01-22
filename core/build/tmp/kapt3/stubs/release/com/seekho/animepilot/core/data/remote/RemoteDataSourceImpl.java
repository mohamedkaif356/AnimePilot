package com.seekho.animepilot.core.data.remote;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/seekho/animepilot/core/data/remote/RemoteDataSourceImpl;", "Lcom/seekho/animepilot/core/data/remote/RemoteDataSource;", "jikanApi", "Lcom/seekho/animepilot/core/data/api/JikanApi;", "<init>", "(Lcom/seekho/animepilot/core/data/api/JikanApi;)V", "fetchTopAnime", "Lcom/seekho/animepilot/core/domain/model/Result;", "", "Lcom/seekho/animepilot/core/data/db/entity/AnimeEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchAnimeDetail", "Lcom/seekho/animepilot/core/data/db/entity/AnimeDetailEntity;", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core_release"})
public final class RemoteDataSourceImpl implements com.seekho.animepilot.core.data.remote.RemoteDataSource {
    @org.jetbrains.annotations.NotNull()
    private final com.seekho.animepilot.core.data.api.JikanApi jikanApi = null;
    
    @javax.inject.Inject()
    public RemoteDataSourceImpl(@org.jetbrains.annotations.NotNull()
    com.seekho.animepilot.core.data.api.JikanApi jikanApi) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object fetchTopAnime(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.seekho.animepilot.core.domain.model.Result<? extends java.util.List<com.seekho.animepilot.core.data.db.entity.AnimeEntity>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object fetchAnimeDetail(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.seekho.animepilot.core.domain.model.Result<com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity>> $completion) {
        return null;
    }
}