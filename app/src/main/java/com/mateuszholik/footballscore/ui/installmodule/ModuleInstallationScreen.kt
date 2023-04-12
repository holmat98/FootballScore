package com.mateuszholik.footballscore.ui.installmodule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManager.InstallationState
import com.mateuszholik.uicomponents.animations.AnimationWithText

@Composable
fun ModuleInstallationScreen(
    doOnInstallationSucceeded: () -> Unit,
    doOnInstallationFailed: () -> Unit,
    viewModel: ModuleInstallationViewModel = hiltViewModel(),
) {
    val installationState by viewModel.installationState.collectAsStateWithLifecycle()

    Content(
        installationState = installationState,
        doOnInstallationFailed = doOnInstallationFailed,
        doOnInstallationSucceeded = doOnInstallationSucceeded
    )
}

@Composable
private fun Content(
    installationState: InstallationState,
    doOnInstallationFailed: () -> Unit,
    doOnInstallationSucceeded: () -> Unit,
) {
    val text = installationState.toText

    when (installationState) {
        is InstallationState.AlreadyInstalled -> {
            LaunchedEffect(Unit) {
                doOnInstallationSucceeded()
            }
        }
        is InstallationState.Downloading -> DownloadingContent(
                downloadedData = installationState.downloadedData,
                totalDataToDownload = installationState.totalDataToDownload
            )
        is InstallationState.Canceled,
        is InstallationState.Canceling,
        is InstallationState.Failed -> ErrorContent(
            text = text,
            doOnInstallationFailed = doOnInstallationFailed
        )
        InstallationState.RequiresUserConfirmation,
        InstallationState.Pending,
        InstallationState.Unknown,
        InstallationState.Installing -> AnimationWithText(
            modifier = Modifier.fillMaxSize(),
            animationResId = R.raw.infinity_loading_anim,
            text = text,
            iterateForever = true
        )
        InstallationState.Installed -> AnimationWithText(
            modifier = Modifier.fillMaxSize(),
            animationResId = R.raw.downloaded_anim,
            text = text,
            doOnAnimationEnd = doOnInstallationSucceeded
        )
    }
}

private val InstallationState.toText
    @Composable
    @ReadOnlyComposable
    get() = stringResource(
        when (this) {
            is InstallationState.Canceled -> R.string.module_installation_description_canceled
            is InstallationState.Canceling -> R.string.module_installation_description_canceling
            is InstallationState.Failed -> R.string.module_installation_description_failed
            is InstallationState.Downloading -> R.string.module_installation_description_downloading
            is InstallationState.Installed -> R.string.module_installation_description_installed
            is InstallationState.Installing -> R.string.module_installation_description_installing
            is InstallationState.RequiresUserConfirmation -> R.string.module_installation_description_required_confirmation
            else -> R.string.module_installation_description
        }
    )

@Composable
private fun ErrorContent(
    text: String,
    doOnInstallationFailed: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimationWithText(
            animationResId = R.raw.failed_anim,
            text = text,
            iterateForever = false
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.normal),
            onClick = doOnInstallationFailed
        ) {
            Text(text = stringResource(R.string.button_cancel))
        }
    }
}

@Composable
private fun DownloadingContent(
    downloadedData: Long,
    totalDataToDownload: Long,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = (downloadedData / totalDataToDownload).toFloat(),
            color = MaterialTheme.colorScheme.primaryContainer,
            trackColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
        AnimationWithText(
            modifier = Modifier.fillMaxSize(),
            animationResId = R.raw.infinity_loading_anim,
            text = stringResource(R.string.module_installation_description_downloading),
            iterateForever = true
        )
    }
}
