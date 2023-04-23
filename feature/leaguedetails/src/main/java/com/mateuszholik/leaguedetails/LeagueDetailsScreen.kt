package com.mateuszholik.leaguedetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.leaguedetails.models.Page
import com.mateuszholik.model.CombinedCompetitionDetails
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.headers.CompetitionHeader
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.texts.TextWithBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeagueDetailsScreen(
    onBackPressed: () -> Unit,
    viewModel: LeagueDetailsViewModel,
) {
    val combinedCompetitionDetailsUiState by viewModel.combinedCompetitionDetails.collectAsStateWithLifecycle()
    var topAppBarText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                title = { Text(text = topAppBarText) },
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
                is UiState.Success -> {
                    val combinedCompetitionDetails =
                        (combinedCompetitionDetailsUiState as UiState.Success<CombinedCompetitionDetails>).data
                    LaunchedEffect(Unit) {
                        topAppBarText = combinedCompetitionDetails.name
                    }
                    Content(
                        paddingValues = it,
                        combinedCompetitionDetails = combinedCompetitionDetails
                    )
                }
            }
        }
    )
}

@Composable
private fun Content(
    combinedCompetitionDetails: CombinedCompetitionDetails,
    paddingValues: PaddingValues,
) {
    var currentPage by remember { mutableStateOf(Page.COMPETITION_TABLE) }

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        item {
            with(combinedCompetitionDetails) {
                CompetitionHeader(
                    competitionType = type,
                    emblem = emblem,
                    name = name,
                    countryFlag = area.flag,
                    countryName = area.name
                )
            }
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                contentPadding = PaddingValues(vertical = MaterialTheme.spacing.extraSmall)
            ) {
                items(items = Page.values().toList()) {
                    TextWithBackground(
                        modifier = Modifier
                            .clickable { currentPage = it }
                            .padding(start = MaterialTheme.spacing.extraSmall),
                        text = stringResource(it.textResId),
                        backgroundColor = if (it == currentPage) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.primaryContainer
                        },
                        textColor = if (it == currentPage) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        },
                        textSize = MaterialTheme.textSizing.normal
                    )
                }
            }
        }
        when (currentPage) {
            Page.COMPETITION_TABLE -> leagueTable(tables = combinedCompetitionDetails.standingsDetails)
            Page.TOP_SCORERS -> topScorers(topScorers = combinedCompetitionDetails.topScorers)
            Page.WINNERS -> winners(seasons = combinedCompetitionDetails.seasons)
        }
    }
}

