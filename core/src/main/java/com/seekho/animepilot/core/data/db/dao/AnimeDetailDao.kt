package com.seekho.animepilot.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDetailDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: AnimeDetailEntity)
    
    @Query("SELECT * FROM anime_detail WHERE id = :id")
    suspend fun getDetailById(id: Int): AnimeDetailEntity?
    
    @Query("SELECT * FROM anime_detail WHERE id = :id")
    fun observeDetailById(id: Int): Flow<AnimeDetailEntity?>
}