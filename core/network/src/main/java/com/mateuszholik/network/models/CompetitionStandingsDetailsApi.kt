package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class CompetitionStandingsDetailsApi(
    @SerializedName("stage")
    val stage: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("group")
    val group: String? = null,
    @SerializedName("table")
    val table: List<TablePositionApi>,
)
