package com.seekho.animepilot.core.data.api

import com.seekho.animepilot.core.data.api.dto.AnimeDetailResponseDto
import com.seekho.animepilot.core.data.api.dto.TopAnimeResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanApi {
    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int? = null
    ): Response<TopAnimeResponseDto>
    
    @GET("anime/{id}")
    suspend fun getAnimeDetail(@Path("id") id: Int): Response<AnimeDetailResponseDto>
}