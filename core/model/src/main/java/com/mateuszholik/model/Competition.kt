package com.mateuszholik.model

data class Competition(
    val code: String,
    val countryName: String,
    val countryCode: String,
    val countryFlag: String,
    val emblem: String,
    val id: Int,
    val name: String,
    val type: CompetitionType,
)
