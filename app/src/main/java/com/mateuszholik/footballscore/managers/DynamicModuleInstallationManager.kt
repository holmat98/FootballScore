package com.mateuszholik.footballscore.managers

import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManager.InstallationState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf

interface DynamicModuleInstallationManager {

    fun startInstallation(moduleName: String): Flow<InstallationState>

    enum class InstallationState {
        UNKNOWN,
        PENDING,
        REQUIRES_USER_CONFIRMATION,
        DOWNLOADING,
        DOWNLOADED,
        INSTALLING,
        INSTALLED,
        FAILED,
        CANCELING,
        CANCELED
    }
}

internal class DynamicModuleInstallationManagerImpl(
    private val splitInstallManager: SplitInstallManager,
) : DynamicModuleInstallationManager {

    override fun startInstallation(moduleName: String): Flow<InstallationState> {
        if (moduleName in splitInstallManager.installedModules) {
            return flowOf(InstallationState.INSTALLED)
        }

        val request = createRequest(moduleName)

        splitInstallManager.startInstall(request)

        return callbackFlow {
            val listener = SplitInstallStateUpdatedListener { splitInstallSessionState ->
                trySend(splitInstallSessionState.status().toInstallationState)
            }

            splitInstallManager.registerListener(listener)

            awaitClose { splitInstallManager.unregisterListener(listener) }
        }
    }

    private fun createRequest(moduleName: String): SplitInstallRequest =
        SplitInstallRequest
            .newBuilder()
            .addModule(moduleName)
            .build()

    private val Int.toInstallationState: InstallationState
        get() = when (this) {
            SplitInstallSessionStatus.PENDING -> InstallationState.PENDING
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> InstallationState.REQUIRES_USER_CONFIRMATION
            SplitInstallSessionStatus.DOWNLOADING -> InstallationState.DOWNLOADING
            SplitInstallSessionStatus.DOWNLOADED -> InstallationState.DOWNLOADED
            SplitInstallSessionStatus.INSTALLING -> InstallationState.INSTALLING
            SplitInstallSessionStatus.INSTALLED -> InstallationState.INSTALLED
            SplitInstallSessionStatus.FAILED -> InstallationState.FAILED
            SplitInstallSessionStatus.CANCELING -> InstallationState.CANCELING
            SplitInstallSessionStatus.CANCELED -> InstallationState.CANCELED
            else -> InstallationState.UNKNOWN
        }
}
