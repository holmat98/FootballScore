package com.mateuszholik.domain.usecases

import com.mateuszholik.common.providers.CurrentDateProvider
import com.mateuszholik.data.repositories.NewsRepository
import com.mateuszholik.domain.usecases.base.FlowUseCase
import com.mateuszholik.domain.usecases.base.ParameterizedFlowUseCase
import com.mateuszholik.model.Article
import com.mateuszholik.model.ArticleSortingOptions
import com.mateuszholik.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTopSportsNewsUseCase : ParameterizedFlowUseCase<ArticleSortingOptions, List<Article>>

internal class GetTopSportsNewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository,
    private val currentDateProvider: CurrentDateProvider
) : GetTopSportsNewsUseCase {

    override fun invoke(param: ArticleSortingOptions): Flow<Result<List<Article>>> =
        newsRepository.getTopSportsNews(
            domains = DEFAULT_DOMAINS,
            fromDate = currentDateProvider.provide().minusDays(DAYS_IN_WEEK),
            sortBy = param
        )

    private companion object {
        val DEFAULT_DOMAINS = listOf(
            "theathletic.com",
            "espn.com",
            "meczyki.pl",
            "sport.pl",
            "goal.com",
            "skysports.com",
            "fourfourtwo.com"
        )
        const val DAYS_IN_WEEK = 7L
    }
}
