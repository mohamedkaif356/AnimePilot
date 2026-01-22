package com.seekho.animepilot.core.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "anime",
    indices = [Index(value = ["rating"])]
)
data class AnimeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val episodes: Int?,
    val rating: Double?,
    val lastUpdated: Long = System.currentTimeMillis()
)