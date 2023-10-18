package com.mateuszholik.uicomponents.score

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.MatchScore
import com.mateuszholik.model.Status
import com.mateuszholik.uicomponents.extensions.toText
import com.mateuszholik.uicomponents.utils.PreviewConstants

@Composable
internal fun DetailedScore(
    modifier: Modifier,
    matchScore: MatchScore,
    status: Status,
    shouldShowDuration: Boolean = true,
) {
    when (status) {
        Status.CANCELLED,
        Status.PAUSED,
        Status.POSTPONED,
        Status.SUSPENDED,
        Status.SCHEDULED,
        Status.TIMED -> {
            Text(
                modifier = modifier,
                text = "-",
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.textSizing.large
            )
        }
        Status.FINISHED -> {
            Text(
                modifier = modifier,
                text = "${matchScore.fullTime.home} : ${matchScore.fullTime.away}",
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.textSizing.large
            )
        }
        Status.IN_PLAY -> {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${matchScore.fullTime.home} : ${matchScore.fullTime.away}",
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.textSizing.large
                )
                if (shouldShowDuration) {
                    Text(
                        text = matchScore.duration.toText(),
                        color = MaterialTheme.colorScheme.error,
                        fontSize = MaterialTheme.textSizing.small
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        DetailedScore(
            modifier = Modifier,
            matchScore = PreviewConstants.MATCH_SCORE,
            status = Status.IN_PLAY)
    }
}

@Preview
@Composable
private fun ScheduledMatchDetailedScorePreview() {
    FootballScoreTheme {
        DetailedScore(
            modifier = Modifier,
            matchScore = PreviewConstants.MATCH_SCORE,
            status = Status.SCHEDULED)
    }
}
