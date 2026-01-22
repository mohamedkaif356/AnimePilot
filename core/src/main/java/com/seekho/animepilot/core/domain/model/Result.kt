package com.seekho.animepilot.core.domain.model

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val error: AppError) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

sealed class AppError {
    sealed class NetworkError : AppError() {
        object NoInternet : NetworkError()
        object Timeout : NetworkError()
        data class HttpError(val code: Int) : NetworkError()
        object UnknownError : NetworkError()
    }
    
    sealed class DataError : AppError() {
        object ParseError : DataError()
        object EmptyResponse : DataError()
    }
    
    sealed class CacheError : AppError() {
        object NotFound : CacheError()
    }
}