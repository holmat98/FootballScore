package com.mateuszholik.leaguedetails

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.model.CombinedCompetitionDetails
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LeagueDetailsScreen(
    onBackPressed: () -> Unit,
    viewModel: LeagueDetailsViewModel,
) {

    val combinedCompetitionDetailsUiState by viewModel.combinedCompetitionDetails.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                title = {},
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
            )
        },
        content = {
            when (combinedCompetitionDetailsUiState) {
                is UiState.Error ->
                    ErrorInfo(
                        (combinedCompetitionDetailsUiState as UiState.Error<CombinedCompetitionDetails>).errorType
                    )
                is UiState.Loading -> Loading()
                is UiState.Success ->
                    Content(
                        (combinedCompetitionDetailsUiState as UiState.Success<CombinedCompetitionDetails>).data
                    )
            }
        }
    )
}

@Composable
private fun Content(combinedCompetitionDetails: CombinedCompetitionDetails) {

}

