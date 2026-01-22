package com.seekho.animepilot.presentation.anime_detail

import com.seekho.animepilot.core.domain.model.AnimeDetail

sealed class AnimeDetailUiState {
    object Loading : AnimeDetailUiState()
    data class Success(
        val animeDetail: AnimeDetail,
        val isRefreshing: Boolean = false,
    ) : AnimeDetailUiState()
    data class Error(
        val error: com.seekho.animepilot.core.domain.model.AppError,
        val errorTitle: String,
        val errorMessage: String,
        val canRetry: Boolean = true,
        val cachedData: AnimeDetail? = null // Show cached data if available
    ) : AnimeDetailUiState()
}
