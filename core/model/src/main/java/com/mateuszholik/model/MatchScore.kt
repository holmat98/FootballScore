package com.mateuszholik.model

data class MatchScore(
    val duration: String,
    val fullTime: Score,
    val halfTime: Score,
    val winner: String,
)
