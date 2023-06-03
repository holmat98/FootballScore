package com.mateuszholik.data.extensions

import com.mateuszholik.database.models.CompetitionDB
import com.mateuszholik.database.models.MatchInfoDB
import com.mateuszholik.database.models.MatchScoreDB
import com.mateuszholik.database.models.ScoreDB
import com.mateuszholik.database.models.TeamDB
import com.mateuszholik.model.Competition
import com.mateuszholik.model.Match
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.MatchScore
import com.mateuszholik.model.Score
import com.mateuszholik.model.Team
import com.mateuszholik.model.WatchedMatchesMap
import com.mateuszholik.network.models.MatchApi

internal fun List<MatchApi>.toWatchedMatchesMap(): WatchedMatchesMap =
    this.map { it.toCommonModel() }
        .sortedByDescending { it.utcDate }
        .groupBy { it.utcDate.toLocalDate() }
        .mapValues { map ->
            map.value
                .groupBy { it.competition }
                .mapValues { it.value.toListOfMatchInfo() }
        }

internal fun List<MatchApi>.toMatchInfoMap(): Map<Competition, List<MatchInfo>> =
    this.map { it.toCommonModel() }
        .groupBy { it.competition }
        .mapValues { it.value.toListOfMatchInfo() }

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

internal fun Map<Competition, List<MatchInfo>>.toListOfMatchInfoDB(): List<MatchInfoDB> =
    map {
        it.value.map { matchInfo ->
            MatchInfoDB(
                id = matchInfo.id,
                competition = it.key.toDBModel(),
                homeTeam = matchInfo.homeTeam.toDBModel(),
                awayTeam = matchInfo.awayTeam.toDBModel(),
                score = matchInfo.score.toDBModel(),
                status = matchInfo.status.name,
                utcDate = matchInfo.utcDate
            )
        }
    }.flatten()

internal fun Competition.toDBModel(): CompetitionDB =
    CompetitionDB(
        id = id,
        code = code,
        countryName = countryName,
        countryCode = countryCode,
        countryFlag = countryFlag,
        emblem = emblem,
        name = name,
        type = type.name
    )

internal fun Team.toDBModel(): TeamDB =
    TeamDB(
        crest = crest,
        id = id,
        name = name,
        shortName = shortName,
        tla = tla
    )

internal fun MatchScore.toDBModel(): MatchScoreDB =
    MatchScoreDB(
        duration = duration.name,
        fullTime = fullTime.toDBModel(),
        winner = winner.name
    )

internal fun Score.toDBModel(): ScoreDB =
    ScoreDB(
        away = away,
        home = home
    )
