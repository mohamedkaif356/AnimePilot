package com.seekho.animepilot.core.data.local

import com.seekho.animepilot.core.data.db.dao.AnimeDao
import com.seekho.animepilot.core.data.db.dao.AnimeDetailDao
import com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val animeDao: AnimeDao,
    private val animeDetailDao: AnimeDetailDao
) : LocalDataSource {
    
    override suspend fun saveAnimeList(entities: List<AnimeEntity>) {
        animeDao.insertAll(entities)
    }
    
    override fun observeAnimeList(): Flow<List<AnimeEntity>> {
        return animeDao.getAllAnime()
    }
    
    override suspend fun clearAnimeList() {
        animeDao.clearAllAnime()
    }
    
    override suspend fun saveAnimeDetail(entity: AnimeDetailEntity) {
        animeDetailDao.insertDetail(entity)
    }
    
    override suspend fun getAnimeDetail(id: Int): AnimeDetailEntity? {
        return animeDetailDao.getDetailById(id)
    }
    
    override fun observeAnimeDetail(id: Int): Flow<AnimeDetailEntity?> {
        return animeDetailDao.observeDetailById(id)
    }
}