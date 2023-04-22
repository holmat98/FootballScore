package com.mateuszholik.model

data class CombinedCompetitionDetails(
    val area: Area,
    val id: Int,
    val name: String,
    val code: String,
    val type: CompetitionType,
    val emblem: String,
    val currentSeason: Season,
    val seasons: List<Season>,
    val standingsDetails: List<CompetitionStandingsDetails>,
    val topScorers: List<Scorer>,
)
