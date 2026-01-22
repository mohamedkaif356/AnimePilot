package com.seekho.animepilot.core.data.api.dto

import com.google.gson.annotations.SerializedName

data class TopAnimeResponseDto(
    @SerializedName("data")
    val data: List<AnimeDto>?
)

data class AnimeDto(
    @SerializedName("mal_id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("images")
    val images: ImagesDto?,
    @SerializedName("episodes")
    val episodes: Int?,
    @SerializedName("score")
    val rating: Double?
)

data class ImagesDto(
    @SerializedName("jpg")
    val jpg: ImageUrlDto?
)

data class ImageUrlDto(
    @SerializedName("image_url")
    val imageUrl: String?
)