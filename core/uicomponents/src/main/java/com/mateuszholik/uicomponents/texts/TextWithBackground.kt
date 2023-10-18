package com.mateuszholik.uicomponents.texts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing

@Composable
fun TextWithBackground(
    text: String,
    modifier: Modifier = Modifier,
    textWithBackgroundColors: TextWithBackgroundColors = TextWithBackgroundDefaults.defaultColors(),
    textSize: TextUnit = MaterialTheme.textSizing.extraSmall,
    textPadding: Dp = MaterialTheme.spacing.extraSmall,
    shape: Shape = CircleShape,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(textWithBackgroundColors.backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(textPadding),
            text = text,
            color = textWithBackgroundColors.textColor,
            fontSize = textSize,
            textAlign = TextAlign.Center
        )
    }
}

data class TextWithBackgroundColors(
    val backgroundColor: Color,
    val textColor: Color,
)

object TextWithBackgroundDefaults {

    @Composable
    fun defaultColors(): TextWithBackgroundColors =
        TextWithBackgroundColors(
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        TextWithBackground(text = "cos")
    }
}
