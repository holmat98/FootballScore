package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class AggregatedH2HDataApi(
    @SerializedName("awayTeam")
    val awayTeam: TeamH2HDataApi,
    @SerializedName("homeTeam")
    val homeTeam: TeamH2HDataApi,
    @SerializedName("numberOfMatches")
    val numberOfMatches: Int,
    @SerializedName("totalGoals")
    val totalGoals: Int,
)
