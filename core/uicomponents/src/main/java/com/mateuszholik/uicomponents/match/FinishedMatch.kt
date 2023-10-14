package com.mateuszholik.uicomponents.match

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.model.Score
import com.mateuszholik.model.Team
import com.mateuszholik.uicomponents.score.Score
import com.mateuszholik.uicomponents.team.Teams
import com.mateuszholik.uicomponents.utils.PreviewConstants.TEAM_1
import com.mateuszholik.uicomponents.utils.PreviewConstants.TEAM_2

@Composable
fun FinishedMatch(
    homeTeam: Team,
    awayTeam: Team,
    score: Score,
    modifier: Modifier = Modifier,
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
    Surface {
        FinishedMatch(
            modifier = Modifier,
            homeTeam = TEAM_1,
            awayTeam = TEAM_2,
            score = Score(1, 1)
        )
    }
}
