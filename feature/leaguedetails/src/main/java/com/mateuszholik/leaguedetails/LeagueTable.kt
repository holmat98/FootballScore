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
import com.mateuszholik.model.CompetitionTableType
import com.mateuszholik.model.Group
import com.mateuszholik.model.Stage
import com.mateuszholik.uicomponents.buttons.SelectableButton
import com.mateuszholik.uicomponents.headers.SmallTextHeader
import com.mateuszholik.uicomponents.table.CompetitionTable

internal fun LazyListScope.leagueTable(
    tables: List<CompetitionStandingsDetails>,
    currentTable: Int,
    onTableTypeClicked: (Int) -> Unit,
) {
    item {
        SmallTextHeader(text = stringResource(R.string.competition_table_header))
    }

    item {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = MaterialTheme.spacing.extraSmall)
        ) {
            itemsIndexed(items = tables) { index, table ->
                val isCurrentTable = currentTable == index
                SelectableButton(
                    text = if (table.group != Group.N_A) {
                        stringResource(R.string.competition_table_group,table.group.toText)
                    } else {
                        "${stringResource(table.stage.toStringResId)} - ${stringResource(table.type.toStringResId)}"
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

private val Group.toText: String
    get() = when (this) {
        Group.N_A -> "N/A"
        Group.GROUP_A -> "A"
        Group.GROUP_B -> "B"
        Group.GROUP_C -> "C"
        Group.GROUP_D -> "D"
        Group.GROUP_E -> "E"
        Group.GROUP_F -> "F"
        Group.GROUP_G -> "G"
        Group.GROUP_H -> "H"
    }

private val Stage.toStringResId: Int
    get() = when (this) {
        Stage.REGULAR_SEASON -> R.string.competition_table_stage_regular_season
        Stage.APERTURA -> R.string.competition_table_stage_apertura
        Stage.CHAMPIONSHIP -> R.string.competition_table_stage_championship
        Stage.CLAUSURA -> R.string.competition_table_stage_clausura
        Stage.GROUP_STAGE -> R.string.competition_table_stage_group
        Stage.FINAL -> R.string.competition_table_stage_final
        Stage.LAST_16 -> R.string.competition_table_stage_last_16
        Stage.LAST_32 -> R.string.competition_table_stage_last_32
        Stage.LAST_64 -> R.string.competition_table_stage_last_64
        Stage.PLAYOFFS -> R.string.competition_table_stage_playoffs
        Stage.PLAYOFF_ROUND_1 -> R.string.competition_table_stage_playoff_round_1
        Stage.PLAYOFF_ROUND_2 -> R.string.competition_table_stage_playoff_round_2
        Stage.PRELIMINARY_ROUND -> R.string.competition_table_stage_preliminary
        Stage.QUALIFICATION -> R.string.competition_table_stage_qualification
        Stage.QUALIFICATION_ROUND_1 -> R.string.competition_table_stage_qualification_round_1
        Stage.QUALIFICATION_ROUND_2 -> R.string.competition_table_stage_qualification_round_2
        Stage.QUALIFICATION_ROUND_3 -> R.string.competition_table_stage_qualification_round_3
        Stage.QUARTER_FINALS -> R.string.competition_table_stage_quarter_finals
        Stage.RELEGATION -> R.string.competition_table_stage_relegation
        Stage.RELEGATION_ROUND -> R.string.competition_table_stage_relegation_round
        Stage.ROUND_1 -> R.string.competition_table_stage_round_1
        Stage.ROUND_2 -> R.string.competition_table_stage_round_2
        Stage.ROUND_3 -> R.string.competition_table_stage_round_3
        Stage.ROUND_4 -> R.string.competition_table_stage_round_4
        Stage.SEMI_FINALS -> R.string.competition_table_stage_semi_finals
        Stage.THIRD_PLACE -> R.string.competition_table_stage_third_place
    }

private val CompetitionTableType.toStringResId: Int
    get() = when (this) {
        CompetitionTableType.TOTAL -> R.string.competition_table_type_total
        CompetitionTableType.HOME -> R.string.competition_table_type_home
        CompetitionTableType.AWAY -> R.string.competition_table_type_away
    }
