package com.seekho.animepilot.core.domain.repository;

@kotlin.Metadata(mv = {2, 3, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H&J\u001c\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00032\u0006\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b\u00c0\u0006\u0003"}, d2 = {"Lcom/seekho/animepilot/core/domain/repository/AnimeRepository;", "", "getTopAnimePagingFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "Lcom/seekho/animepilot/core/domain/model/Anime;", "getAnimeDetail", "Lcom/seekho/animepilot/core/domain/model/Result;", "Lcom/seekho/animepilot/core/domain/model/AnimeDetail;", "id", "", "core_debug"})
public abstract interface AnimeRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.seekho.animepilot.core.domain.model.Anime>> getTopAnimePagingFlow();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.seekho.animepilot.core.domain.model.Result<com.seekho.animepilot.core.domain.model.AnimeDetail>> getAnimeDetail(int id);
}