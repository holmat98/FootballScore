package com.mateuszholik.data.repositories

import com.mateuszholik.data.extensions.toCommonModel
import com.mateuszholik.data.extensions.toResult
import com.mateuszholik.model.Article
import com.mateuszholik.model.Result
import com.mateuszholik.network.repositories.NewsApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface NewsRepository {

    fun getTopSportsNews(): Flow<Result<List<Article>>>
}

internal class NewsRepositoryImpl(
    private val newsApiRepository: NewsApiRepository,
) : NewsRepository {

    override fun getTopSportsNews(): Flow<Result<List<Article>>> =
        flow {
            emit(newsApiRepository.getTopSportsNews())
        }.map { result ->
            result.toResult { this.toCommonModel() }
        }
}
