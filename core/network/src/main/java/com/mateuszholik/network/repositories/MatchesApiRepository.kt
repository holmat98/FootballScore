package com.mateuszholik.network.repositories

import com.mateuszholik.network.extensions.asString
import com.mateuszholik.network.extensions.toResultApi
import com.mateuszholik.network.models.Head2HeadApi
import com.mateuszholik.network.models.MatchApi
import com.mateuszholik.network.models.ResultApi
import com.mateuszholik.network.services.MatchesService
import java.time.LocalDate

interface MatchesApiRepository {

    suspend fun getMatchesForDateRange(
        dateFrom: LocalDate,
        dateTo: LocalDate,
    ): ResultApi<List<MatchApi>>

    suspend fun getMatch(id: Int): ResultApi<MatchApi>

    suspend fun getHead2HeadForMatch(id: Int): ResultApi<Head2HeadApi>
}

internal class MatchesApiRepositoryImpl(
    private val matchesService: MatchesService,
) : MatchesApiRepository {

    override suspend fun getMatchesForDateRange(
        dateFrom: LocalDate,
        dateTo: LocalDate,
    ): ResultApi<List<MatchApi>> =
        matchesService.getMatchesForDateRange(
            dateFrom = dateFrom.asString(),
            dateTo = dateTo.asString()
        ).toResultApi { this.matches }

    override suspend fun getMatch(id: Int): ResultApi<MatchApi> =
        matchesService.getMatch(id).toResultApi()

    override suspend fun getHead2HeadForMatch(id: Int): ResultApi<Head2HeadApi> =
        matchesService.getHead2HeadForMatch(id).toResultApi()
}
