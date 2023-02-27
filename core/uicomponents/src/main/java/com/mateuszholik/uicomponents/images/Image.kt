package com.mateuszholik.uicomponents.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun Image(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    if (url.endsWith(suffix = "svg", ignoreCase = true)) {
        SvgImage(
            url = url,
            modifier = modifier,
            contentDescription = contentDescription
        )
    } else {
        AsyncImage(
            modifier = modifier,
            model = url,
            contentDescription = contentDescription
        )
    }
}

@Composable
private fun SvgImage(
    url: String,
    modifier: Modifier,
    contentDescription: String?,
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = contentDescription
    )
}
