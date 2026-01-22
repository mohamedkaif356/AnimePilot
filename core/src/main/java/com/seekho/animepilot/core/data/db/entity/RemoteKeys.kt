package com.seekho.animepilot.core.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "remote_keys",
    indices = [Index(value = ["page"], unique = true)]
)
data class RemoteKeys(
    @PrimaryKey
    val page: Int = 1,
    val prevKey: Int?,
    val nextKey: Int?
)
