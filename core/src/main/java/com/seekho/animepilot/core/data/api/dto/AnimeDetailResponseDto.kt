package com.seekho.animepilot.core.data.api.dto

import com.google.gson.annotations.SerializedName

data class AnimeDetailResponseDto(
    @SerializedName("data")
    val data: AnimeDetailDto?
)

data class AnimeDetailDto(
    @SerializedName("mal_id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("synopsis")
    val synopsis: String?,
    @SerializedName("genres")
    val genres: List<GenreDto>?,
    @SerializedName("characters")
    val characters: List<CharacterDto>?,
    @SerializedName("trailer")
    val trailer: TrailerDto?,
    @SerializedName("score")
    val rating: Double?,
    @SerializedName("episodes")
    val episodes: Int?,
    @SerializedName("images")
    val images: ImagesDto?
)

data class GenreDto(
    @SerializedName("name")
    val name: String?
)

data class CharacterDto(
    @SerializedName("character")
    val character: CharacterInfoDto?
)

data class CharacterInfoDto(
    @SerializedName("name")
    val name: String?
)

data class TrailerDto(
    @SerializedName("url")
    val url: String?,
    @SerializedName("embed_url")
    val embedUrl: String?
)