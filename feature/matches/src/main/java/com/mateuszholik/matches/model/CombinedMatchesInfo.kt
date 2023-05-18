package com.mateuszholik.matches.model

import com.mateuszholik.model.Competition
import com.mateuszholik.model.MatchInfo

data class CombinedMatchesInfo(
    val matches: Map<Competition, List<MatchInfo>>,
    val watchedMatchesIds: List<Int> = emptyList(),
)
