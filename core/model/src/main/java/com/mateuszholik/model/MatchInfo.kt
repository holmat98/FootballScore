package com.mateuszholik.model

import java.time.LocalDateTime
data class MatchInfo(
    val awayTeam: Team,
    val homeTeam: Team,
    val score: MatchScore,
    val status: Status,
    val utcDate: LocalDateTime,
)
