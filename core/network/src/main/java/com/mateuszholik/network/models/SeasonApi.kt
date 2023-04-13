package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class SeasonApi(
    @SerializedName("currentMatchday")
    val currentMatchday: Int? = null,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("winner")
    val winner: WinnerApi? = null,
)
