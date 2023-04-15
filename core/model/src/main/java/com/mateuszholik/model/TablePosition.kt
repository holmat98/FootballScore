package com.mateuszholik.model

data class TablePosition(
    val position: Int,
    val team: Team,
    val playedGames: Int,
    val form: List<TeamForm>,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val points: Int,
    val goalsScored: Int,
    val goalsConceded: Int,
    val goalsDifference: Int,
)
