package com.mateuszholik.uicomponents.team

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.model.Team
import com.mateuszholik.uicomponents.utils.PreviewConstants

@Composable
internal fun Teams(
    modifier: Modifier,
    homeTeam: Team,
    awayTeam: Team,
) {
    Column(modifier = modifier) {
        HorizontalTeamItem(
            modifier = Modifier.padding(
                PaddingValues(
                    top = MaterialTheme.spacing.tiny,
                    bottom = MaterialTheme.spacing.tiny,
                    start = MaterialTheme.spacing.small
                )
            ),
            team = homeTeam
        )
        HorizontalTeamItem(
            modifier = Modifier.padding(
                PaddingValues(
                    top = MaterialTheme.spacing.tiny,
                    bottom = MaterialTheme.spacing.tiny,
                    start = MaterialTheme.spacing.small
                )
            ),
            team = awayTeam
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        Teams(
            modifier = Modifier,
            homeTeam = PreviewConstants.TEAM_1,
            awayTeam = PreviewConstants.TEAM_2
        )
    }
}
