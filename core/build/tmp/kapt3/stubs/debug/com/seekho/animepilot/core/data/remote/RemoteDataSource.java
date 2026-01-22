package com.seekho.animepilot.core.data.remote;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\t\u001a\u00020\nH\u00a6@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f\u00c0\u0006\u0003"}, d2 = {"Lcom/seekho/animepilot/core/data/remote/RemoteDataSource;", "", "fetchTopAnime", "Lcom/seekho/animepilot/core/domain/model/Result;", "", "Lcom/seekho/animepilot/core/data/db/entity/AnimeEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchAnimeDetail", "Lcom/seekho/animepilot/core/data/db/entity/AnimeDetailEntity;", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core_debug"})
public abstract interface RemoteDataSource {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchTopAnime(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.seekho.animepilot.core.domain.model.Result<? extends java.util.List<com.seekho.animepilot.core.data.db.entity.AnimeEntity>>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchAnimeDetail(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.seekho.animepilot.core.domain.model.Result<com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity>> $completion);
}