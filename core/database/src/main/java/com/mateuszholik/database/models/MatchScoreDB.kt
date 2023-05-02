package com.mateuszholik.database.models

data class MatchScoreDB(
    val duration: String,
    val fullTime: ScoreDB,
    val winner: String,
)
