package com.mateuszholik.leaguedetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mateuszholik.model.CompetitionStandingsDetails
import com.mateuszholik.uicomponents.headers.SmallTextHeader
import com.mateuszholik.uicomponents.table.CompetitionTable

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun LeagueTable(
    tables: List<CompetitionStandingsDetails>,
) {
    LazyColumn {
        tables.forEach {
            stickyHeader { SmallTextHeader(text = "${it.stage} - ${it.type}") }

            item {
                CompetitionTable(
                    modifier = Modifier.fillMaxWidth(),
                    tableStandings = it.table
                )
            }
        }
    }
}
