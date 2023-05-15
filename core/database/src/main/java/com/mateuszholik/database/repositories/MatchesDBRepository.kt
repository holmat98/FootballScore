package com.mateuszholik.database.repositories

import com.mateuszholik.database.daos.CompetitionDao
import com.mateuszholik.database.daos.MatchInfoDao
import com.mateuszholik.database.daos.TeamDao
import com.mateuszholik.database.daos.WatchedGameDao
import com.mateuszholik.database.extensions.toEntityModel
import com.mateuszholik.database.extensions.toMatchInfoDB
import com.mateuszholik.database.models.MatchInfoDB
import com.mateuszholik.database.models.ResultDB
import java.time.LocalDate
import javax.inject.Inject

interface MatchesDBRepository {

    suspend fun saveMatchesInfo(matchesInfoDB: List<MatchInfoDB>)

    suspend fun getMatchesInfoFor(date: LocalDate): ResultDB<List<MatchInfoDB>>

    suspend fun getWatchedGames(): ResultDB<List<Int>>
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

    override suspend fun getWatchedGames(): ResultDB<List<Int>> =
        ResultDB.Success(watchedGameDao.getAllWatchedGames())
}
