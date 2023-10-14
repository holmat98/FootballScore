package com.mateuszholik.uicomponents.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing

@Composable
internal fun CommonErrorInfo(
    @DrawableRes imageRes: Int,
    text: String,
    imageContentDescription: String? = null,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.normal),
            painter = painterResource(imageRes),
            contentDescription = imageContentDescription
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.normal),
            text = text,
            color = MaterialTheme.colorScheme.error,
            fontSize = MaterialTheme.textSizing.large,
            textAlign = TextAlign.Center
        )

    }

}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    FootballScoreTheme {
        CommonErrorInfo(
            imageRes = R.drawable.ic_client_error,
            text = stringResource(R.string.error_client),
        )
    }
}
