package com.mateuszholik.uicomponents.animations

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing

@Composable
fun AnimationWithText(
    @RawRes animationResId: Int,
    text: String,
    modifier: Modifier = Modifier,
    animationSize: Dp = MaterialTheme.sizing.large,
    iterateForever: Boolean = false,
    doOnAnimationEnd: (() -> Unit)? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Animation(
            modifier = Modifier.size(animationSize),
            animationResId = animationResId,
            iterateForever = iterateForever,
            doOnAnimationEnd = doOnAnimationEnd
        )
        Text(
            modifier = Modifier.padding(top = MaterialTheme.spacing.normal),
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}
