package com.mateuszholik.model

data class CompetitionStandingsDetails(
    val stage: Stage,
    val type: CompetitionTableType,
    val group: Group,
    val table: List<TablePosition>,
)
