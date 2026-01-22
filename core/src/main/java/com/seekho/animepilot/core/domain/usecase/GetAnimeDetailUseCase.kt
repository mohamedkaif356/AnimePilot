package com.seekho.animepilot.core.domain.usecase

import com.seekho.animepilot.core.domain.model.AnimeDetail
import com.seekho.animepilot.core.domain.model.AppError
import com.seekho.animepilot.core.domain.model.Result
import com.seekho.animepilot.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetAnimeDetailUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(id: Int): Flow<Result<AnimeDetail>> {
        if (id <= 0) {
            return flowOf(Result.Error(AppError.DataError.EmptyResponse))
        }
        return repository.getAnimeDetail(id)
    }
}