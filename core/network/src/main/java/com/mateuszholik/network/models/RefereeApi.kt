package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class RefereeApi(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationality")
    val nationality: String? = null,
    @SerializedName("type")
    val type: String,
)
