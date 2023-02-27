package com.mateuszholik.uicomponents.match

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.model.Score
import com.mateuszholik.model.Team

@Composable
fun FinishedMatch(
    modifier: Modifier,
    homeTeam: Team,
    awayTeam: Team,
    score: Score,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Teams(
            modifier = Modifier.weight(1f),
            homeTeam = homeTeam,
            awayTeam = awayTeam
        )
        Score(
            modifier = Modifier,
            score = score
        )
    }
}

@Preview
@Composable
private fun FinishedMatchPreview() {
    FootballScoreTheme {
        FinishedMatch(
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
                name = "Manchester City",
                shortName = "ManCity",
                tla = "MC"
            ),
            score = Score(1, 1)
        )
    }
}
