package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class SourceApi(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String
)
