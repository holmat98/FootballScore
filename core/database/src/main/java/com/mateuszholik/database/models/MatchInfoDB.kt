package com.mateuszholik.database.models

import java.time.LocalDateTime

data class MatchInfoDB(
    val id: Int,
    val competition: CompetitionDB,
    val awayTeam: TeamDB,
    val homeTeam: TeamDB,
    val score: MatchScoreDB,
    val status: String,
    val utcDate: LocalDateTime,
)
