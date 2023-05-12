package com.mateuszholik.database.models.views

import androidx.room.DatabaseView
import java.time.LocalDateTime

@DatabaseView(
    """
        SELECT match_info.id as id,
               competition.id as competitionId,
               competition.code as competitionCode,
               competition.country_name as competitionCountryName,
               competition.country_code as competitionCountryCode,
               competition.country_flag as competitionCountryFlag,
               competition.emblem as competitionEmblem,
               competition.name as competitionName,
               competition.type as competitionType,
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
    """
)
internal data class MergedMatchInfo(
    val id: Int,
    val competitionId: Int,
    val competitionCode: String,
    val competitionCountryName: String,
    val competitionCountryCode: String,
    val competitionCountryFlag: String,
    val competitionEmblem: String,
    val competitionName: String,
    val competitionType: String,
    val homeTeamId: Int,
    val homeTeamCrest: String,
    val homeTeamName: String,
    val homeTeamShortName: String,
    val homeTeamTla: String,
    val awayTeamId: Int,
    val awayTeamCrest: String,
    val awayTeamName: String,
    val awayTeamShortName: String,
    val awayTeamTla: String,
    val duration: String,
    val homeFullTimeScore: Int?,
    val awayFullTimeScore: Int?,
    val winner: String,
    val status: String,
    val utcDate: LocalDateTime,
)
