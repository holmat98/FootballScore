package com.mateuszholik.uicomponents.team

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.model.Team

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
            modifier = Modifier.background(color = MaterialTheme.colorScheme.surface),
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
                name = "Manchester City",
                shortName = "ManCity",
                tla = "MC"
            )
        )
    }
}
