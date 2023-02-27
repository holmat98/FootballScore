package com.mateuszholik.uicomponents.match

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.Team
import com.mateuszholik.uicomponents.R

@Composable
fun ScheduledMatch(
    modifier: Modifier,
    homeTeam: Team,
    awayTeam: Team,
    text: String,
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
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.tiny),
            text = text,
            fontSize = MaterialTheme.textSizing.small,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
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
                name = "Manchester City",
                shortName = "ManCity",
                tla = "MC"
            ),
            text = stringResource(R.string.match_postponed)
        )
    }
}
