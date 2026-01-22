package com.seekho.animepilot.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import androidx.paging.map
import com.seekho.animepilot.core.data.local.LocalDataSource
import com.seekho.animepilot.core.data.mapper.toDomain
import com.seekho.animepilot.core.data.remote.RemoteDataSource
import com.seekho.animepilot.core.domain.model.Anime
import com.seekho.animepilot.core.domain.model.AnimeDetail
import com.seekho.animepilot.core.domain.model.AppError
import com.seekho.animepilot.core.domain.model.Result
import com.seekho.animepilot.core.domain.repository.AnimeRepository
import com.seekho.animepilot.core.util.ConnectivityObserver
import com.seekho.animepilot.core.util.ErrorMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.jvm.JvmSuppressWildcards
import javax.inject.Inject

class AnimeRepositoryImpl @OptIn(ExperimentalPagingApi::class)
@Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val connectivityObserver: ConnectivityObserver,
    @JvmSuppressWildcards private val remoteMediatorFactory: @JvmSuppressWildcards () -> RemoteMediator<Int, com.seekho.animepilot.core.data.db.entity.AnimeEntity>,
    @JvmSuppressWildcards private val pagingSourceFactory: @JvmSuppressWildcards () -> androidx.paging.PagingSource<Int, com.seekho.animepilot.core.data.db.entity.AnimeEntity>
) : AnimeRepository {
    
    @OptIn(ExperimentalPagingApi::class)
    override fun getTopAnimePagingFlow(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false,
                prefetchDistance = 5
            ),
            remoteMediator = remoteMediatorFactory(),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { entity -> entity.toDomain() }
        }
    }
    
    override fun getAnimeDetail(id: Int): Flow<Result<AnimeDetail>> = flow {
        emit(Result.Loading)
        
        val cachedEntity = localDataSource.observeAnimeDetail(id).first()
        if (cachedEntity != null) {
            emit(Result.Success(cachedEntity.toDomain()))
        }

        val isOnline = connectivityObserver.observeConnectivity().first()
        if (isOnline) {
            try {
                when (val remoteResult = remoteDataSource.fetchAnimeDetail(id)) {
                    is Result.Success -> {
                        localDataSource.saveAnimeDetail(remoteResult.data)
                    }
                    is Result.Error -> {
                        if (cachedEntity == null) {
                            emit(remoteResult)
                        }
                    }
                    Result.Loading -> {
                        // Should not happen from remote data source
                    }
                }
            } catch (e: Exception) {
                if (cachedEntity == null) {
                    emit(Result.Error(ErrorMapper.map(e)))
                }
            }
        } else {
            if (cachedEntity == null) {
                emit(Result.Error(AppError.NetworkError.NoInternet))
            }
        }

        localDataSource.observeAnimeDetail(id).collect { entity ->
            if (entity != null) {
                emit(Result.Success(entity.toDomain()))
            }
        }
    }.catch { e ->
        emit(Result.Error(ErrorMapper.map(e)))
    }
}