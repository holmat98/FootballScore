package com.mateuszholik.uicomponents.team

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.model.Team
import com.mateuszholik.uicomponents.utils.PreviewConstants

@Composable
internal fun Teams(
    modifier: Modifier,
    homeTeam: Team,
    awayTeam: Team,
) {
    Column(modifier = modifier) {
        HorizontalTeamItem(team = homeTeam)
        HorizontalTeamItem(team = awayTeam)
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
