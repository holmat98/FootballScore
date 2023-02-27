package com.mateuszholik.uicomponents.images

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing

@Composable
fun RoundedImage(
    imageUrl: String,
    contentDescription: String? = null,
    size: Dp = MaterialTheme.sizing.normal,
    padding: Dp = MaterialTheme.spacing.medium,
    innerPadding: Dp = MaterialTheme.spacing.extraSmall,
    cornerRadius: Dp = MaterialTheme.cornerRadius.small,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
) {
    Box(
        modifier = Modifier
            .width(size)
            .height(size)
            .padding(padding)
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            url = imageUrl,
            contentDescription = contentDescription
        )
    }
}
