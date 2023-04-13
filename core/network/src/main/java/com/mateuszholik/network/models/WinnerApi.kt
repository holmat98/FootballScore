package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class WinnerApi(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("shortName")
    val shortName: String,
    @SerializedName("tla")
    val tla: String,
    @SerializedName("crest")
    val crest: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("founded")
    val founded: Int,
    @SerializedName("clubColors")
    val clubColors: String,
    @SerializedName("venue")
    val venue: String,
    @SerializedName("lastUpdated")
    val lastUpdated: String,
)
