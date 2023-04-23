package com.mateuszholik.model

data class CompetitionStandingsDetails(
    val stage: String,
    val type: String,
    val group: Group,
    val table: List<TablePosition>,
)
