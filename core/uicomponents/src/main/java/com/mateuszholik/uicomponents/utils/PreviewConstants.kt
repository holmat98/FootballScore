package com.mateuszholik.uicomponents.utils

import com.mateuszholik.model.Duration
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.MatchScore
import com.mateuszholik.model.Referee
import com.mateuszholik.model.RefereeType
import com.mateuszholik.model.Score
import com.mateuszholik.model.Status
import com.mateuszholik.model.Team
import com.mateuszholik.model.TeamH2HData
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
    val REFEREE = Referee(
        id = 1,
        name = "Name Surname",
        nationality = "Poland",
        type = RefereeType.REFEREE
    )
    val TEAM_H2H_DATA = TeamH2HData(
        draws = 5,
        id = 1,
        losses = 10,
        name = "ManCity",
        wins = 15
    )
    val TEAM_H2H_DATA_2 = TeamH2HData(
        draws = 5,
        id = 1,
        losses = 15,
        name = "ManUnited",
        wins = 10
    )
}
