package com.seekho.animepilot.presentation

sealed class NavigationEvent {
    data class NavigateToDetail(val animeId: Int) : NavigationEvent()
}