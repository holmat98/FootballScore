package com.mateuszholik.footballscore.managers

import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManager.InstallationState
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManager.InstallationListener

internal interface DynamicModuleInstallationManager {

    fun startModuleInstallation(
        moduleName: String,
        installationListener: InstallationListener,
    )

    interface InstallationListener {

        fun onStatusChanged(installationState: InstallationState)
    }

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

    override fun startModuleInstallation(
        moduleName: String,
        installationListener: InstallationListener,
    ) {
        if (moduleName in splitInstallManager.installedModules) {
            installationListener.onStatusChanged(InstallationState.INSTALLED)
            return
        }

        val request = createRequest(moduleName)
        val listener =

        splitInstallManager.run {
            registerListener {
                installationListener.onStatusChanged(it.status().toInstallationState)
            }
            startInstall(request)
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
