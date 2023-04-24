package com.mateuszholik.uicomponents.texts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing

@Composable
fun SmallText(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Bold,
    padding: Dp = MaterialTheme.spacing.extraSmall,
) {
    Text(
        modifier = modifier.padding(padding),
        text = text,
        color = color,
        fontSize = MaterialTheme.textSizing.small,
        fontWeight = fontWeight,
    )
}