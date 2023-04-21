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
    textSize: TextUnit = MaterialTheme.textSizing.extraSmall,
    textColor: Color = MaterialTheme.colorScheme.primaryContainer,
    backgroundColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    textPadding: Dp = MaterialTheme.spacing.extraSmall,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(textPadding),
            text = text,
            color = textColor,
            fontSize = textSize
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        TextWithBackground(text = "cos")
    }
}
