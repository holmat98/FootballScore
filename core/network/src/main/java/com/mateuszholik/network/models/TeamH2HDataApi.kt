package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class TeamH2HDataApi(
    @SerializedName("draws")
    val draws: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("losses")
    val losses: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("wins")
    val wins: Int,
)
