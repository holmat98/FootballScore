package com.mateuszholik.data.repositories

import com.mateuszholik.common.providers.CurrentDateProvider
import com.mateuszholik.data.extensions.toCommonModel
import com.mateuszholik.data.extensions.toListOfMatchInfo
import com.mateuszholik.data.extensions.toListOfMatchInfoDB
import com.mateuszholik.data.extensions.toResult
import com.mateuszholik.database.models.ResultDB
import com.mateuszholik.database.repositories.MatchesDBRepository
import com.mateuszholik.model.Competition
import com.mateuszholik.model.Head2Head
import com.mateuszholik.model.Match
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.Result
import com.mateuszholik.network.models.ResultApi
import com.mateuszholik.network.repositories.MatchesApiRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate

interface MatchesRepository {

    fun getMatchesForDate(date: LocalDate): Flow<Result<Map<Competition, List<MatchInfo>>>>

    fun getMatch(id: Int): Flow<Result<Match>>

    fun getMatchH2H(id: Int): Flow<Result<Head2Head>>

    fun getWatchedMatches(): Flow<Result<Map<Competition, List<MatchInfo>>>>

    fun getWatchedMatchesId(): Flow<Result<List<Int>>>

    suspend fun insertWatchedGame(id: Int)

    suspend fun deleteWatchedGame(id: Int)
}

@OptIn(FlowPreview::class)
internal class MatchesRepositoryImpl(
    private val matchesApiRepository: MatchesApiRepository,
    private val matchesDBRepository: MatchesDBRepository,
    private val currentDateProvider: CurrentDateProvider,
) : MatchesRepository {

    override fun getMatchesForDate(date: LocalDate): Flow<Result<Map<Competition, List<MatchInfo>>>> =
        if (currentDateProvider.provide().isAfter(date)) {
            getMatchesForDateFromDatabase(date)
                .flatMapConcat {
                    if (it is Result.Error) {
                        getMatchesForDateFromApi(
                            date = date,
                            shouldSaveToDatabase = true
                        )
                    } else {
                        flowOf(it)
                    }
                }
        } else {
            getMatchesForDateFromApi(date)
        }

    override fun getMatch(id: Int): Flow<Result<Match>> =
        flow {
            emit(matchesApiRepository.getMatch(id))
        }.map { resultApi ->
            resultApi.toResult {
                this.toCommonModel()
            }
        }

    override fun getMatchH2H(id: Int): Flow<Result<Head2Head>> =
        flow {
            emit(matchesApiRepository.getHead2HeadForMatch(id))
        }.map { resultApi ->
            resultApi.toResult {
                this.toCommonModel()
            }
        }

    override fun getWatchedMatches(): Flow<Result<Map<Competition, List<MatchInfo>>>> =
        flow {
            emit(matchesDBRepository.getWatchedGames())
        }.flatMapConcat { result ->
            if (result is ResultDB.Success) {
                flow {
                    emit(matchesApiRepository.getMatchesForIds(result.data))
                }
            } else {
                flowOf(ResultApi.EmptyBody())
            }
        }.map { resultApi ->
            resultApi.toResult {
                this.map { it.toCommonModel() }
                    .groupBy { it.competition }
                    .mapValues { it.value.toListOfMatchInfo() }
            }
        }

    override fun getWatchedMatchesId(): Flow<Result<List<Int>>> =
        flow {
            emit(matchesDBRepository.getWatchedGames().toResult())
        }

    override suspend fun insertWatchedGame(id: Int) =
        matchesDBRepository.insertWatchedGame(id)

    override suspend fun deleteWatchedGame(id: Int) =
        matchesDBRepository.deleteWatchedGame(id)

    private fun getMatchesForDateFromDatabase(
        date: LocalDate,
    ): Flow<Result<Map<Competition, List<MatchInfo>>>> =
        flow {
            emit(matchesDBRepository.getMatchesInfoFor(date))
        }.map { resultDB ->
            resultDB.toResult {
                this.groupBy { it.competition }
                    .mapKeys { it.key.toCommonModel() }
                    .mapValues {
                        it.value.map { matchInfoDB -> matchInfoDB.toCommonModel() }
                    }
            }
        }

    private fun getMatchesForDateFromApi(
        date: LocalDate,
        shouldSaveToDatabase: Boolean = false,
    ): Flow<Result<Map<Competition, List<MatchInfo>>>> =
        flow {
            emit(
                matchesApiRepository.getMatchesForDateRange(
                    dateFrom = date,
                    dateTo = date.plusDays(1)
                )
            )
        }.map { resultApi ->
            resultApi.toResult {
                this.map { it.toCommonModel() }
                    .groupBy { it.competition }
                    .mapValues { it.value.toListOfMatchInfo() }
            }
        }.onEach {
            if (shouldSaveToDatabase && it is Result.Success) {
                matchesDBRepository.saveMatchesInfo(it.data.toListOfMatchInfoDB())
            }
        }
}
