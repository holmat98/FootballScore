package com.mateuszholik.footballscore.managers

import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManager.InstallationState
import com.mateuszholik.footballscore.providers.CurrentActivityProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf

interface DynamicModuleInstallationManager {

    fun startInstallation(moduleName: String): Flow<InstallationState>

    sealed class InstallationState {

        object AlreadyInstalled : InstallationState()
        object Unknown : InstallationState()
        object Pending : InstallationState()
        object RequiresUserConfirmation : InstallationState()
        data class Downloading(val downloadedData: Long, val totalDataToDownload: Long) : InstallationState()
        object Installing : InstallationState()
        object Installed : InstallationState()
        object Failed : InstallationState()
        object Canceling : InstallationState()
        object Canceled : InstallationState()
    }
}

internal class DynamicModuleInstallationManagerImpl(
    private val currentActivityProvider: CurrentActivityProvider,
    private val splitInstallManager: SplitInstallManager,
) : DynamicModuleInstallationManager {

    override fun startInstallation(moduleName: String): Flow<InstallationState> {
        if (moduleName in splitInstallManager.installedModules) {
            return flowOf(InstallationState.AlreadyInstalled)
        }

        val request = createRequest(moduleName)

        splitInstallManager.startInstall(request)

        return callbackFlow {
            val listener = SplitInstallStateUpdatedListener { splitInstallSessionState ->
                val installationState = splitInstallSessionState.toInstallationState

                if (installationState is InstallationState.RequiresUserConfirmation) {
                    showUserConfirmationDialog(splitInstallSessionState)
                }

                trySend(installationState)
            }

            splitInstallManager.registerListener(listener)

            awaitClose { splitInstallManager.unregisterListener(listener) }
        }
    }

    private fun showUserConfirmationDialog(splitInstallSessionState: SplitInstallSessionState) {
        currentActivityProvider.currentActivity?.let { activity ->
            splitInstallManager.startConfirmationDialogForResult(
                splitInstallSessionState,
                activity,
                splitInstallSessionState.sessionId()
            )
        }
    }

    private fun createRequest(moduleName: String): SplitInstallRequest =
        SplitInstallRequest
            .newBuilder()
            .addModule(moduleName)
            .build()

    private val SplitInstallSessionState.toInstallationState: InstallationState
        get() = when (status()) {
            SplitInstallSessionStatus.PENDING -> InstallationState.Pending
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> InstallationState.RequiresUserConfirmation
            SplitInstallSessionStatus.DOWNLOADING -> InstallationState.Downloading(
                downloadedData = bytesDownloaded(),
                totalDataToDownload = totalBytesToDownload()
            )
            SplitInstallSessionStatus.DOWNLOADED -> InstallationState.Downloading(1, 1)
            SplitInstallSessionStatus.INSTALLING -> InstallationState.Installing
            SplitInstallSessionStatus.INSTALLED -> InstallationState.Installed
            SplitInstallSessionStatus.FAILED -> InstallationState.Failed
            SplitInstallSessionStatus.CANCELING -> InstallationState.Canceling
            SplitInstallSessionStatus.CANCELED -> InstallationState.Canceled
            else -> InstallationState.Unknown
        }
}
