package com.mateuszholik.domain.usecases

import com.mateuszholik.data.repositories.NewsRepository
import com.mateuszholik.domain.usecases.base.FlowUseCase
import com.mateuszholik.model.Article
import com.mateuszholik.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTopSportsNewsUseCase : FlowUseCase<List<Article>>

internal class GetTopSportsNewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository,
) : GetTopSportsNewsUseCase {

    override fun invoke(): Flow<Result<List<Article>>> =
        newsRepository.getTopSportsNews()
}
