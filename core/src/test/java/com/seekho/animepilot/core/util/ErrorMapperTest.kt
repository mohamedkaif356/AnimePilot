package com.seekho.animepilot.core.util

import android.database.sqlite.SQLiteException
import com.seekho.animepilot.core.domain.model.AppError
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorMapperTest {

    @Test
    fun `map returns Timeout for SocketTimeoutException`() {
        // When
        val result = ErrorMapper.map(SocketTimeoutException())

        // Then
        assertTrue(result is AppError.NetworkError.Timeout)
    }

    @Test
    fun `map returns NoInternet for UnknownHostException`() {
        // When
        val result = ErrorMapper.map(UnknownHostException())

        // Then
        assertTrue(result is AppError.NetworkError.NoInternet)
    }

    @Test
    fun `map returns NoInternet for IOException`() {
        // When
        val result = ErrorMapper.map(IOException())

        // Then
        assertTrue(result is AppError.NetworkError.NoInternet)
    }

    @Test
    fun `map returns HttpError with correct code for HttpException`() {
        // Given
        val responseBody = "Not Found".toResponseBody(null)
        val httpException = HttpException(
            Response.error<Any>(
                404,
                responseBody
            )
        )

        // When
        val result = ErrorMapper.map(httpException)

        // Then
        assertTrue(result is AppError.NetworkError.HttpError)
        assertEquals(404, (result as AppError.NetworkError.HttpError).code)
    }

    @Test
    fun `map returns UnknownError for unknown exception`() {
        // When
        val result = ErrorMapper.map(RuntimeException("Unknown error"))

        // Then
        assertTrue(result is AppError.NetworkError.UnknownError)
    }

    @Test
    fun `toUserMessage returns correct message for NoInternet`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.NetworkError.NoInternet)

        // Then
        assertEquals("No internet connection. Showing cached data if available.", message)
    }

    @Test
    fun `toUserMessage returns correct message for Timeout`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.NetworkError.Timeout)

        // Then
        assertEquals("Request timed out. Please check your connection and try again.", message)
    }

    @Test
    fun `toUserMessage returns correct message for HttpError 404`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.NetworkError.HttpError(404))

        // Then
        assertEquals("Resource not found.", message)
    }

    @Test
    fun `toUserMessage returns correct message for HttpError 429`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.NetworkError.HttpError(429))

        // Then
        assertEquals("Too many requests. Please try again later.", message)
    }

    @Test
    fun `toUserMessage returns correct message for HttpError 500`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.NetworkError.HttpError(500))

        // Then
        assertEquals("Server error. Please try again later.", message)
    }

    @Test
    fun `toUserMessage returns correct message for HttpError 502`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.NetworkError.HttpError(502))

        // Then
        assertEquals("Server error. Please try again later.", message)
    }

    @Test
    fun `toUserMessage returns correct message for HttpError 503`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.NetworkError.HttpError(503))

        // Then
        assertEquals("Server error. Please try again later.", message)
    }

    @Test
    fun `toUserMessage returns correct message for HttpError unknown code`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.NetworkError.HttpError(418))

        // Then
        assertEquals("Network error (418). Please try again.", message)
    }

    @Test
    fun `toUserMessage returns correct message for UnknownError`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.NetworkError.UnknownError)

        // Then
        assertEquals("An unexpected error occurred. Please try again.", message)
    }

    @Test
    fun `toUserMessage returns correct message for ParseError`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.DataError.ParseError)

        // Then
        assertEquals("Failed to parse data. Please try again.", message)
    }

    @Test
    fun `toUserMessage returns correct message for EmptyResponse`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.DataError.EmptyResponse)

        // Then
        assertEquals("No data available.", message)
    }

    @Test
    fun `toUserMessage returns correct message for NotFound`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.CacheError.NotFound)

        // Then
        assertEquals("Data not found in cache. Please check your connection.", message)
    }

    @Test
    fun `getErrorTitle returns correct title for NoInternet`() {
        // When
        val title = ErrorMapper.getErrorTitle(AppError.NetworkError.NoInternet)

        // Then
        assertEquals("No Internet", title)
    }

    @Test
    fun `getErrorTitle returns correct title for Timeout`() {
        // When
        val title = ErrorMapper.getErrorTitle(AppError.NetworkError.Timeout)

        // Then
        assertEquals("Connection Timeout", title)
    }

    @Test
    fun `getErrorTitle returns correct title for HttpError`() {
        // When
        val title = ErrorMapper.getErrorTitle(AppError.NetworkError.HttpError(404))

        // Then
        assertEquals("Server Error", title)
    }

    @Test
    fun `getErrorTitle returns correct title for UnknownError`() {
        // When
        val title = ErrorMapper.getErrorTitle(AppError.NetworkError.UnknownError)

        // Then
        assertEquals("Error", title)
    }

    @Test
    fun `getErrorTitle returns correct title for DataError`() {
        // When
        val title = ErrorMapper.getErrorTitle(AppError.DataError.EmptyResponse)

        // Then
        assertEquals("No Data", title)
    }

    @Test
    fun `getErrorTitle returns correct title for ParseError`() {
        // When
        val title = ErrorMapper.getErrorTitle(AppError.DataError.ParseError)

        // Then
        assertEquals("Data Error", title)
    }

    @Test
    fun `getErrorTitle returns correct title for CacheError`() {
        // When
        val title = ErrorMapper.getErrorTitle(AppError.CacheError.NotFound)

        // Then
        assertEquals("Not Found", title)
    }

    @Test
    fun `map returns NotFound for SQLiteException`() {
        // When
        val result = ErrorMapper.map(SQLiteException("Database error"))

        // Then
        assertTrue(result is AppError.CacheError.NotFound)
    }

    @Test
    fun `map returns HttpError with correct code for HttpException with 500`() {
        // Given
        val responseBody = "Internal Server Error".toResponseBody(null)
        val httpException = HttpException(
            Response.error<Any>(
                500,
                responseBody
            )
        )

        // When
        val result = ErrorMapper.map(httpException)

        // Then
        assertTrue(result is AppError.NetworkError.HttpError)
        assertEquals(500, (result as AppError.NetworkError.HttpError).code)
    }

    @Test
    fun `map returns HttpError with correct code for HttpException with 401`() {
        // Given
        val responseBody = "Unauthorized".toResponseBody(null)
        val httpException = HttpException(
            Response.error<Any>(
                401,
                responseBody
            )
        )

        // When
        val result = ErrorMapper.map(httpException)

        // Then
        assertTrue(result is AppError.NetworkError.HttpError)
        assertEquals(401, (result as AppError.NetworkError.HttpError).code)
    }

    @Test
    fun `toUserMessage returns correct message for HttpError 401`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.NetworkError.HttpError(401))

        // Then
        assertEquals("Network error (401). Please try again.", message)
    }

    @Test
    fun `toUserMessage returns correct message for HttpError 403`() {
        // When
        val message = ErrorMapper.toUserMessage(AppError.NetworkError.HttpError(403))

        // Then
        assertEquals("Network error (403). Please try again.", message)
    }
}
