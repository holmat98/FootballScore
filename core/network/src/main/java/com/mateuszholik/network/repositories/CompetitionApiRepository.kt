package com.mateuszholik.network.repositories

import com.mateuszholik.network.extensions.toResultApi
import com.mateuszholik.network.models.CompetitionDetailsApi
import com.mateuszholik.network.models.ResultApi
import com.mateuszholik.network.services.CompetitionService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface CompetitionApiRepository {

    fun getCompetition(id: Int): Flow<ResultApi<CompetitionDetailsApi>>
}

internal class CompetitionApiRepositoryImpl(
    private val competitionService: CompetitionService,
) : CompetitionApiRepository {

    override fun getCompetition(id: Int): Flow<ResultApi<CompetitionDetailsApi>> =
        flow {
            emit(competitionService.getCompetitionDetails(id).toResultApi())
        }
}
