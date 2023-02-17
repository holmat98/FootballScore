package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class MatchScoreApi(
    @SerializedName("duration")
    val duration: String,
    @SerializedName("fullTime")
    val fullTime: ScoreApi,
    @SerializedName("halfTime")
    val halfTime: ScoreApi,
    @SerializedName("winner")
    val winner: String,
)
