package com.mateuszholik.uicomponents.images

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun Image(
    url: String,
    @DrawableRes onErrorImageRes: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    if (url.endsWith(suffix = "svg", ignoreCase = true)) {
        SvgImage(
            url = url,
            modifier = modifier,
            contentDescription = contentDescription,
            onErrorImageRes = onErrorImageRes
        )
    } else {
        AsyncImage(
            modifier = modifier,
            model = url,
            contentDescription = contentDescription,
            error = painterResource(onErrorImageRes),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun SvgImage(
    url: String,
    modifier: Modifier,
    contentDescription: String?,
    @DrawableRes onErrorImageRes: Int
) {
    val context = LocalContext.current
    val model = remember {
        ImageRequest.Builder(context)
            .data(url)
            .decoderFactory(SvgDecoder.Factory())
            .build()
    }

    AsyncImage(
        modifier = modifier,
        model = model,
        contentDescription = contentDescription,
        error = painterResource(onErrorImageRes),
        contentScale = ContentScale.FillWidth
    )
}
