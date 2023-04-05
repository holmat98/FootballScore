package com.mateuszholik.uicomponents.headers

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.designsystem.R

@Composable
fun SmallImageHeader(
    @DrawableRes imageRes: Int,
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            painter = painterResource(imageRes),
            contentDescription = null
        )
        Text(
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
        SmallImageHeader(
            imageRes = R.drawable.ic_ball,
            text = "Text"
        )
    }
}
