package com.mateuszholik.uicomponents.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing

@Composable
fun SelectableButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(start = MaterialTheme.spacing.small)
            .clip(CircleShape)
            .background(
                if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.primaryContainer
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            text = text,
            color = if (isSelected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onPrimaryContainer
            },
            fontSize = MaterialTheme.textSizing.normal,
            textAlign = TextAlign.Center
        )
    }
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

@Preview
@Composable
private fun PreviewNotSelected() {
    FootballScoreTheme {
        SelectableButton(
            text = "Not selected",
            isSelected = false,
            onClick = {}
        )
    }
}
