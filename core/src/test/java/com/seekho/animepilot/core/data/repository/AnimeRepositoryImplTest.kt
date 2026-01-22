package com.seekho.animepilot.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.google.gson.Gson
import com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import com.seekho.animepilot.core.data.local.LocalDataSource
import com.seekho.animepilot.core.data.mapper.EntityMapper
import com.seekho.animepilot.core.data.mapper.toDomain
import com.seekho.animepilot.core.data.remote.RemoteDataSource
import com.seekho.animepilot.core.domain.model.AnimeDetail
import com.seekho.animepilot.core.domain.model.AppError
import com.seekho.animepilot.core.domain.model.Result
import com.seekho.animepilot.core.util.ConnectivityObserver
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalPagingApi::class)
class AnimeRepositoryImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private lateinit var connectivityObserver: ConnectivityObserver
    private lateinit var repository: AnimeRepositoryImpl

    @Before
    fun setup() {
        // Initialize Gson for EntityMapper
        EntityMapper.setGson(Gson())
        
        remoteDataSource = mockk()
        localDataSource = mockk()
        connectivityObserver = mockk()
        
        // Create mock factories that return simple implementations
        val remoteMediatorFactory: () -> RemoteMediator<Int, AnimeEntity> = {
            mockk<RemoteMediator<Int, AnimeEntity>>(relaxed = true)
        }
        val pagingSourceFactory: () -> androidx.paging.PagingSource<Int, AnimeEntity> = {
            mockk<androidx.paging.PagingSource<Int, AnimeEntity>>(relaxed = true)
        }
        
        repository = AnimeRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            connectivityObserver = connectivityObserver,
            remoteMediatorFactory = remoteMediatorFactory,
            pagingSourceFactory = pagingSourceFactory
        )
    }

    @Test
    fun `getAnimeDetail returns cached data first when available`() = runTest {
        // Given
        val cachedEntity = createAnimeDetailEntity(1, "Cached Anime")
        val domainDetail = cachedEntity.toDomain()
        every { localDataSource.observeAnimeDetail(1) } returns flowOf(cachedEntity, null)
        every { connectivityObserver.observeConnectivity() } returns flowOf(true)

        // When
        val results = mutableListOf<Result<AnimeDetail>>()
        repository.getAnimeDetail(1).collect {
            results.add(it)
            if (results.size >= 2) return@collect // Collect Loading and first Success
        }

        // Then
        assertTrue(results.isNotEmpty())
        val successResult = results.firstOrNull { it is Result.Success } as? Result.Success
        assertTrue(successResult != null)
        assertEquals(domainDetail.id, successResult?.data?.id)
        assertEquals(domainDetail.title, successResult?.data?.title)
    }

    @Test
    fun `getAnimeDetail emits Loading first`() = runTest {
        // Given
        every { localDataSource.observeAnimeDetail(1) } returns flowOf(null)
        every { connectivityObserver.observeConnectivity() } returns flowOf(true)
        coEvery { remoteDataSource.fetchAnimeDetail(1) } returns Result.Error(AppError.NetworkError.NoInternet)

        // When
        val result = repository.getAnimeDetail(1).first()

        // Then
        assertTrue(result is Result.Loading)
    }

    @Test
    fun `getAnimeDetail returns error when offline and no cache`() = runTest {
        // Given
        every { localDataSource.observeAnimeDetail(1) } returns flowOf(null)
        every { connectivityObserver.observeConnectivity() } returns flowOf(false)

        // When
        val results = mutableListOf<Result<AnimeDetail>>()
        repository.getAnimeDetail(1).collect { results.add(it) }

        // Then - should emit Loading, then Error
        assertTrue(results.isNotEmpty())
        assertTrue(results[0] is Result.Loading)
        val errorResult = results.find { it is Result.Error } as? Result.Error
        assertTrue(errorResult != null)
        assertTrue(errorResult?.error is AppError.NetworkError.NoInternet)
    }

    @Test
    fun `getAnimeDetail saves remote data and emits success`() = runTest {
        // Given
        val remoteEntity = createAnimeDetailEntity(1, "Remote Anime")
        every { localDataSource.observeAnimeDetail(1) } returns flowOf(null, remoteEntity)
        every { connectivityObserver.observeConnectivity() } returns flowOf(true)
        coEvery { remoteDataSource.fetchAnimeDetail(1) } returns Result.Success(remoteEntity)
        coEvery { localDataSource.saveAnimeDetail(remoteEntity) } returns Unit

        // When
        val results = mutableListOf<Result<AnimeDetail>>()
        repository.getAnimeDetail(1).collect { 
            results.add(it)
            if (results.size >= 2) return@collect // Stop after Loading and Success
        }

        // Then
        assertTrue(results.isNotEmpty())
        assertTrue(results[0] is Result.Loading)
        val successResult = results.find { it is Result.Success } as? Result.Success
        assertTrue(successResult != null)
        assertEquals(1, successResult?.data?.id)
    }

    @Test
    fun `getAnimeDetail does not emit error when cache exists and remote fails`() = runTest {
        // Given
        val cachedEntity = createAnimeDetailEntity(1, "Cached Anime")
        every { localDataSource.observeAnimeDetail(1) } returns flowOf(cachedEntity)
        every { connectivityObserver.observeConnectivity() } returns flowOf(true)
        coEvery { remoteDataSource.fetchAnimeDetail(1) } returns Result.Error(AppError.NetworkError.HttpError(500))

        // When
        val results = mutableListOf<Result<AnimeDetail>>()
        repository.getAnimeDetail(1).collect {
            results.add(it)
            if (results.size >= 2) return@collect // Stop after Loading and Success
        }

        // Then - should not emit error, only Loading and Success
        val errorResults = results.filterIsInstance<Result.Error>()
        assertTrue(errorResults.isEmpty())
        val successResults = results.filterIsInstance<Result.Success<AnimeDetail>>()
        assertTrue(successResults.isNotEmpty())
    }

    private fun createAnimeDetailEntity(id: Int, title: String): AnimeDetailEntity {
        return AnimeDetailEntity(
            id = id,
            title = title,
            synopsis = "Test synopsis",
            genres = "[]",
            cast = "[]",
            trailerUrl = null,
            rating = 8.5,
            episodes = 12,
            posterUrl = "https://example.com/image.jpg"
        )
    }
}
