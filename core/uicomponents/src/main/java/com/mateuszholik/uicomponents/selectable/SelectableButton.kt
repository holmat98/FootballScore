package com.mateuszholik.uicomponents.selectable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.uicomponents.texts.TextWithBackground

@Composable
fun SelectableButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextWithBackground(
        modifier = modifier
            .clickable { onClick() }
            .padding(start = MaterialTheme.spacing.extraSmall),
        text = text,
        backgroundColor = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.primaryContainer
        },
        textColor = if (isSelected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onPrimaryContainer
        },
        textSize = MaterialTheme.textSizing.normal,
        textPadding = MaterialTheme.spacing.small
    )
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        SelectableButton(
            text = "Selected",
            isSelected = true,
            onClick = {}
        )
    }
}
