package com.mateuszholik.footballscore.ui.installmodule

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManager
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManager.InstallationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ModuleInstallationViewModel @Inject constructor(
    dynamicModuleInstallationManager: DynamicModuleInstallationManager,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val moduleName: String = savedStateHandle[MODULE_NAME_ARGUMENT] ?: ""

    val installationState: StateFlow<InstallationState> =
        dynamicModuleInstallationManager.startInstallation(moduleName)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = InstallationState.PENDING
            )

    companion object {
        const val MODULE_NAME_ARGUMENT = "moduleName"
    }
}
