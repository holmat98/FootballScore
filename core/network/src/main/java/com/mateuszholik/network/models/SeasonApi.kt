package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class SeasonApi(
    @SerializedName("currentMatchday")
    val currentMatchday: Int,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("winner")
    val winner: String? = null,
    @SerializedName("stages")
    val stages: List<String>?,
)
