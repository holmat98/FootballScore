package com.mateuszholik.model

data class CompetitionDetails(
    val area: Area,
    val id: Int,
    val name: String,
    val code: String,
    val type: CompetitionType,
    val emblem: String,
)
