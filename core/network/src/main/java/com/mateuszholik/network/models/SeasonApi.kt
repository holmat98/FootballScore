package com.mateuszholik.network.models

data class SeasonApi(
    val currentMatchday: Int,
    val endDate: String,
    val id: Int,
    val startDate: String,
    val winner: Any
)
