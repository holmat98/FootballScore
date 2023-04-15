package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

internal data class StandingsApi(
    @SerializedName("standings")
    val standingsDetails: List<CompetitionStandingsDetailsApi>
)
