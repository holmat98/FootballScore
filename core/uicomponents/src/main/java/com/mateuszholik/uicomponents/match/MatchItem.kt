package com.mateuszholik.uicomponents.match

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.Status
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.uicomponents.buttons.FavoriteButton
import com.mateuszholik.uicomponents.extensions.asHourString
import com.mateuszholik.uicomponents.utils.PreviewConstants.FINISHED_MATCH_INFO
import com.mateuszholik.uicomponents.utils.PreviewConstants.IN_PLAY_MATCH_INFO

@Composable
fun MatchItem(
    matchInfo: MatchInfo,
    onFavoriteButtonClicked: (Boolean) -> Unit,
    isAddedToFavorites: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (matchInfo.status != Status.FINISHED) {
            FavoriteButton(
                modifier = Modifier.padding(start = MaterialTheme.spacing.small),
                initialState = isAddedToFavorites,
                onClick = onFavoriteButtonClicked,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        when (matchInfo.status) {
            Status.FINISHED -> FinishedMatch(
                homeTeam = matchInfo.homeTeam,
                awayTeam = matchInfo.awayTeam,
                score = matchInfo.score.fullTime
            )
            Status.IN_PLAY -> InPlayMatch(
                homeTeam = matchInfo.homeTeam,
                awayTeam = matchInfo.awayTeam,
                score = matchInfo.score.fullTime
            )
            Status.PAUSED -> HalfTimeMatch(
                homeTeam = matchInfo.homeTeam,
                awayTeam = matchInfo.awayTeam,
                score = matchInfo.score.fullTime
            )
            Status.POSTPONED -> ScheduledMatch(
                homeTeam = matchInfo.homeTeam,
                awayTeam = matchInfo.awayTeam,
                text = stringResource(R.string.match_postponed)
            )
            Status.SCHEDULED,
            Status.TIMED,
            -> ScheduledMatch(
                homeTeam = matchInfo.homeTeam,
                awayTeam = matchInfo.awayTeam,
                text = matchInfo.utcDate.asHourString()
            )
            Status.CANCELLED -> ScheduledMatch(
                homeTeam = matchInfo.homeTeam,
                awayTeam = matchInfo.awayTeam,
                text = stringResource(R.string.match_cancelled)
            )
            Status.SUSPENDED -> ScheduledMatch(
                homeTeam = matchInfo.homeTeam,
                awayTeam = matchInfo.awayTeam,
                text = stringResource(R.string.match_suspended)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        Surface {
            Column {
                MatchItem(
                    modifier = Modifier.fillMaxWidth(),
                    matchInfo = IN_PLAY_MATCH_INFO,
                    onFavoriteButtonClicked = {},
                    isAddedToFavorites = false
                )
                MatchItem(
                    modifier = Modifier.fillMaxWidth(),
                    matchInfo = FINISHED_MATCH_INFO,
                    onFavoriteButtonClicked = {},
                    isAddedToFavorites = false
                )
            }
        }
    }
}
