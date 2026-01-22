package com.seekho.animepilot.core.data.mapper

import com.google.gson.Gson
import com.seekho.animepilot.core.data.api.dto.AnimeDetailDto
import com.seekho.animepilot.core.data.api.dto.AnimeDto
import com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity
import com.seekho.animepilot.core.data.db.entity.AnimeEntity

fun AnimeDto.toEntity(): AnimeEntity {
    val posterUrl = images?.jpg?.imageUrl
    
    return AnimeEntity(
        id = id ?: 0,
        title = title ?: "Unknown Title",
        posterUrl = posterUrl,
        episodes = episodes,
        rating = rating,
        lastUpdated = System.currentTimeMillis()
    )
}

fun AnimeDetailDto.toEntity(): AnimeDetailEntity {
    val posterUrl = images?.jpg?.imageUrl
    val genresJson = convertGenresToJson(genres)
    val castJson = convertCharactersToJson(characters)
    val trailerUrl = trailer?.url ?: trailer?.embedUrl
    
    return AnimeDetailEntity(
        id = id ?: 0,
        title = title ?: "Unknown Title",
        synopsis = synopsis ?: "No synopsis available.",
        genres = genresJson,
        cast = castJson,
        trailerUrl = trailerUrl,
        rating = rating,
        episodes = episodes,
        posterUrl = posterUrl,
        lastUpdated = System.currentTimeMillis()
    )
}

private fun convertGenresToJson(genres: List<com.seekho.animepilot.core.data.api.dto.GenreDto>?): String {
    if (genres.isNullOrEmpty()) {
        return "[]"
    }
    val genreNames = genres.mapNotNull { it.name }
    return if (genreNames.isEmpty()) {
        "[]"
    } else {
        Gson().toJson(genreNames)
    }
}

private fun convertCharactersToJson(characters: List<com.seekho.animepilot.core.data.api.dto.CharacterDto>?): String {
    if (characters.isNullOrEmpty()) {
        return "[]"
    }
    val characterNames = characters
        .mapNotNull { it.character?.name }
        .take(10)
    
    return if (characterNames.isEmpty()) {
        "[]"
    } else {
        Gson().toJson(characterNames)
    }
}

fun List<AnimeDto>.toEntities(): List<AnimeEntity> {
    return mapNotNull { dto ->
        if (dto.id != null) {
            dto.toEntity()
        } else {
            null
        }
    }
}