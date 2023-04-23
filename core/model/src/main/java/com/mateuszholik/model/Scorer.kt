package com.mateuszholik.model

data class Scorer(
    val player: Player,
    val team: Team,
    val goals: Int,
    val assists: Int,
    val penalties: Int,
)
