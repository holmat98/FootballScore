package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class PlayerApi(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName("nationality")
    val nationality: String? = null,
    @SerializedName("position")
    val position: String? = null,
    @SerializedName("shirtNumber")
    val shirtNumber: Int? = null,
    @SerializedName("lastUpdated")
    val lastUpdated: String,
)
