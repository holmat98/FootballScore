package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class MatchesApi(
    @SerializedName("matches")
    val matches: List<MatchApi>,
)
