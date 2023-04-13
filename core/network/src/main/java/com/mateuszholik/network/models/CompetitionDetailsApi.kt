package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class CompetitionDetailsApi(
    @SerializedName("area")
    val area: AreaApi,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("emblem")
    val emblem: String,

)
