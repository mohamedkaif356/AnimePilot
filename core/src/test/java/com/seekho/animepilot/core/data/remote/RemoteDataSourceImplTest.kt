package com.seekho.animepilot.core.data.remote

import com.seekho.animepilot.core.data.api.JikanApi
import com.seekho.animepilot.core.data.api.dto.AnimeDetailDto
import com.seekho.animepilot.core.data.api.dto.AnimeDetailResponseDto
import com.seekho.animepilot.core.data.api.dto.AnimeDto
import com.seekho.animepilot.core.data.api.dto.ImagesDto
import com.seekho.animepilot.core.data.api.dto.ImageUrlDto
import com.seekho.animepilot.core.data.api.dto.TopAnimeResponseDto
import com.seekho.animepilot.core.domain.model.AppError
import com.seekho.animepilot.core.domain.model.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RemoteDataSourceImplTest {

    private lateinit var jikanApi: JikanApi
    private lateinit var remoteDataSource: RemoteDataSourceImpl

    @Before
    fun setup() {
        jikanApi = mockk()
        remoteDataSource = RemoteDataSourceImpl(jikanApi)
    }

    @Test
    fun `fetchTopAnime returns success when API returns data`() = runTest {
        // Given
        val dtoList = listOf(
            createAnimeDto(1, "Anime 1"),
            createAnimeDto(2, "Anime 2")
        )
        val response = Response.success(TopAnimeResponseDto(dtoList))
        coEvery { jikanApi.getTopAnime() } returns response

        // When
        val result = remoteDataSource.fetchTopAnime()

        // Then
        assertTrue(result is Result.Success)
        val success = result as Result.Success
        assertEquals(2, success.data.size)
        assertEquals(1, success.data[0].id)
        assertEquals("Anime 1", success.data[0].title)
    }

    @Test
    fun `fetchTopAnime returns error when API returns empty list`() = runTest {
        // Given
        val response = Response.success(TopAnimeResponseDto(emptyList()))
        coEvery { jikanApi.getTopAnime() } returns response

        // When
        val result = remoteDataSource.fetchTopAnime()

        // Then
        assertTrue(result is Result.Error)
        val error = result as Result.Error
        assertTrue(error.error is AppError.DataError.EmptyResponse)
    }

    @Test
    fun `fetchTopAnime returns error when API returns null body`() = runTest {
        // Given
        val response = Response.success<TopAnimeResponseDto>(null)
        coEvery { jikanApi.getTopAnime() } returns response

        // When
        val result = remoteDataSource.fetchTopAnime()

        // Then
        assertTrue(result is Result.Error)
        val error = result as Result.Error
        assertTrue(error.error is AppError.DataError.EmptyResponse)
    }

    @Test
    fun `fetchTopAnime returns HttpError when API returns 404`() = runTest {
        // Given
        val response = Response.error<TopAnimeResponseDto>(
            404,
            "Not Found".toResponseBody()
        )
        coEvery { jikanApi.getTopAnime() } returns response

        // When
        val result = remoteDataSource.fetchTopAnime()

        // Then
        assertTrue(result is Result.Error)
        val error = result as Result.Error
        assertTrue(error.error is AppError.NetworkError.HttpError)
        assertEquals(404, (error.error as AppError.NetworkError.HttpError).code)
    }

    @Test
    fun `fetchTopAnime returns HttpError when API returns 500`() = runTest {
        // Given
        val response = Response.error<TopAnimeResponseDto>(
            500,
            "Server Error".toResponseBody()
        )
        coEvery { jikanApi.getTopAnime() } returns response

        // When
        val result = remoteDataSource.fetchTopAnime()

        // Then
        assertTrue(result is Result.Error)
        val error = result as Result.Error
        assertTrue(error.error is AppError.NetworkError.HttpError)
        assertEquals(500, (error.error as AppError.NetworkError.HttpError).code)
    }

    @Test
    fun `fetchTopAnime returns Timeout when SocketTimeoutException occurs`() = runTest {
        // Given
        coEvery { jikanApi.getTopAnime() } throws SocketTimeoutException()

        // When
        val result = remoteDataSource.fetchTopAnime()

        // Then
        assertTrue(result is Result.Error)
        val error = result as Result.Error
        assertTrue(error.error is AppError.NetworkError.Timeout)
    }

    @Test
    fun `fetchTopAnime returns NoInternet when UnknownHostException occurs`() = runTest {
        // Given
        coEvery { jikanApi.getTopAnime() } throws UnknownHostException()

        // When
        val result = remoteDataSource.fetchTopAnime()

        // Then
        assertTrue(result is Result.Error)
        val error = result as Result.Error
        assertTrue(error.error is AppError.NetworkError.NoInternet)
    }

    @Test
    fun `fetchAnimeDetail returns success when API returns data`() = runTest {
        // Given
        val detailDto = createAnimeDetailDto(1, "Anime Detail")
        val response = Response.success(AnimeDetailResponseDto(detailDto))
        coEvery { jikanApi.getAnimeDetail(1) } returns response

        // When
        val result = remoteDataSource.fetchAnimeDetail(1)

        // Then
        assertTrue(result is Result.Success)
        val success = result as Result.Success
        assertEquals(1, success.data.id)
        assertEquals("Anime Detail", success.data.title)
    }

    @Test
    fun `fetchAnimeDetail returns error when API returns null body`() = runTest {
        // Given
        val response = Response.success<AnimeDetailResponseDto>(null)
        coEvery { jikanApi.getAnimeDetail(1) } returns response

        // When
        val result = remoteDataSource.fetchAnimeDetail(1)

        // Then
        assertTrue(result is Result.Error)
        val error = result as Result.Error
        assertTrue(error.error is AppError.DataError.EmptyResponse)
    }

    @Test
    fun `fetchAnimeDetail returns HttpError when API returns 404`() = runTest {
        // Given
        val response = Response.error<AnimeDetailResponseDto>(
            404,
            "Not Found".toResponseBody()
        )
        coEvery { jikanApi.getAnimeDetail(1) } returns response

        // When
        val result = remoteDataSource.fetchAnimeDetail(1)

        // Then
        assertTrue(result is Result.Error)
        val error = result as Result.Error
        assertTrue(error.error is AppError.NetworkError.HttpError)
        assertEquals(404, (error.error as AppError.NetworkError.HttpError).code)
    }

    @Test
    fun `fetchAnimeDetail returns HttpError when HttpException occurs`() = runTest {
        // Given
        val response = Response.error<AnimeDetailResponseDto>(
            500,
            "Server Error".toResponseBody()
        )
        coEvery { jikanApi.getAnimeDetail(1) } throws HttpException(response)

        // When
        val result = remoteDataSource.fetchAnimeDetail(1)

        // Then
        assertTrue(result is Result.Error)
        val error = result as Result.Error
        assertTrue(error.error is AppError.NetworkError.HttpError)
        assertEquals(500, (error.error as AppError.NetworkError.HttpError).code)
    }

    private fun createAnimeDto(id: Int, title: String): AnimeDto {
        return AnimeDto(
            id = id,
            title = title,
            images = ImagesDto(ImageUrlDto("https://example.com/image.jpg")),
            episodes = 12,
            rating = 8.5
        )
    }

    private fun createAnimeDetailDto(id: Int, title: String): AnimeDetailDto {
        return AnimeDetailDto(
            id = id,
            title = title,
            synopsis = "Test synopsis",
            genres = emptyList(),
            characters = emptyList(),
            trailer = null,
            rating = 8.5,
            episodes = 12,
            images = ImagesDto(ImageUrlDto("https://example.com/image.jpg"))
        )
    }
}
