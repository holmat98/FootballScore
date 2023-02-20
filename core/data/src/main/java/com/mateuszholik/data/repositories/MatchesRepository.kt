package com.mateuszholik.data.repositories

import com.mateuszholik.data.extensions.toCommonModel
import com.mateuszholik.data.extensions.toResult
import com.mateuszholik.model.Head2Head
import com.mateuszholik.model.Match
import com.mateuszholik.model.Result
import com.mateuszholik.network.repositories.MatchesApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

interface MatchesRepository {

    fun getMatchesForDate(date: LocalDate): Flow<Result<List<Match>>>

    fun getMatch(id: Int): Flow<Result<Match>>

    fun getMatchH2H(id: Int): Flow<Result<Head2Head>>
}

internal class MatchesRepositoryImpl(
    private val matchesApiRepository: MatchesApiRepository,
) : MatchesRepository {

    override fun getMatchesForDate(date: LocalDate): Flow<Result<List<Match>>> =
        matchesApiRepository.getMatchesForDateRange(
            dateFrom = date,
            dateTo = date.plusDays(1)
        ).map { resultApi ->
            resultApi.toResult {
                this.toCommonModel()
            }
        }

    override fun getMatch(id: Int): Flow<Result<Match>> =
        matchesApiRepository.getMatch(id)
            .map { resultApi ->
                resultApi.toResult {
                    this.toCommonModel()
                }
            }

    override fun getMatchH2H(id: Int): Flow<Result<Head2Head>> =
        matchesApiRepository.getHead2HeadForMatch(id)
            .map { resultApi ->
                resultApi.toResult {
                    this.toCommonModel()
                }
            }
}
