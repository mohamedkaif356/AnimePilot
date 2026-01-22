package com.seekho.animepilot.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_detail")
data class AnimeDetailEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val synopsis: String,
    val genres: String,
    val cast: String,
    val trailerUrl: String?,
    val rating: Double?,
    val episodes: Int?,
    val posterUrl: String?,
    val lastUpdated: Long = System.currentTimeMillis()
)