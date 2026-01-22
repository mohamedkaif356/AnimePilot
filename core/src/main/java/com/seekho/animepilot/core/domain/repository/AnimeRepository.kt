package com.seekho.animepilot.core.domain.repository

import androidx.paging.PagingData
import com.seekho.animepilot.core.domain.model.Anime
import com.seekho.animepilot.core.domain.model.AnimeDetail
import com.seekho.animepilot.core.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getTopAnimePagingFlow(): Flow<PagingData<Anime>>
    fun getAnimeDetail(id: Int): Flow<Result<AnimeDetail>>
}