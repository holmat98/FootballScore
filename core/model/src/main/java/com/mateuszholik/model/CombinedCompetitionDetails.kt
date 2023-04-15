package com.mateuszholik.model

data class CombinedCompetitionDetails(
    val area: Area,
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String,
    val standingsDetails: List<CompetitionStandingsDetails>,
    val topScorers: List<Scorer>,
)
