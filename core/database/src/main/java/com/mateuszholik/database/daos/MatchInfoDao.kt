package com.mateuszholik.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mateuszholik.database.models.MatchInfoEntity
import com.mateuszholik.database.models.MergedMatchInfo
import java.time.LocalDateTime

@Dao
internal interface MatchInfoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(matchInfoEntity: MatchInfoEntity)

    @Query(
        """
            SELECT match_info.id as id,
                   competition.id as competitionId,
                   competition.code as competitionCode,
                   competition.country_name as competitionCountryName,
                   competition.country_code as competitionCountryCode,
                   competition.country_flag as competitionCountryFlag,
                   competition.emblem as emblem,
                   competition.name as name,
                   competition.type as type,
                   home_team.id as homeTeamId,
                   home_team.crest as homeTeamCrest,
                   home_team.name as homeTeamName,
                   home_team.short_name as homeTeamShortName,
                   home_team.tla as homeTeamTla,
                   away_team.id as awayTeamId,
                   away_team.crest as awayTeamCrest,
                   away_team.name as awayTeamName,
                   away_team.short_name as awayTeamShortName,
                   away_team.tla as awayTeamTla,
                   match_info.duration as duration,
                   match_info.home_full_time_score as homeFullTimeScore,
                   match_info.away_full_time_score as awayFullTimeScore,
                   match_info.winner as winner,
                   match_info.status as status,
                   match_info.utc_date as utcDate
            FROM match_info
            JOIN competition ON match_info.competition_id = competition.id
            JOIN team as home_team ON match_info.home_team_id = home_team.id
            JOIN team as away_team ON match_info.away_team_id = away_team.id
            WHERE match_info.utc_date >= :dayStart AND match_info.utc_date < :dayEnd
        """
    )
    suspend fun getListOfMatchInfoFor(dayStart: LocalDateTime, dayEnd: LocalDateTime): List<MergedMatchInfo>
}
