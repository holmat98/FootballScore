package com.mateuszholik.database.extensions

import com.mateuszholik.database.models.CompetitionDB
import com.mateuszholik.database.models.MatchInfoDB
import com.mateuszholik.database.models.MatchScoreDB
import com.mateuszholik.database.models.MergedMatchInfo
import com.mateuszholik.database.models.ScoreDB
import com.mateuszholik.database.models.TeamDB

internal fun MergedMatchInfo.toMatchInfoDB(): MatchInfoDB =
    MatchInfoDB(
        id = id,
        competition = CompetitionDB(
            id = competitionId,
            code = competitionCode,
            countryCode = competitionCountryCode,
            countryFlag = competitionCountryFlag,
            countryName = competitionCountryName,
            emblem = competitionEmblem,
            name = competitionName,
            type = competitionType
        ),
        homeTeam = TeamDB(
            crest = homeTeamCrest,
            id = homeTeamId,
            name = homeTeamName,
            shortName = homeTeamShortName,
            tla = homeTeamTla
        ),
        awayTeam = TeamDB(
            crest = awayTeamCrest,
            id = awayTeamId,
            name = awayTeamName,
            shortName = awayTeamShortName,
            tla = awayTeamTla
        ),
        score = MatchScoreDB(
            duration = duration,
            fullTime = ScoreDB(
                away = awayFullTimeScore,
                home = homeFullTimeScore
            ),
            winner = winner
        ),
        status = status,
        utcDate = utcDate
    )
