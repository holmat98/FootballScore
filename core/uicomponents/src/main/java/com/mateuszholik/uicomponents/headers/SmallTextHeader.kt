package com.mateuszholik.uicomponents.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing

@Composable
fun SmallTextHeader(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .background(backgroundColor)
    ) {
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            text = text.uppercase(),
            fontSize = MaterialTheme.textSizing.small,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        SmallTextHeader(text = "Text")
    }
}
