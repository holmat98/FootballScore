package com.mateuszholik.uicomponents.scorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.uicomponents.texts.SmallText

@Composable
fun ScorersListLegend(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    Row(
        modifier = modifier.background(color = backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            SmallText(
                text = "#",
                color = contentColor
            )
        }

        Box(
            modifier = Modifier
                .weight(9f)
                .padding(start = MaterialTheme.spacing.extraSmall)
        ) {
            SmallText(
                text = stringResource(R.string.scorers_list_legend_player).uppercase(),
                color = contentColor,
            )
        }

        SmallText(
            text = "G",
            color = contentColor
        )

        SmallText(
            text = "A",
            color = contentColor
        )

        SmallText(
            text = "P",
            color = contentColor
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        ScorersListLegend(modifier = Modifier.fillMaxWidth())
    }
}
