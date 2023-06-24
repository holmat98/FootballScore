package com.mateuszholik.data.repositories

import com.mateuszholik.data.extensions.toCommonModel
import com.mateuszholik.data.extensions.toResult
import com.mateuszholik.model.Article
import com.mateuszholik.model.ArticleSortingOptions
import com.mateuszholik.model.Result
import com.mateuszholik.network.repositories.NewsApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

interface NewsRepository {

    fun getTopSportsNews(
        domains: List<String>,
        fromDate: LocalDate,
        sortBy: ArticleSortingOptions,
    ): Flow<Result<List<Article>>>
}

internal class NewsRepositoryImpl(
    private val newsApiRepository: NewsApiRepository,
) : NewsRepository {

    override fun getTopSportsNews(
        domains: List<String>,
        fromDate: LocalDate,
        sortBy: ArticleSortingOptions,
    ): Flow<Result<List<Article>>> =
        flow {
            emit(
                newsApiRepository.getTopSportsNews(
                    domains = domains,
                    fromDate = fromDate,
                    sortBy = sortBy.value
                )
            )
        }.map { result ->
            result.toResult { this.toCommonModel() }
        }
}
