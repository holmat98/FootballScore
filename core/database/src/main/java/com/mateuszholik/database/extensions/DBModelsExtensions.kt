package com.mateuszholik.database.extensions

import com.mateuszholik.database.models.CompetitionDB
import com.mateuszholik.database.models.CompetitionEntity
import com.mateuszholik.database.models.MatchInfoDB
import com.mateuszholik.database.models.MatchInfoEntity
import com.mateuszholik.database.models.TeamDB
import com.mateuszholik.database.models.TeamEntity

internal fun CompetitionDB.toEntityModel(): CompetitionEntity =
    CompetitionEntity(
        id = id,
        code = code,
        countryCode = countryCode,
        countryFlag = countryFlag,
        countryName = countryName,
        emblem = emblem,
        name = name,
        type = type
    )

internal fun TeamDB.toEntityModel(): TeamEntity =
    TeamEntity(
        id = id,
        crest = crest,
        name = name,
        shortName = shortName,
        tla = tla
    )

internal fun MatchInfoDB.toEntityModel(): MatchInfoEntity =
    MatchInfoEntity(
        id = id,
        competitionId = competition.id,
        awayTeamId = awayTeam.id,
        homeTeamId = homeTeam.id,
        duration = score.duration,
        homeFullTimeScore = score.fullTime.home,
        awayFullTimeScore = score.fullTime.away,
        winner = score.winner,
        status = status,
        utcDate = utcDate
    )
