package com.seekho.animepilot.core.domain.usecase

import com.seekho.animepilot.core.domain.model.AnimeDetail
import com.seekho.animepilot.core.domain.model.AppError
import com.seekho.animepilot.core.domain.model.Result
import com.seekho.animepilot.core.domain.repository.AnimeRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import app.cash.turbine.test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetAnimeDetailUseCaseTest {

    private lateinit var repository: AnimeRepository
    private lateinit var useCase: GetAnimeDetailUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetAnimeDetailUseCase(repository)
    }

    private fun createTestAnimeDetail(): AnimeDetail {
        return AnimeDetail(
            id = 1,
            title = "Test Anime",
            synopsis = "Test synopsis",
            genres = listOf("Action"),
            cast = listOf("Character 1"),
            trailerUrl = null,
            rating = 8.5,
            episodes = 12,
            posterUrl = "https://example.com/poster.jpg"
        )
    }

    @Test
    fun `invoke with valid ID delegates to repository`() = runTest {
        // Given
        val animeDetail = createTestAnimeDetail()
        every { repository.getAnimeDetail(1) } returns flowOf(
            Result.Loading,
            Result.Success(animeDetail)
        )

        // When
        val result = useCase(1)

        // Then
        result.test {
            val loading = awaitItem()
            assertTrue(loading is Result.Loading)
            val success = awaitItem()
            assertTrue(success is Result.Success)
            assertEquals(animeDetail, success.data)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `invoke with ID zero returns error immediately`() = runTest {
        // When
        val result = useCase(0)

        // Then
        result.test {
            val error = awaitItem()
            assertTrue(error is Result.Error)
            assertTrue(error.error is AppError.DataError.EmptyResponse)
            awaitComplete()
        }
    }

    @Test
    fun `invoke with negative ID returns error immediately`() = runTest {
        // When
        val result = useCase(-1)

        // Then
        result.test {
            val error = awaitItem()
            assertTrue(error is Result.Error)
            assertTrue(error.error is AppError.DataError.EmptyResponse)
            awaitComplete()
        }
    }

    @Test
    fun `invoke with valid ID does not call repository when ID is invalid`() = runTest {
        // Given
        val invalidIds = listOf(0, -1, -100)
        
        invalidIds.forEach { invalidId ->
            // When
            val result = useCase(invalidId)

            // Then
            result.test {
                val error = awaitItem()
                assertTrue(error is Result.Error)
                awaitComplete()
            }
        }
    }

    @Test
    fun `invoke with valid ID returns Flow from repository`() = runTest {
        // Given
        val animeDetail = createTestAnimeDetail()
        every { repository.getAnimeDetail(5) } returns flowOf(Result.Success(animeDetail))

        // When
        val result = useCase(5)

        // Then
        result.test {
            val success = awaitItem()
            assertTrue(success is Result.Success)
            assertEquals(animeDetail, success.data)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `invoke with large valid ID delegates to repository`() = runTest {
        // Given
        val animeDetail = createTestAnimeDetail()
        every { repository.getAnimeDetail(999999) } returns flowOf(Result.Success(animeDetail))

        // When
        val result = useCase(999999)

        // Then
        result.test {
            val success = awaitItem()
            assertTrue(success is Result.Success)
            cancelAndConsumeRemainingEvents()
        }
    }
}
