package com.mateuszholik.data.extensions

import com.mateuszholik.model.Match
import com.mateuszholik.model.MatchInfo

internal fun List<Match>.toListOfMatchInfo(): List<MatchInfo> =
    map {
        MatchInfo(
            id = it.id,
            awayTeam = it.awayTeam,
            homeTeam = it.homeTeam,
            score = it.score,
            status = it.status,
            utcDate = it.utcDate
        )
    }
