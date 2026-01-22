package com.seekho.animepilot.core.domain.model

data class AnimeDetail(
    val id: Int,
    val title: String,
    val synopsis: String,
    val genres: List<String>,
    val cast: List<String>,
    val trailerUrl: String?,
    val rating: Double?,
    val episodes: Int?,
    val posterUrl: String?
)