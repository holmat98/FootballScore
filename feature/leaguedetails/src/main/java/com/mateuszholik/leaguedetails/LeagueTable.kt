package com.mateuszholik.leaguedetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import com.mateuszholik.model.CompetitionStandingsDetails
import com.mateuszholik.uicomponents.headers.SmallTextHeader
import com.mateuszholik.uicomponents.table.CompetitionTable

@OptIn(ExperimentalFoundationApi::class)
internal fun LazyListScope.leagueTable(
    tables: List<CompetitionStandingsDetails>,
) {
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
