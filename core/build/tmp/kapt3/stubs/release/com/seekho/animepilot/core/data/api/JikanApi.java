package com.seekho.animepilot.core.data.api;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f\u00c0\u0006\u0003"}, d2 = {"Lcom/seekho/animepilot/core/data/api/JikanApi;", "", "getTopAnime", "Lretrofit2/Response;", "Lcom/seekho/animepilot/core/data/api/dto/TopAnimeResponseDto;", "page", "", "(Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAnimeDetail", "Lcom/seekho/animepilot/core/data/api/dto/AnimeDetailResponseDto;", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core_release"})
public abstract interface JikanApi {
    
    @retrofit2.http.GET(value = "top/anime")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTopAnime(@retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.seekho.animepilot.core.data.api.dto.TopAnimeResponseDto>> $completion);
    
    @retrofit2.http.GET(value = "anime/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAnimeDetail(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.seekho.animepilot.core.data.api.dto.AnimeDetailResponseDto>> $completion);
    
    @kotlin.Metadata(mv = {2, 3, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}