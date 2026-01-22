package com.seekho.animepilot.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.seekho.animepilot.core.data.api.JikanApi
import com.seekho.animepilot.core.data.db.AnimeDatabase
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import com.seekho.animepilot.core.data.db.entity.RemoteKeys
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import com.seekho.animepilot.core.data.api.dto.TopAnimeResponseDto
import com.seekho.animepilot.core.data.api.dto.AnimeDto
import com.seekho.animepilot.core.data.api.dto.ImagesDto
import com.seekho.animepilot.core.data.api.dto.ImageUrlDto

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediatorIntegrationTest {

    private lateinit var database: AnimeDatabase
    private lateinit var remoteMediator: AnimeRemoteMediator
    private lateinit var mockWebServer: MockWebServer
    private lateinit var jikanApi: JikanApi
    private lateinit var gson: Gson

    @Before
    fun setup() {
        // Setup in-memory database
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AnimeDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        // Setup MockWebServer
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // Setup Retrofit with MockWebServer
        gson = Gson()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        jikanApi = retrofit.create(JikanApi::class.java)
        remoteMediator = AnimeRemoteMediator(jikanApi, database)
    }

    @After
    fun tearDown() {
        database.close()
        mockWebServer.shutdown()
    }

    @Test
    fun `load REFRESH loads first page successfully`() = runTest {
        // Given
        val mockResponse = createMockTopAnimeResponse(1, 25)
        mockWebServer.enqueue(MockResponse().setBody(gson.toJson(mockResponse)))

        val pagingState = PagingState<Int, AnimeEntity>(
            pages = emptyList(),
            anchorPosition = null,
            config = androidx.paging.PagingConfig(pageSize = 25),
            leadingPlaceholderCount = 0
        )

        // When
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        // Then
        assertTrue(result is androidx.paging.RemoteMediator.MediatorResult.Success)
        // Verify data was saved (we'd need Flow.first() but this is simplified for integration test)
        val remoteKey = database.animeDao().getRemoteKeyByPage(1)
        assertNotNull(remoteKey)
    }

    @Test
    fun `load APPEND loads next page successfully`() = runTest {
        // Given - First page already loaded
        val firstPageResponse = createMockTopAnimeResponse(1, 25)
        mockWebServer.enqueue(MockResponse().setBody(gson.toJson(firstPageResponse)))
        
        val firstPagingState = PagingState<Int, AnimeEntity>(
            pages = emptyList(),
            anchorPosition = null,
            config = androidx.paging.PagingConfig(pageSize = 25),
            leadingPlaceholderCount = 0
        )
        remoteMediator.load(LoadType.REFRESH, firstPagingState)

        // Setup second page
        val secondPageResponse = createMockTopAnimeResponse(2, 25)
        mockWebServer.enqueue(MockResponse().setBody(gson.toJson(secondPageResponse)))

        val secondPagingState = PagingState<Int, AnimeEntity>(
            pages = listOf(
                androidx.paging.PagingSource.LoadResult.Page(
                    data = (1..25).map { createMockAnimeEntity(it) },
                    prevKey = null,
                    nextKey = 2
                )
            ),
            anchorPosition = 24,
            config = androidx.paging.PagingConfig(pageSize = 25),
            leadingPlaceholderCount = 0
        )

        // When
        val result = remoteMediator.load(LoadType.APPEND, secondPagingState)

        // Then
        assertTrue(result is androidx.paging.RemoteMediator.MediatorResult.Success)
    }

    @Test
    fun `initialize returns SKIP_INITIAL_REFRESH when cached data exists`() = runTest {
        // Given - Insert remote key for page 1
        database.animeDao().insertRemoteKeys(
            listOf(RemoteKeys(page = 1, prevKey = null, nextKey = 2))
        )

        // When
        val action = remoteMediator.initialize()

        // Then
        assertEquals(
            androidx.paging.RemoteMediator.InitializeAction.SKIP_INITIAL_REFRESH,
            action
        )
    }

    @Test
    fun `initialize returns LAUNCH_INITIAL_REFRESH when no cached data`() = runTest {
        // Given - No cached data

        // When
        val action = remoteMediator.initialize()

        // Then
        assertEquals(
            androidx.paging.RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH,
            action
        )
    }

    private fun createMockTopAnimeResponse(page: Int, count: Int): TopAnimeResponseDto {
        val animeList = (1..count).map { index ->
            AnimeDto(
                id = (page - 1) * 25 + index,
                title = "Anime ${(page - 1) * 25 + index}",
                images = ImagesDto(ImageUrlDto("https://example.com/image.jpg")),
                episodes = 12,
                rating = 8.5
            )
        }
        return TopAnimeResponseDto(animeList)
    }

    private fun createMockAnimeEntity(id: Int): AnimeEntity {
        return AnimeEntity(
            id = id,
            title = "Anime $id",
            posterUrl = "https://example.com/image.jpg",
            episodes = 12,
            rating = 8.5
        )
    }
}
