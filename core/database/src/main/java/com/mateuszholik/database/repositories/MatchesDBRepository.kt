package com.mateuszholik.database.repositories

import com.mateuszholik.database.daos.CompetitionDao
import com.mateuszholik.database.daos.MatchInfoDao
import com.mateuszholik.database.daos.TeamDao
import com.mateuszholik.database.daos.WatchedGameDao
import com.mateuszholik.database.extensions.toEntityModel
import com.mateuszholik.database.extensions.toMatchInfoDB
import com.mateuszholik.database.models.MatchInfoDB
import com.mateuszholik.database.models.ResultDB
import com.mateuszholik.database.models.entities.WatchedGameEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

interface MatchesDBRepository {

    suspend fun saveMatchesInfo(matchesInfoDB: List<MatchInfoDB>)

    suspend fun getMatchesInfoFor(date: LocalDate): ResultDB<List<MatchInfoDB>>

    fun getWatchedGames(): Flow<ResultDB<List<Int>>>

    suspend fun insertWatchedGame(id: Int)

    suspend fun deleteWatchedGame(id: Int)
}

internal class MatchesDBRepositoryImpl @Inject constructor(
    private val competitionDao: CompetitionDao,
    private val teamDao: TeamDao,
    private val matchInfoDao: MatchInfoDao,
    private val watchedGameDao: WatchedGameDao,
) : MatchesDBRepository {

    override suspend fun saveMatchesInfo(matchesInfoDB: List<MatchInfoDB>) =
        matchesInfoDB.forEach { matchInfoDB ->
            competitionDao.insert(matchInfoDB.competition.toEntityModel())
            teamDao.insert(matchInfoDB.homeTeam.toEntityModel())
            teamDao.insert(matchInfoDB.awayTeam.toEntityModel())
            matchInfoDao.insert(matchInfoDB.toEntityModel())
        }

    override suspend fun getMatchesInfoFor(date: LocalDate): ResultDB<List<MatchInfoDB>> {
        val mergedMatchesInfo =
            matchInfoDao.getListOfMatchInfoFor(
                dayStart = date.atStartOfDay(),
                dayEnd = date.plusDays(1).atStartOfDay()
            )

        return if (mergedMatchesInfo.isEmpty()) {
            ResultDB.EmptyBody()
        } else {
            val matchesInfoDB = mergedMatchesInfo.map { it.toMatchInfoDB() }

            ResultDB.Success(matchesInfoDB)
        }
    }

    override fun getWatchedGames(): Flow<ResultDB<List<Int>>> =
        watchedGameDao.getAllWatchedGames()
            .map {
                if (it.isEmpty()) {
                    ResultDB.EmptyBody()
                } else {
                    ResultDB.Success(it)
                }
            }

    override suspend fun insertWatchedGame(id: Int) =
        watchedGameDao.insert(WatchedGameEntity(id))

    override suspend fun deleteWatchedGame(id: Int) =
        watchedGameDao.delete(WatchedGameEntity(id))
}
