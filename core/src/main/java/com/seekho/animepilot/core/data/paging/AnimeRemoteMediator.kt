package com.seekho.animepilot.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.seekho.animepilot.core.data.api.JikanApi
import com.seekho.animepilot.core.data.db.AnimeDatabase
import com.seekho.animepilot.core.data.db.dao.AnimeDao
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import com.seekho.animepilot.core.data.db.entity.RemoteKeys
import com.seekho.animepilot.core.data.mapper.toEntities
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator @javax.inject.Inject constructor(
    private val jikanApi: JikanApi,
    private val database: AnimeDatabase
) : RemoteMediator<Int, AnimeEntity>() {

    private val animeDao: AnimeDao = database.animeDao()

    override suspend fun initialize(): InitializeAction {
        val cachedDataExists = database.withTransaction {
            animeDao.getRemoteKeyByPage(1) != null
        }
        return if (cachedDataExists) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                    nextKey
                }
            }

            val response = jikanApi.getTopAnime(page = page)
            
            if (response.isSuccessful) {
                val animeDtoList = response.body()?.data ?: emptyList()
                val entities = animeDtoList.toEntities()
                val endOfPaginationReached = entities.isEmpty()


                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        animeDao.clearAllAnime()
                        animeDao.clearRemoteKeys()
                    }
                    
                    val prevKey = if (page == 1) null else page - 1
                    val nextKey = if (endOfPaginationReached) null else page + 1
                    

                    val remoteKey = RemoteKeys(
                        page = page,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                    
                    animeDao.insertRemoteKeys(listOf(remoteKey))
                    animeDao.insertAll(entities)
                }

                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } else {
                MediatorResult.Error(
                    HttpException(response)
                )
            }
        } catch (e: IOException) {
            val cachedDataExists = database.withTransaction {
                animeDao.getRemoteKeyByPage(1) != null
            }
            if (cachedDataExists) {
                MediatorResult.Success(endOfPaginationReached = true)
            } else {
                MediatorResult.Error(e)
            }
        } catch (e: HttpException) {
            val cachedDataExists = database.withTransaction {
                animeDao.getRemoteKeyByPage(1) != null
            }
            if (cachedDataExists && loadType != LoadType.REFRESH) {
                MediatorResult.Success(endOfPaginationReached = true)
            } else {
                MediatorResult.Error(e)
            }
        } catch (e: Exception) {
            android.util.Log.e("AnimeRemoteMediator", "Unexpected error: ${e.javaClass.simpleName}", e)
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, AnimeEntity>
    ): RemoteKeys? {
        return state.pages.lastOrNull()?.let { lastPage ->
            lastPage.nextKey?.let { nextPageNumber ->
                val currentPageNumber = nextPageNumber - 1
                animeDao.getRemoteKeyByPage(currentPageNumber)
            } ?: run {
                var maxPage = 1
                for (i in 1..1000) {
                    val key = animeDao.getRemoteKeyByPage(i)
                    if (key != null) {
                        maxPage = i
                    } else {
                        break
                    }
                }
                animeDao.getRemoteKeyByPage(maxPage)
            }
        }
    }
}
