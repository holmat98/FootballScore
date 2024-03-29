package com.mateuszholik.uicomponents.utils

import com.mateuszholik.model.Area
import com.mateuszholik.model.Competition
import com.mateuszholik.model.CompetitionStandingsDetails
import com.mateuszholik.model.CompetitionTableType
import com.mateuszholik.model.CompetitionType
import com.mateuszholik.model.Duration
import com.mateuszholik.model.Group
import com.mateuszholik.model.Head2Head
import com.mateuszholik.model.Match
import com.mateuszholik.model.MatchInfo
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
import com.mateuszholik.model.TeamForm.WIN
import com.mateuszholik.model.TeamForm.DRAW
import com.mateuszholik.model.TeamForm.LOSE
import com.mateuszholik.model.TeamH2HData
import com.mateuszholik.model.Winner
import java.time.LocalDate
import java.time.LocalDateTime

object PreviewConstants {
    val DAYS = listOf(
        LocalDate.of(2023, 2, 18),
        LocalDate.of(2023, 2, 19),
        LocalDate.of(2023, 2, 20),
        LocalDate.of(2023, 2, 21),
        LocalDate.of(2023, 2, 22),
        LocalDate.of(2023, 2, 23),
        LocalDate.of(2023, 2, 24),
        LocalDate.of(2023, 2, 25),
        LocalDate.of(2023, 2, 26),
        LocalDate.of(2023, 2, 27),
        LocalDate.of(2023, 2, 28),
    )
    val SELECTED_DAY = LocalDate.of(2023, 2, 20)
    val AREA = Area(
        code = "",
        flag = "",
        id = 1,
        name = "Poland"
    )
    val COMPETITION = Competition(
        code = "code",
        countryName = "England",
        countryCode = "Eg",
        countryFlag = "",
        emblem = "",
        id = 1,
        name = "League",
        type = CompetitionType.LEAGUE
    )
    val COMPETITION_2 = Competition(
        code = "code",
        countryName = "Germany",
        countryCode = "Ge",
        countryFlag = "",
        emblem = "",
        id = 1,
        name = "League",
        type = CompetitionType.LEAGUE
    )
    val TEAM_1 = Team(
        crest = "",
        id = 1,
        name = "Team 1",
        shortName = "TM1",
        tla = "TM1"
    )
    val TEAM_2 = Team(
        crest = "",
        id = 2,
        name = "Team 2",
        shortName = "TM2",
        tla = "TM2"
    )
    val SCORE = Score(1, 1)
    val MATCH_SCORE = MatchScore(
        duration = Duration.REGULAR,
        fullTime = Score(1, 1),
        halfTime = Score(0, 1),
        winner = Winner.N_A
    )
    val IN_PLAY_MATCH_INFO = MatchInfo(
        id = 1,
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
        name = "Team1",
        wins = 15
    )
    val TEAM_H2H_DATA_2 = TeamH2HData(
        draws = 5,
        id = 1,
        losses = 15,
        name = "Team2",
        wins = 10
    )
    val SEASON = Season(
        currentMatchday = 1,
        endDate = LocalDate.of(2023, 5, 31),
        id = 1,
        startDate = LocalDate.of(2022, 8, 12),
        winner = null
    )
    val MATCH = Match(
        awayTeam = TEAM_2,
        competition = COMPETITION,
        group = Group.N_A,
        homeTeam = TEAM_1,
        id = 1,
        lastUpdated = LocalDateTime.of(2023, 3, 15, 12, 0, 0),
        matchday = 1,
        referees = listOf(REFEREE),
        score = MATCH_SCORE,
        season = SEASON,
        stage = Stage.REGULAR_SEASON,
        status = Status.IN_PLAY,
        utcDate = LocalDateTime.of(2023, 3, 15, 12, 0, 0),
        venue = "Stadium"
    )
    val H2H_DATA = Head2Head(
        awayTeam = TEAM_H2H_DATA,
        homeTeam = TEAM_H2H_DATA_2,
        matches = listOf(FINISHED_MATCH_INFO, SCHEDULED_MATCH_INFO),
        numberOfMatches = 2,
        totalGoals = 2
    )
    val PLAYER = Player(
        id = 1,
        name = "Mateusz Holik",
        firstName = "Mateusz",
        lastName = "Holik",
        dateOfBirth = LocalDate.of(1998, 8, 2),
        nationality = "Polish",
        position = "Midfielder",
        shirtNumber = 10,
        lastUpdated = LocalDateTime.of(2023, 4, 17, 12, 0, 0)
    )
    val SCORER = Scorer(
        player = PLAYER,
        team = TEAM_1,
        goals = 30,
        assists = 20,
        penalties = 5
    )
    val TABLE_POSITION = TablePosition(
        position = 1,
        team = TEAM_1,
        playedGames = 30,
        form = listOf(WIN, WIN, DRAW, LOSE, WIN),
        won = 20,
        draw = 7,
        lost = 3,
        points = 67,
        goalsScored = 61,
        goalsConceded = 15,
        goalsDifference = 46
    )
    val SEASON_WINNER = SeasonWinner(
        id = 1,
        name = "Team 1",
        shortName = "1",
        tla = "",
        crest = "",
        address = "Address",
        website = "",
        founded = 1899,
        clubColors = "red/white/blue"
    )
    val TABLE = listOf(
        TABLE_POSITION,
        TABLE_POSITION.copy(position = 2, points = 64, won = 19, lost = 4),
        TABLE_POSITION.copy(position = 3, points = 59, won = 17, draw = 8, lost = 5),
        TABLE_POSITION.copy(position = 10, points = 40, won = 10, draw = 10, lost = 10)
    )
    val COMPETITION_STANDINGS_DETAILS = CompetitionStandingsDetails(
        stage = Stage.REGULAR_SEASON,
        type = CompetitionTableType.HOME,
        group = Group.N_A,
        table = TABLE
    )
}
