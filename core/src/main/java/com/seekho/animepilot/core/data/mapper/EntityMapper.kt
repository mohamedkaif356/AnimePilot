package com.seekho.animepilot.core.data.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import com.seekho.animepilot.core.domain.model.Anime
import com.seekho.animepilot.core.domain.model.AnimeDetail

object EntityMapper {
    // Gson will be injected via DI and set before use
    private var gsonInstance: Gson? = null
    
    fun setGson(gson: Gson) {
        gsonInstance = gson
    }
    
    private val gson: Gson
        get() = gsonInstance ?: throw IllegalStateException("Gson not initialized. Call EntityMapper.setGson() first.")
    
    internal fun parseJsonList(jsonString: String): List<String> {
        if (jsonString.isEmpty() || jsonString == "[]") {
            return emptyList()
        }
        return try {
            val listType = object : TypeToken<List<String>>() {}.type
            gson.fromJson(jsonString, listType) ?: emptyList()
        } catch (_: Exception) {
            emptyList()
        }
    }
}

fun AnimeEntity.toDomain(): Anime {
    return Anime(
        id = id,
        title = title,
        posterUrl = posterUrl,
        episodes = episodes,
        rating = rating
    )
}

fun AnimeDetailEntity.toDomain(): AnimeDetail {
    val genres = EntityMapper.parseJsonList(genres)
    val cast = EntityMapper.parseJsonList(cast)
    
    return AnimeDetail(
        id = id,
        title = title,
        synopsis = synopsis.ifEmpty { "No synopsis available." },
        genres = genres,
        cast = cast,
        trailerUrl = trailerUrl,
        rating = rating,
        episodes = episodes,
        posterUrl = posterUrl
    )
}

fun List<AnimeEntity>.toDomain(): List<Anime> {
    return map { it.toDomain() }
}