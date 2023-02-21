package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class ScoreApi(
    @SerializedName("away")
    val away: Int?,
    @SerializedName("home")
    val home: Int?,
)
