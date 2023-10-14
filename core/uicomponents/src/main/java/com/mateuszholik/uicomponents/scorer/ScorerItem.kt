package com.mateuszholik.uicomponents.scorer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.Scorer
import com.mateuszholik.uicomponents.images.RoundedImageWithBackground
import com.mateuszholik.uicomponents.texts.SmallText
import com.mateuszholik.uicomponents.texts.TextWithBackground
import com.mateuszholik.uicomponents.texts.TextWithBackgroundColors
import com.mateuszholik.uicomponents.utils.PreviewConstants.SCORER

private val TEXT_WITH_BACKGROUND_HEIGHT = 37.5.dp

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
        TextWithBackground(
            modifier = Modifier
                .size(TEXT_WITH_BACKGROUND_HEIGHT)
                .padding(MaterialTheme.spacing.extraSmall),
            textSize = MaterialTheme.textSizing.small,
            text = "$position.",
            textWithBackgroundColors = TextWithBackgroundColors(
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

        RoundedImageWithBackground(
            modifier = Modifier
                .padding(MaterialTheme.spacing.normal)
                .size(MaterialTheme.sizing.normal),
            imageUrl = scorer.team.crest,
            cornerRadius = MaterialTheme.cornerRadius.medium,
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
