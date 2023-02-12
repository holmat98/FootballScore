package com.mateuszholik.network.models

data class ScoreApi(
    val duration: String,
    val fullTime: FullTimeScoreApi,
    val halfTime: HalfTimeScoreApi,
    val winner: String
)
