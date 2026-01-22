package com.seekho.animepilot.presentation.anime_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seekho.animepilot.core.domain.model.Result
import com.seekho.animepilot.core.domain.usecase.GetAnimeDetailUseCase
import com.seekho.animepilot.core.util.ConnectivityObserver
import com.seekho.animepilot.core.util.ErrorMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val getAnimeDetailUseCase: GetAnimeDetailUseCase,
    connectivityObserver: ConnectivityObserver
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<AnimeDetailUiState>(AnimeDetailUiState.Loading)
    val uiState: StateFlow<AnimeDetailUiState> = _uiState.asStateFlow()
    
    private var currentAnimeId: Int? = null

    private val _isOffline = MutableStateFlow(false)
    val isOffline: StateFlow<Boolean> = _isOffline.asStateFlow()

    init {
        connectivityObserver.observeConnectivity()
            .onEach { isOnline ->
                _isOffline.value = !isOnline
            }
            .launchIn(viewModelScope)
    }
    
    fun loadAnimeDetail(animeId: Int) {
        if (currentAnimeId == animeId && _uiState.value is AnimeDetailUiState.Success) {
            return
        }
        
        currentAnimeId = animeId
        _uiState.value = AnimeDetailUiState.Loading
        
        viewModelScope.launch {
            getAnimeDetailUseCase(animeId)
                .catch { e ->
                    val appError = ErrorMapper.map(e)
                    _uiState.value = AnimeDetailUiState.Error(
                        error = appError,
                        errorTitle = ErrorMapper.getErrorTitle(appError),
                        errorMessage = ErrorMapper.toUserMessage(appError),
                        canRetry = true
                    )
                }
                .collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            val currentState = _uiState.value
                            if (currentState is AnimeDetailUiState.Success) {
                                _uiState.value = currentState.copy(isRefreshing = true)
                            } else {
                                _uiState.value = AnimeDetailUiState.Loading
                            }
                        }
                        is Result.Success -> {
                            _uiState.value = AnimeDetailUiState.Success(
                                animeDetail = result.data,
                                isRefreshing = false,
                            )
                        }
                        is Result.Error -> {
                            val currentState = _uiState.value
                            _uiState.value = when (currentState) {
                                is AnimeDetailUiState.Success -> {
                                    currentState.copy(
                                        isRefreshing = false,
                                    )
                                }
                                else -> {
                                    AnimeDetailUiState.Error(
                                        error = result.error,
                                        errorTitle = ErrorMapper.getErrorTitle(result.error),
                                        errorMessage = ErrorMapper.toUserMessage(result.error),
                                        canRetry = true,
                                        cachedData = null
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }
    
    fun refresh() {
        currentAnimeId?.let { loadAnimeDetail(it) }
    }
    
    fun retry() {
        currentAnimeId?.let { loadAnimeDetail(it) }
    }
}