package com.mateuszholik.network.repositories

import com.mateuszholik.network.extensions.asString
import com.mateuszholik.network.extensions.toResultApi
import com.mateuszholik.network.models.Head2HeadApi
import com.mateuszholik.network.models.MatchApi
import com.mateuszholik.network.models.MatchesApi
import com.mateuszholik.network.models.ResultApi
import com.mateuszholik.network.services.MatchesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate

interface MatchesApiRepository {

    fun getMatchesForDateRange(dateFrom: LocalDate, dateTo: LocalDate): Flow<ResultApi<MatchesApi>>

    fun getMatch(id: Int): Flow<ResultApi<MatchApi>>

    fun getHead2HeadForMatch(id: Int): Flow<ResultApi<Head2HeadApi>>
}

internal class MatchesApiRepositoryImpl(
    private val matchesService: MatchesService,
) : MatchesApiRepository {

    override fun getMatchesForDateRange(
        dateFrom: LocalDate,
        dateTo: LocalDate,
    ): Flow<ResultApi<MatchesApi>> =
        flow {
            emit(
                matchesService.getMatchesForDateRange(
                    dateFrom = dateFrom.asString(),
                    dateTo = dateTo.asString()
                ).toResultApi()
            )
        }

    override fun getMatch(id: Int): Flow<ResultApi<MatchApi>> =
        flow {
            emit(matchesService.getMatch(id).toResultApi())
        }

    override fun getHead2HeadForMatch(id: Int): Flow<ResultApi<Head2HeadApi>> =
        flow {
            emit(matchesService.getHead2HeadForMatch(id).toResultApi())
        }
}
