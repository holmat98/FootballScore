package com.mateuszholik.network.models

data class Head2HeadApi(
    val aggregates: AggregatesApi,
    val matches: List<MatchApi>
)
