package com.mateuszholik.uicomponents.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing

@Composable
fun FavoriteButton(
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    initialState: Boolean = false,
    tint: Color = Color.Yellow,
) {
    var isChecked by remember { mutableStateOf(initialState) }

    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
                isChecked = isChecked.not()
                onClick(isChecked)
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            imageVector = if (isChecked) {
                Icons.Filled.Favorite
            } else {
                Icons.Outlined.FavoriteBorder
            },
            contentDescription = null,
            tint = tint
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        FavoriteButton(initialState = false, onClick = {})
    }
}
