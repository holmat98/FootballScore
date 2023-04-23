package com.mateuszholik.uicomponents.animations

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.sizing

@Composable
fun Animation(
    @RawRes animationResId: Int,
    modifier: Modifier = Modifier,
    iterateForever: Boolean = false,
    doOnAnimationEnd: (() -> Unit)? = null,
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(animationResId))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = if (iterateForever) LottieConstants.IterateForever else 1
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress }
    )

    if (progress == 1.0f) {
        LaunchedEffect(Unit) { doOnAnimationEnd?.invoke() }
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        Surface {
            Animation(
                modifier = Modifier.size(MaterialTheme.sizing.large),
                animationResId = R.raw.loading_anim
            )
        }
    }
}
