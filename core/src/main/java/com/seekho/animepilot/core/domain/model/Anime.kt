package com.seekho.animepilot.core.domain.model

data class Anime(
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val episodes: Int?,
    val rating: Double?
)