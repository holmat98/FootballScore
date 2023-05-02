package com.mateuszholik.database.models

data class CompetitionDB(
    val id: Int,
    val code: String,
    val countryName: String,
    val countryCode: String,
    val countryFlag: String,
    val emblem: String,
    val name: String,
    val type: String,
)
