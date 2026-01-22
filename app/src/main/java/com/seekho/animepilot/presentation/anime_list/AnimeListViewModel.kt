package com.seekho.animepilot.presentation.anime_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.seekho.animepilot.core.domain.model.Anime
import com.seekho.animepilot.core.domain.repository.AnimeRepository
import com.seekho.animepilot.core.util.ConnectivityObserver
import com.seekho.animepilot.presentation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    repository: AnimeRepository,
    connectivityObserver: ConnectivityObserver
) : ViewModel() {

    val animePagingFlow: Flow<PagingData<Anime>> = repository
        .getTopAnimePagingFlow()
        .cachedIn(viewModelScope)

    private val _isOffline = MutableStateFlow(false)
    val isOffline: StateFlow<Boolean> = _isOffline.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1)
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents.asSharedFlow()
    
    init {
        connectivityObserver.observeConnectivity()
            .onEach { isOnline ->
                _isOffline.value = !isOnline
            }
            .launchIn(viewModelScope)
    }
    
    fun setRefreshing(isRefreshing: Boolean) {
        _isRefreshing.value = isRefreshing
    }
    
    fun onAnimeClicked(animeId: Int) {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.NavigateToDetail(animeId))
        }
    }
}