package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class ScorerApi(
    @SerializedName("player")
    val player: PlayerApi,
    @SerializedName("team")
    val team: TeamApi,
    @SerializedName("goals")
    val goals: Int,
    @SerializedName("assists")
    val assists: Int,
    @SerializedName("penalties")
    val penalties: Int,
)
