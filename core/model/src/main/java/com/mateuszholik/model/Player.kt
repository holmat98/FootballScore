package com.mateuszholik.model

import java.time.LocalDate
import java.time.LocalDateTime

data class Player(
    val id: Int,
    val name: String,
    val firstName: String,
    val lastName: String? = null,
    val dateOfBirth: LocalDate,
    val nationality: String,
    val position: String,
    val shirtNumber: Int,
    val lastUpdated: LocalDateTime,
)
