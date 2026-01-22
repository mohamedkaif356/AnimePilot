package com.seekho.animepilot.core.data.local

import com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveAnimeList(entities: List<AnimeEntity>)
    fun observeAnimeList(): Flow<List<AnimeEntity>>
    suspend fun clearAnimeList()
    
    suspend fun saveAnimeDetail(entity: AnimeDetailEntity)
    suspend fun getAnimeDetail(id: Int): AnimeDetailEntity?
    fun observeAnimeDetail(id: Int): Flow<AnimeDetailEntity?>
}