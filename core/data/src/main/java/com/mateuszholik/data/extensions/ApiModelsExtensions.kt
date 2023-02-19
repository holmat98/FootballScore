package com.mateuszholik.data.extensions

import com.mateuszholik.model.Competition
import com.mateuszholik.model.CompetitionType
import com.mateuszholik.model.Duration
import com.mateuszholik.model.Group
import com.mateuszholik.model.Head2Head
import com.mateuszholik.model.Match
import com.mateuszholik.model.MatchScore
import com.mateuszholik.model.Referee
import com.mateuszholik.model.RefereeType
import com.mateuszholik.model.Score
import com.mateuszholik.model.Season
import com.mateuszholik.model.Stage
import com.mateuszholik.model.Status
import com.mateuszholik.model.Team
import com.mateuszholik.model.TeamH2HData
import com.mateuszholik.model.Winner
import com.mateuszholik.network.models.AreaApi
import com.mateuszholik.network.models.CompetitionApi
import com.mateuszholik.network.models.Head2HeadApi
import com.mateuszholik.network.models.MatchApi
import com.mateuszholik.network.models.MatchScoreApi
import com.mateuszholik.network.models.MatchesApi
import com.mateuszholik.network.models.RefereeApi
import com.mateuszholik.network.models.ScoreApi
import com.mateuszholik.network.models.SeasonApi
import com.mateuszholik.network.models.TeamApi
import com.mateuszholik.network.models.TeamH2HDataApi

internal fun MatchesApi.toCommonModel(): List<Match> =
    matches.map { it.toCommonModel() }

internal fun MatchApi.toCommonModel(): Match =
    Match(
        awayTeam = awayTeam.toCommonModel(),
        competition = competition.toCommonModel(area),
        group = getGroup(group),
        homeTeam = homeTeam.toCommonModel(),
        id = id,
        lastUpdated = lastUpdated.toLocalDateTime(),
        matchday = matchday,
        referees = referees.map { it.toCommonModel() },
        score = score.toCommonModel(),
        season = season.toCommonModel(),
        stage = Stage.valueOf(stage),
        status = Status.valueOf(status),
        utcDate = utcDate.toLocalDateTime(),
        venue = venue.orEmpty()
    )

private fun getGroup(groupName: String?): Group =
    when (groupName) {
        "Group A" -> Group.GROUP_A
        "Group B" -> Group.GROUP_B
        "Group C" -> Group.GROUP_C
        "Group D" -> Group.GROUP_D
        "Group E" -> Group.GROUP_E
        "Group F" -> Group.GROUP_F
        "Group G" -> Group.GROUP_G
        "Group H" -> Group.GROUP_H
        else -> Group.N_A
    }

internal fun TeamApi.toCommonModel(): Team =
    Team(
        crest = crest,
        id = id,
        name = name,
        shortName = shortName,
        tla = tla
    )

internal fun CompetitionApi.toCommonModel(areaApi: AreaApi): Competition =
    Competition(
        code = code,
        countryCode = areaApi.code,
        countryName = areaApi.name,
        countryFlag = areaApi.flag,
        emblem = emblem,
        id = id,
        name = name,
        type = CompetitionType.valueOf(type)
    )

internal fun RefereeApi.toCommonModel(): Referee =
    Referee(
        id = id,
        name = name,
        nationality = nationality.orEmpty(),
        type = RefereeType.valueOf(type)
    )

internal fun MatchScoreApi.toCommonModel(): MatchScore =
    MatchScore(
        duration = Duration.valueOf(duration),
        fullTime = fullTime.toCommonModel(),
        halfTime = halfTime.toCommonModel(),
        winner = winner?.let { Winner.valueOf(it) } ?: Winner.N_A
    )

internal fun ScoreApi.toCommonModel(): Score =
    Score(
        home = home,
        away = away
    )

internal fun SeasonApi.toCommonModel(): Season =
    Season(
        currentMatchday = currentMatchday,
        endDate = endDate.toLocalDate(),
        id = id,
        startDate = startDate.toLocalDate(),
        winner = winner,
    )

internal fun Head2HeadApi.toCommonModel(): Head2Head =
    Head2Head(
        awayTeam = aggregatedH2HData.awayTeam.toCommonModel(),
        homeTeam = aggregatedH2HData.homeTeam.toCommonModel(),
        matches = matches.map { it.toCommonModel() },
        numberOfMatches = aggregatedH2HData.numberOfMatches,
        totalGoals = aggregatedH2HData.totalGoals
    )

internal fun TeamH2HDataApi.toCommonModel(): TeamH2HData =
    TeamH2HData(
        draws = draws,
        id = id,
        losses = losses,
        name = name,
        wins = wins
    )
