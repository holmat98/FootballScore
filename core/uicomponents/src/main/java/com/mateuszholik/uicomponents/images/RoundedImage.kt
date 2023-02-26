package com.mateuszholik.uicomponents.images

import androidx.compose.foundation.background
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mateuszholik.designsystem.theme.Size
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RoundedImage(
    imageUrl: String,
    contentDescription: String? = null,
    size: Size = MaterialTheme.sizing.smallImage,
    padding: Dp = MaterialTheme.spacing.tiny,
    cornerRadius: Dp = MaterialTheme.cornerRadius.small,
    backgroundColor: Color = MaterialTheme.colorScheme.surface
) {
    GlideImage(
        modifier = Modifier
            .width(size.width)
            .padding(padding)
            .height(size.height)
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor),
        model = imageUrl,
        contentDescription = contentDescription,
    )
}
