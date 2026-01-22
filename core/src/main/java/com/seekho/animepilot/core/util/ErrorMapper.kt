package com.seekho.animepilot.core.util

import android.database.sqlite.SQLiteException
import com.seekho.animepilot.core.domain.model.AppError
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorMapper {
    
    fun map(throwable: Throwable): AppError {
        return when (throwable) {
            is SocketTimeoutException -> AppError.NetworkError.Timeout
            is UnknownHostException -> AppError.NetworkError.NoInternet
            is IOException -> AppError.NetworkError.NoInternet
            is HttpException -> {
                val code = throwable.code()
                AppError.NetworkError.HttpError(code)
            }
            is SQLiteException -> AppError.CacheError.NotFound
            else -> AppError.NetworkError.UnknownError
        }
    }
    
    fun toUserMessage(error: AppError): String {
        return when (error) {
            is AppError.NetworkError.NoInternet -> 
                "No internet connection. Showing cached data if available."
            is AppError.NetworkError.Timeout -> 
                "Request timed out. Please check your connection and try again."
            is AppError.NetworkError.HttpError -> {
                when (error.code) {
                    429 -> "Too many requests. Please try again later."
                    500, 502, 503 -> "Server error. Please try again later."
                    404 -> "Resource not found."
                    else -> "Network error (${error.code}). Please try again."
                }
            }
            is AppError.NetworkError.UnknownError -> 
                "An unexpected error occurred. Please try again."
            is AppError.DataError.ParseError -> 
                "Failed to parse data. Please try again."
            is AppError.DataError.EmptyResponse -> 
                "No data available."
            is AppError.CacheError.NotFound -> 
                "Data not found in cache. Please check your connection."
        }
    }
    
    fun getErrorTitle(error: AppError): String {
        return when (error) {
            is AppError.NetworkError.NoInternet -> "No Internet"
            is AppError.NetworkError.Timeout -> "Connection Timeout"
            is AppError.NetworkError.HttpError -> "Server Error"
            is AppError.NetworkError.UnknownError -> "Error"
            is AppError.DataError.ParseError -> "Data Error"
            is AppError.DataError.EmptyResponse -> "No Data"
            is AppError.CacheError.NotFound -> "Not Found"
        }
    }
}