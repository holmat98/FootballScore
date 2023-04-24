package com.mateuszholik.leaguedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.model.CompetitionStandingsDetails
import com.mateuszholik.model.Group
import com.mateuszholik.uicomponents.headers.SmallTextHeader
import com.mateuszholik.uicomponents.selectable.SelectableButton
import com.mateuszholik.uicomponents.table.CompetitionTable

internal fun LazyListScope.leagueTable(
    tables: List<CompetitionStandingsDetails>,
    currentTable: Int,
    onTableTypeClicked: (Int) -> Unit,
) {
    item {
        SmallTextHeader(text = stringResource(R.string.comprtition_table_header))
    }

    item {
        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            contentPadding = PaddingValues(vertical = MaterialTheme.spacing.extraSmall)
        ) {
            itemsIndexed(items = tables) { index, table ->
                val isCurrentTable = currentTable == index
                SelectableButton(
                    text = if (table.group != Group.N_A) {
                        table.group.groupName
                    } else {
                        "${table.stage}-${table.type}"
                    },
                    isSelected = isCurrentTable,
                    onClick = { onTableTypeClicked(index) }
                )
            }
        }
    }

    item {
        CompetitionTable(
            modifier = Modifier.fillMaxWidth(),
            tableStandings = tables[currentTable].table
        )
    }
}
