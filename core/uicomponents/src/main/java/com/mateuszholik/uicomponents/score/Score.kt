package com.mateuszholik.uicomponents.score

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.Score

@Composable
internal fun Score(
    modifier: Modifier,
    score: Score,
    scoreColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(
                vertical = MaterialTheme.spacing.tiny,
                horizontal = MaterialTheme.spacing.extraSmall,
            ),
            text = "${score.home}",
            fontSize = MaterialTheme.textSizing.small,
            fontWeight = FontWeight.Bold,
            color = scoreColor
        )
        Text(
            modifier = Modifier.padding(
                vertical = MaterialTheme.spacing.tiny,
                horizontal = MaterialTheme.spacing.extraSmall,
            ),
            text = "${score.away}",
            fontSize = MaterialTheme.textSizing.small,
            fontWeight = FontWeight.Bold,
            color = scoreColor
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            Score(
                modifier = Modifier,
                score = Score(1, 1)
            )
        }
    }
}
