package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

internal data class ScorersApi(
    @SerializedName("scorers")
    val scorers: List<ScorerApi>
)
