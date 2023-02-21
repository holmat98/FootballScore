package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class AreaApi(
    @SerializedName("code")
    val code: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)
