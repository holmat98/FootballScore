package com.mateuszholik.footballscore.ui.installmodule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManager.InstallationState

@Composable
fun ModuleInstallationScreen(
    doOnInstallationSucceeded: () -> Unit,
    viewModel: ModuleInstallationViewModel = hiltViewModel(),
) {
    val installationState by viewModel.installationState.collectAsStateWithLifecycle()

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = installationState.name)
        }
    }

    if (installationState == InstallationState.INSTALLED) {
        LaunchedEffect(Unit) {
            doOnInstallationSucceeded()
        }
    }
}
