package com.seekho.animepilot.core.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.seekho.animepilot.core.data.db.AnimeDatabase
import com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class LocalDataSourceImplTest {

    private lateinit var database: AnimeDatabase
    private lateinit var localDataSource: LocalDataSourceImpl

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AnimeDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        localDataSource = LocalDataSourceImpl(
            animeDao = database.animeDao(),
            animeDetailDao = database.animeDetailDao()
        )
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `saveAnimeList and observeAnimeList work correctly`() = runTest {
        // Given
        val entities = listOf(
            createAnimeEntity(1, "Anime 1"),
            createAnimeEntity(2, "Anime 2")
        )

        // When
        localDataSource.saveAnimeList(entities)
        val result = localDataSource.observeAnimeList().first()

        // Then
        assertEquals(2, result.size)
        assertTrue(result.any { it.id == 1 && it.title == "Anime 1" })
        assertTrue(result.any { it.id == 2 && it.title == "Anime 2" })
    }


    @Test
    fun `clearAnimeList removes all anime`() = runTest {
        // Given
        val entities = listOf(createAnimeEntity(1, "Anime 1"))
        localDataSource.saveAnimeList(entities)

        // When
        localDataSource.clearAnimeList()
        val result = localDataSource.observeAnimeList().first()

        // Then
        assertTrue(result.isEmpty())
    }

    @Test
    fun `saveAnimeDetail and getAnimeDetail work correctly`() = runTest {
        // Given
        val entity = createAnimeDetailEntity(1, "Anime Detail 1")

        // When
        localDataSource.saveAnimeDetail(entity)
        val result = localDataSource.getAnimeDetail(1)

        // Then
        assertEquals(1, result?.id)
        assertEquals("Anime Detail 1", result?.title)
    }

    @Test
    fun `observeAnimeDetail emits updated data`() = runTest {
        // Given
        val entity = createAnimeDetailEntity(2, "Anime Detail 2")

        // When
        localDataSource.saveAnimeDetail(entity)
        val result = localDataSource.observeAnimeDetail(2).first()

        // Then
        assertEquals(2, result?.id)
        assertEquals("Anime Detail 2", result?.title)
    }

    @Test
    fun `getAnimeDetail returns null when not found`() = runTest {
        // When
        val result = localDataSource.getAnimeDetail(999)

        // Then
        assertNull(result)
    }

    @Test
    fun `observeAnimeDetail emits null when not found`() = runTest {
        // When
        val result = localDataSource.observeAnimeDetail(999).first()

        // Then
        assertNull(result)
    }

    private fun createAnimeEntity(id: Int, title: String): AnimeEntity {
        return AnimeEntity(
            id = id,
            title = title,
            posterUrl = "https://example.com/image.jpg",
            episodes = 12,
            rating = 8.5
        )
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
