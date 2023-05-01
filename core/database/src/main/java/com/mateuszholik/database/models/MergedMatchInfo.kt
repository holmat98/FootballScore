package com.mateuszholik.database.models

import java.time.LocalDateTime

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
    val awayFullTImeScore: Int?,
    val winner: String,
    val utcDate: LocalDateTime,
)
