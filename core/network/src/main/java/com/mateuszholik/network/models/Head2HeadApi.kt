package com.mateuszholik.network.models

import com.google.gson.annotations.SerializedName

data class Head2HeadApi(
    @SerializedName("aggregates")
    val aggregatedH2HData: AggregatedH2HDataApi,
    @SerializedName("matches")
    val matches: List<MatchApi>,
)
