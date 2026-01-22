package com.seekho.animepilot.core.data.remote

import com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import com.seekho.animepilot.core.domain.model.Result

interface RemoteDataSource {
    suspend fun fetchTopAnime(): Result<List<AnimeEntity>>
    suspend fun fetchAnimeDetail(id: Int): Result<AnimeDetailEntity>
}