package com.mateuszholik.uicomponents.match

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.Match
import com.mateuszholik.model.Score
import com.mateuszholik.model.Status
import com.mateuszholik.model.Team
import com.mateuszholik.uicomponents.R
import com.mateuszholik.uicomponents.images.RoundedImage
import java.time.LocalDateTime

@Composable
fun MatchItem(
    match: Match,
    modifier: Modifier = Modifier,
) {
    when (match.status) {
        Status.FINISHED -> Match(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam,
            score = match.score.fullTime
        )
        Status.TIMED -> ScheduledMatch(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam,
            dateTime = match.utcDate
        )
        Status.IN_PLAY -> Match(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam,
            score = match.score.fullTime,
            scoreColor = MaterialTheme.colorScheme.primary
        )
        Status.POSTPONED -> PostponedMatch(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam
        )
        Status.SCHEDULED -> ScheduledMatch(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam,
            dateTime = match.utcDate
        )
    }
}

@Composable
private fun Match(
    modifier: Modifier,
    homeTeam: Team,
    awayTeam: Team,
    score: Score,
    scoreColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    RowItem(
        modifier = modifier,
        leftContent = {
            Teams(
                modifier = it,
                homeTeam = homeTeam,
                awayTeam = awayTeam
            )
        },
        rightContent = {
            Score(
                modifier = Modifier,
                score = score,
                scoreColor = scoreColor
            )
        }
    )
}

@Composable
private fun ScheduledMatch(
    modifier: Modifier,
    homeTeam: Team,
    awayTeam: Team,
    dateTime: LocalDateTime,
) {
    RowItem(
        modifier = modifier,
        leftContent = {
            Teams(
                modifier = it,
                homeTeam = homeTeam,
                awayTeam = awayTeam
            )
        },
        rightContent = {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.tiny),
                text = "${dateTime.hour}:${dateTime.minute}",
                fontSize = MaterialTheme.textSizing.small,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    )
}

@Composable
private fun PostponedMatch(
    modifier: Modifier,
    homeTeam: Team,
    awayTeam: Team,
) {
    RowItem(
        modifier = modifier,
        leftContent = {
            Teams(
                modifier = it,
                homeTeam = homeTeam,
                awayTeam = awayTeam
            )
        },
        rightContent = {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.tiny),
                text = stringResource(R.string.match_postponed),
                fontSize = MaterialTheme.textSizing.small,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    )
}

@Composable
private fun RowItem(
    modifier: Modifier,
    leftContent: @Composable (modifier: Modifier) -> Unit,
    rightContent: @Composable () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leftContent(modifier = Modifier.weight(1f))
        rightContent()
    }
}

@Composable
private fun Teams(
    modifier: Modifier,
    homeTeam: Team,
    awayTeam: Team,
) {
    Column(modifier = modifier) {
        TeamItem(team = homeTeam)
        TeamItem(team = awayTeam)
    }
}

@Composable
private fun TeamItem(team: Team) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RoundedImage(
            imageUrl = team.crest,
            size = MaterialTheme.sizing.tinyImage,
            cornerRadius = MaterialTheme.cornerRadius.small
        )
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.tiny),
            text = team.name,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.textSizing.small
        )
    }
}

@Composable
private fun Score(
    modifier: Modifier,
    score: Score,
    scoreColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.tiny),
            text = "${score.home}",
            fontSize = MaterialTheme.textSizing.small,
            color = scoreColor
        )
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.tiny),
            text = "${score.away}",
            fontSize = MaterialTheme.textSizing.small,
            color = scoreColor
        )
    }
}

@Preview
@Composable
private fun ScheduledMatchPreview() {
    FootballScoreTheme {
        ScheduledMatch(
            modifier = Modifier,
            homeTeam = Team(
                crest = "",
                id = 1,
                name = "Manchester United",
                shortName = "ManU",
                tla = "MU"
            ),
            awayTeam = Team(
                crest = "",
                id = 2,
                name = "Manchester United",
                shortName = "ManU",
                tla = "MU"
            ),
            dateTime = LocalDateTime.now()
        )
    }
}

@Preview
@Composable
private fun InPlayMatchPreview() {
    FootballScoreTheme {
        Match(
            modifier = Modifier,
            homeTeam = Team(
                crest = "",
                id = 1,
                name = "Manchester United",
                shortName = "ManU",
                tla = "MU"
            ),
            awayTeam = Team(
                crest = "",
                id = 2,
                name = "Manchester United",
                shortName = "ManU",
                tla = "MU"
            ),
            score = Score(1, 1),
            scoreColor = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
private fun PostponedMatchPreview() {
    FootballScoreTheme {
        PostponedMatch(
            modifier = Modifier,
            homeTeam = Team(
                crest = "",
                id = 1,
                name = "Manchester United",
                shortName = "ManU",
                tla = "MU"
            ),
            awayTeam = Team(
                crest = "",
                id = 2,
                name = "Manchester United",
                shortName = "ManU",
                tla = "MU"
            )
        )
    }
}
