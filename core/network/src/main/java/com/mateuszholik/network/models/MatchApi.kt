package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class MatchApi(
    @SerializedName("area")
    val area: AreaApi,
    @SerializedName("awayTeam")
    val awayTeam: TeamApi,
    @SerializedName("competition")
    val competition: CompetitionApi,
    @SerializedName("homeTeam")
    val homeTeam: TeamApi,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastUpdated")
    val lastUpdated: String,
    @SerializedName("matchday")
    val matchday: Int,
    @SerializedName("referees")
    val referees: List<RefereeApi>,
    @SerializedName("score")
    val score: MatchScoreApi,
    @SerializedName("season")
    val season: SeasonApi,
    @SerializedName("stage")
    val stage: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("utcDate")
    val utcDate: String,
    @SerializedName("venue")
    val venue: String? = null,
)
