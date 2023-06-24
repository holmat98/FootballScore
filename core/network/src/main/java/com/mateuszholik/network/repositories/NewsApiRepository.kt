package com.mateuszholik.network.repositories

import com.mateuszholik.network.extensions.asString
import com.mateuszholik.network.extensions.toResultApi
import com.mateuszholik.network.models.ArticleApi
import com.mateuszholik.network.models.ResultApi
import com.mateuszholik.network.services.NewsService
import java.time.LocalDate
import javax.inject.Inject

interface NewsApiRepository {

    /**
     * @param domains - list of domains from which articles are downloaded, e.g. "google.com"
     * @param fromDate - A date and optional time for the oldest article allowed.
     * @param sortBy - The order to sort the articles in (relevancy, popularity, publishedAt)
     */
    suspend fun getTopSportsNews(
        domains: List<String>,
        fromDate: LocalDate,
        sortBy: String,
    ): ResultApi<List<ArticleApi>>
}

internal class NewsApiRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
) : NewsApiRepository {

    override suspend fun getTopSportsNews(
        domains: List<String>,
        fromDate: LocalDate,
        sortBy: String,
    ): ResultApi<List<ArticleApi>> =
        newsService.getTopSportsNews(
            domains = domains.joinToString(","),
            fromDate = fromDate.asString(),
            sortBy = sortBy
        ).toResultApi { this.articles }
}
