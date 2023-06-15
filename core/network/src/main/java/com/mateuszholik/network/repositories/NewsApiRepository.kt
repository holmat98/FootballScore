package com.mateuszholik.network.repositories

import com.mateuszholik.network.extensions.toResultApi
import com.mateuszholik.network.models.ArticleApi
import com.mateuszholik.network.models.ResultApi
import com.mateuszholik.network.services.NewsService
import javax.inject.Inject

interface NewsApiRepository {

    suspend fun getTopSportsNews(): ResultApi<List<ArticleApi>>
}

internal class NewsApiRepositoryImpl @Inject constructor(
    private val newsService: NewsService,
) : NewsApiRepository {

    override suspend fun getTopSportsNews(): ResultApi<List<ArticleApi>> =
        newsService.getTopSportsNews().toResultApi { this.articles }
}
