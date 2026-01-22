package com.seekho.animepilot.core.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import com.seekho.animepilot.core.data.db.entity.RemoteKeys
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(animes: List<AnimeEntity>)
    
    @Query("SELECT * FROM anime ORDER BY rating DESC")
    fun getAllAnime(): Flow<List<AnimeEntity>>
    
    @Query("SELECT * FROM anime ORDER BY rating DESC")
    fun getAllAnimePaged(): PagingSource<Int, AnimeEntity>
    
    @Query("DELETE FROM anime")
    suspend fun clearAllAnime()
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remoteKeys: List<RemoteKeys>)
    
    @Query("SELECT * FROM remote_keys WHERE page = :page")
    suspend fun getRemoteKeyByPage(page: Int): RemoteKeys?
    
    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}