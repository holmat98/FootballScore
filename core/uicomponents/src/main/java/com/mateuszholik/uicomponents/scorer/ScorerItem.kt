package com.mateuszholik.uicomponents.scorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.model.Scorer
import com.mateuszholik.uicomponents.images.RoundedImage
import com.mateuszholik.uicomponents.texts.SmallText
import com.mateuszholik.uicomponents.utils.PreviewConstants.SCORER

@Composable
fun ScorerItem(
    position: Int,
    scorer: Scorer,
    backgroundColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.background(color = backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallText(
            text = "$position.",
            color = contentColor
        )

        RoundedImage(
            imageUrl = scorer.team.crest,
            cornerRadius = MaterialTheme.cornerRadius.medium,
            padding = MaterialTheme.spacing.extraSmall,
            size = MaterialTheme.sizing.normal,
            onErrorImageRes = R.drawable.ic_ball
        )

        Column(modifier = Modifier.weight(1f)) {
            SmallText(
                text = scorer.player.name,
                color = contentColor,
                padding = MaterialTheme.spacing.none
            )
            SmallText(
                text = scorer.team.name.uppercase(),
                color = contentColor,
                fontWeight = FontWeight.Normal,
                padding = MaterialTheme.spacing.none
            )
        }

        SmallText(
            text = "${scorer.goals}",
            color = contentColor
        )

        SmallText(
            text = "${scorer.assists}",
            color = contentColor
        )

        SmallText(
            text = "${scorer.penalties}",
            color = contentColor
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        ScorerItem(
            position = 1,
            scorer = SCORER,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}
