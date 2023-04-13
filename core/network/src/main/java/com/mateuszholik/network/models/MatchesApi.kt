package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

internal data class MatchesApi(
    @SerializedName("matches")
    val matches: List<MatchApi>,
)
