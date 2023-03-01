package com.mateuszholik.uicomponents.info

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.model.ErrorType
import com.mateuszholik.designsystem.R

@Composable
fun ErrorInfo(errorType: ErrorType) {
    when (errorType) {
        ErrorType.UNKNOWN -> CommonErrorInfo(
            imageRes = R.drawable.ic_unknown_error,
            text = stringResource(R.string.error_unknown)
        )
        ErrorType.EMPTY_DATA -> CommonErrorInfo(
            imageRes = R.drawable.ic_empty_data,
            text = stringResource(R.string.error_empty_data)
        )
        ErrorType.INTERNET_ERROR -> CommonErrorInfo(
            imageRes = R.drawable.ic_no_internet,
            text = stringResource(R.string.error_no_internet)
        )
        ErrorType.CLIENT_ERROR -> CommonErrorInfo(
            imageRes = R.drawable.ic_client_error,
            text = stringResource(R.string.error_client)
        )
        ErrorType.SERVER_ERROR -> CommonErrorInfo(
            imageRes = R.drawable.ic_server_error,
            text = stringResource(R.string.error_server)
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    FootballScoreTheme {
        ErrorInfo(errorType = ErrorType.INTERNET_ERROR)
    }
}
