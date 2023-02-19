package com.mateuszholik.model

data class MatchScore(
    val duration: Duration,
    val fullTime: Score,
    val halfTime: Score,
    val winner: Winner,
)
