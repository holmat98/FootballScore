package com.mateuszholik.matches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mateuszholik.designsystem.R
import com.mateuszholik.model.Competition
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.UiState
import com.mateuszholik.uicomponents.calendar.Calendar
import com.mateuszholik.uicomponents.dialogs.DatePickerDialog
import com.mateuszholik.uicomponents.headers.CompetitionHeader
import com.mateuszholik.uicomponents.info.ErrorInfo
import com.mateuszholik.uicomponents.loading.Loading
import com.mateuszholik.uicomponents.match.MatchItem
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesScreen(
    onMatchClicked: (matchId: Int) -> Unit,
    viewModel: MatchesViewModel = hiltViewModel(),
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppBarState)

    var shouldShowDatePicker by remember { mutableStateOf(false) }
    val days = remember { viewModel.days }
    val currentDay by viewModel.currentDay.collectAsStateWithLifecycle()
    val matchesUiState by viewModel.matches.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                actions = {
                    IconButton(onClick = { shouldShowDatePicker = true }) {
                        Image(
                            painter = painterResource(R.drawable.ic_calendar),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                scrollBehavior = scrollBehavior
            )
        },
        content = { paddingValues ->
            when (matchesUiState) {
                is UiState.Loading -> Loading()
                is UiState.Success -> Content(
                    modifier = Modifier.padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                    days = days,
                    selectedDay = currentDay,
                    data = (matchesUiState as UiState.Success<Map<Competition, List<MatchInfo>>>).data,
                    onDaySelected = { viewModel.updateCurrentDate(it) },
                    onMatchClicked = onMatchClicked
                )
                is UiState.Error ->
                    ErrorInfo((matchesUiState as UiState.Error<Map<Competition, List<MatchInfo>>>).errorType)
            }

            if (shouldShowDatePicker) {
                DatePickerDialog(
                    title = stringResource(R.string.dialog_date_picker_title),
                    positiveButtonText = stringResource(R.string.button_ok),
                    negativeButtonText = stringResource(R.string.button_cancel),
                    onDateSelected = {
                        viewModel.updateCurrentDate(it)
                    },
                    onDismissRequest = {
                        shouldShowDatePicker = false
                    }
                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    modifier: Modifier,
    days: List<LocalDate>,
    selectedDay: LocalDate,
    data: Map<Competition, List<MatchInfo>>,
    onDaySelected: (LocalDate) -> Unit,
    onMatchClicked: (matchId: Int) -> Unit,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Calendar(
                days = days,
                selectedDay = selectedDay,
                onDaySelected = onDaySelected
            )
        }

        data.forEach { (competition, matches) ->
            stickyHeader {
                CompetitionHeader(competition = competition)
            }
            itemsIndexed(items = matches) { index, matchInfo ->
                MatchItem(
                    modifier = Modifier.clickable { onMatchClicked(matchInfo.id) },
                    matchInfo = matchInfo,
                )
                if (index < matches.lastIndex) {
                    Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                }
            }
        }
    }
}
