package com.mateuszholik.uicomponents.match

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.uicomponents.extensions.asFullDateString
import com.mateuszholik.uicomponents.score.Score
import com.mateuszholik.uicomponents.team.Teams
import com.mateuszholik.uicomponents.utils.PreviewConstants

@Composable
fun H2HMatch(
    matchInfo: MatchInfo,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = MaterialTheme.spacing.small),
            text = matchInfo.utcDate.asFullDateString(),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.textSizing.small,
            fontWeight = FontWeight.Bold
        )
        Teams(
            modifier = Modifier.weight(1f),
            homeTeam = matchInfo.homeTeam,
            awayTeam = matchInfo.awayTeam
        )
        Score(
            modifier = Modifier,
            score = matchInfo.score.fullTime
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        H2HMatch(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            matchInfo = PreviewConstants.FINISHED_MATCH_INFO
        )
    }
}
