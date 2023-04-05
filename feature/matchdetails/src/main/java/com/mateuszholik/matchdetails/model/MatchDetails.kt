package com.mateuszholik.matchdetails.model

import com.mateuszholik.model.Head2Head
import com.mateuszholik.model.Match

data class MatchDetails(
    val match: Match,
    val h2hData: Head2Head,
)
