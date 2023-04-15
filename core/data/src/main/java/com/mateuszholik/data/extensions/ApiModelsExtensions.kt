package com.mateuszholik.data.extensions

import com.mateuszholik.model.Area
import com.mateuszholik.model.Competition
import com.mateuszholik.model.CompetitionDetails
import com.mateuszholik.model.CompetitionStandingsDetails
import com.mateuszholik.model.CompetitionType
import com.mateuszholik.model.Duration
import com.mateuszholik.model.Group
import com.mateuszholik.model.Head2Head
import com.mateuszholik.model.Match
import com.mateuszholik.model.MatchScore
import com.mateuszholik.model.Player
import com.mateuszholik.model.Referee
import com.mateuszholik.model.RefereeType
import com.mateuszholik.model.Score
import com.mateuszholik.model.Scorer
import com.mateuszholik.model.Season
import com.mateuszholik.model.SeasonWinner
import com.mateuszholik.model.Stage
import com.mateuszholik.model.Status
import com.mateuszholik.model.TablePosition
import com.mateuszholik.model.Team
import com.mateuszholik.model.TeamForm
import com.mateuszholik.model.TeamH2HData
import com.mateuszholik.model.Winner
import com.mateuszholik.network.models.AreaApi
import com.mateuszholik.network.models.CompetitionApi
import com.mateuszholik.network.models.CompetitionDetailsApi
import com.mateuszholik.network.models.CompetitionStandingsDetailsApi
import com.mateuszholik.network.models.Head2HeadApi
import com.mateuszholik.network.models.MatchApi
import com.mateuszholik.network.models.MatchScoreApi
import com.mateuszholik.network.models.PlayerApi
import com.mateuszholik.network.models.RefereeApi
import com.mateuszholik.network.models.ScoreApi
import com.mateuszholik.network.models.ScorerApi
import com.mateuszholik.network.models.SeasonApi
import com.mateuszholik.network.models.TablePositionApi
import com.mateuszholik.network.models.TeamApi
import com.mateuszholik.network.models.TeamH2HDataApi
import com.mateuszholik.network.models.WinnerApi

internal fun List<MatchApi>.toCommonModel(): List<Match> =
    this.map { it.toCommonModel() }

internal fun MatchApi.toCommonModel(): Match =
    Match(
        awayTeam = awayTeam.toCommonModel(),
        competition = competition.toCommonModel(area),
        group = Group.values().firstOrNull { it.groupName == group } ?: Group.N_A,
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

internal fun TeamApi.toCommonModel(): Team =
    Team(
        crest = crest,
        id = id,
        name = name,
        shortName = shortName,
        tla = tla.orEmpty()
    )

internal fun CompetitionApi.toCommonModel(areaApi: AreaApi): Competition =
    Competition(
        code = code,
        countryCode = areaApi.code,
        countryName = areaApi.name,
        countryFlag = areaApi.flag.orEmpty(),
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
        currentMatchday = currentMatchday ?: -1,
        endDate = endDate.toLocalDate(),
        id = id,
        startDate = startDate.toLocalDate(),
        winner = winner?.toCommonModel(),
    )

internal fun WinnerApi.toCommonModel(): SeasonWinner =
    SeasonWinner(
        id = id,
        name = name,
        shortName = shortName,
        tla = tla,
        crest = crest,
        address = address,
        website = website,
        founded = founded,
        clubColors = clubColors
    )

internal fun Head2HeadApi.toCommonModel(): Head2Head =
    Head2Head(
        awayTeam = aggregatedH2HData.awayTeam.toCommonModel(),
        homeTeam = aggregatedH2HData.homeTeam.toCommonModel(),
        matches = matches.map { it.toCommonModel() }.toListOfMatchInfo(),
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

internal fun CompetitionDetailsApi.toCommonModel(): CompetitionDetails =
    CompetitionDetails(
        area = area.toCommonModel(),
        id = id,
        name = name,
        code = code,
        type = type,
        emblem = emblem
    )

internal fun AreaApi.toCommonModel(): Area =
    Area(
        code = code,
        flag = flag.orEmpty(),
        id = id,
        name = name
    )

internal fun CompetitionStandingsDetailsApi.toCommonModel(): CompetitionStandingsDetails =
    CompetitionStandingsDetails(
        stage = stage,
        type = type,
        group = Group.values().firstOrNull { it.name == group } ?: Group.N_A,
        table = table.map { it.toCommonModel() }
    )

internal fun TablePositionApi.toCommonModel(): TablePosition =
    TablePosition(
        position = position,
        team = team.toCommonModel(),
        playedGames = playedGames,
        form = form.toListOfTeamForm(),
        won = won,
        draw = draw,
        lost = lost,
        points = points,
        goalsScored = goalsScored,
        goalsConceded = goalsConceded,
        goalsDifference = goalsDifference
    )

internal fun String?.toListOfTeamForm(): List<TeamForm> =
    this?.map { teamFormType ->
        TeamForm.values().firstOrNull { it.type == "$teamFormType" }
    }?.filterNotNull() ?: listOf(TeamForm.N_A)

internal fun ScorerApi.toCommonModel(): Scorer =
    Scorer(
        player = player.toCommonModel(),
        team = team.toCommonModel(),
        goals = goals,
        assists = assists,
        penalties = penalties
    )

internal fun PlayerApi.toCommonModel(): Player =
    Player(
        id = id,
        name = name,
        firstName = firstName,
        lastName = lastName,
        dateOfBirth = dateOfBirth.toLocalDate(),
        nationality = nationality,
        position = position,
        shirtNumber = shirtNumber ?: -1,
        lastUpdated = lastUpdated.toLocalDateTime()
    )
