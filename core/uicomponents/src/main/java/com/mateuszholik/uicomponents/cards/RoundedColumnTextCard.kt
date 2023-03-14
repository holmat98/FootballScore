package com.mateuszholik.uicomponents.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing

@Composable
fun RoundedColumnTextCard(
    topText: String,
    bottomText: String,
    modifier: Modifier = Modifier,
    textSize: TextUnit = MaterialTheme.textSizing.normal,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    cornerRadius: Dp = MaterialTheme.cornerRadius.normal,
    innerPadding: Dp = MaterialTheme.spacing.small,
) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.elevatedCardColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
        ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(innerPadding),
                text = topText,
                fontSize = textSize
            )
            Text(
                modifier = Modifier.padding(innerPadding),
                text = bottomText,
                fontSize = textSize
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        RoundedColumnTextCard(
            modifier = Modifier,
            topText = "10",
            bottomText = "15"
        )
    }
}
