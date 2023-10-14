package com.mateuszholik.uicomponents.images

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.spacing

@Composable
fun RoundedImageWithBackground(
    imageUrl: String,
    @DrawableRes onErrorImageRes: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    innerPadding: Dp = MaterialTheme.spacing.extraSmall,
    cornerRadius: Dp = MaterialTheme.cornerRadius.small,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            url = imageUrl,
            contentDescription = contentDescription,
            onErrorImageRes = onErrorImageRes
        )
    }
}
