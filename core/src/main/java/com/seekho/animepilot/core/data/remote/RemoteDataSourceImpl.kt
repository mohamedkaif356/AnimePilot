package com.seekho.animepilot.core.data.remote

import com.seekho.animepilot.core.data.api.JikanApi
import com.seekho.animepilot.core.data.db.entity.AnimeDetailEntity
import com.seekho.animepilot.core.data.db.entity.AnimeEntity
import com.seekho.animepilot.core.data.mapper.toEntities
import com.seekho.animepilot.core.data.mapper.toEntity
import com.seekho.animepilot.core.domain.model.AppError
import com.seekho.animepilot.core.domain.model.Result
import com.seekho.animepilot.core.util.ErrorMapper
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val jikanApi: JikanApi
) : RemoteDataSource {
    
    override suspend fun fetchTopAnime(): Result<List<AnimeEntity>> {
        return try {
            val response = jikanApi.getTopAnime()
            if (response.isSuccessful) {
                val body = response.body()
                val dtoList = body?.data
                if (dtoList.isNullOrEmpty()) {
                    Result.Error(AppError.DataError.EmptyResponse)
                } else {
                    val entities = dtoList.toEntities()
                    Result.Success(entities)
                }
            } else {
                Result.Error(
                    if (response.code() != 0) {
                        AppError.NetworkError.HttpError(response.code())
                    } else {
                        AppError.DataError.EmptyResponse
                    }
                )
            }
        } catch (e: HttpException) {
            Result.Error(AppError.NetworkError.HttpError(e.code()))
        } catch (e: SocketTimeoutException) {
            Result.Error(AppError.NetworkError.Timeout)
        } catch (e: UnknownHostException) {
            Result.Error(AppError.NetworkError.NoInternet)
        } catch (e: IOException) {
            Result.Error(AppError.NetworkError.NoInternet)
        } catch (e: Exception) {
            Result.Error(ErrorMapper.map(e))
        }
    }
    
    override suspend fun fetchAnimeDetail(id: Int): Result<AnimeDetailEntity> {
        return try {
            val response = jikanApi.getAnimeDetail(id)
            if (response.isSuccessful) {
                val body = response.body()
                val dto = body?.data
                if (dto == null) {
                    Result.Error(AppError.DataError.EmptyResponse)
                } else {
                    val entity = dto.toEntity()
                    Result.Success(entity)
                }
            } else {
                Result.Error(
                    if (response.code() != 0) {
                        AppError.NetworkError.HttpError(response.code())
                    } else {
                        AppError.DataError.EmptyResponse
                    }
                )
            }
        } catch (e: HttpException) {
            Result.Error(AppError.NetworkError.HttpError(e.code()))
        } catch (e: SocketTimeoutException) {
            Result.Error(AppError.NetworkError.Timeout)
        } catch (e: UnknownHostException) {
            Result.Error(AppError.NetworkError.NoInternet)
        } catch (e: IOException) {
            Result.Error(AppError.NetworkError.NoInternet)
        } catch (e: Exception) {
            Result.Error(ErrorMapper.map(e))
        }
    }
}