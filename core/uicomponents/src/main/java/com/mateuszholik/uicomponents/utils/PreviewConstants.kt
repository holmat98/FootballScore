package com.mateuszholik.uicomponents.utils

import com.mateuszholik.model.Duration
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.MatchScore
import com.mateuszholik.model.Score
import com.mateuszholik.model.Status
import com.mateuszholik.model.Team
import com.mateuszholik.model.Winner
import java.time.LocalDateTime

internal object PreviewConstants {
    val TEAM_1 = Team(
        crest = "",
        id = 1,
        name = "Manchester United",
        shortName = "ManU",
        tla = "MU"
    )
    val TEAM_2 = Team(
        crest = "",
        id = 2,
        name = "Manchester City",
        shortName = "ManCity",
        tla = "MC"
    )
    val SCORE = Score(1, 1)
    val MATCH_SCORE = MatchScore(
        duration = Duration.REGULAR,
        fullTime = Score(1, 1),
        halfTime = Score(0, 1),
        winner = Winner.N_A
    )
    val IN_PLAY_MATCH_INFO = MatchInfo(
        homeTeam = TEAM_1,
        awayTeam = TEAM_2,
        score = MATCH_SCORE,
        status = Status.IN_PLAY,
        utcDate = LocalDateTime.of(2023, 3, 4, 15, 30, 0)
    )
    val SCHEDULED_MATCH_INFO = IN_PLAY_MATCH_INFO.copy(status = Status.SCHEDULED)
    val FINISHED_MATCH_INFO = IN_PLAY_MATCH_INFO.copy(status = Status.FINISHED)
}
