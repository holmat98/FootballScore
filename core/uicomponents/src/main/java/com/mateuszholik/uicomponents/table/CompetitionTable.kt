package com.mateuszholik.uicomponents.table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.TablePosition
import com.mateuszholik.model.TeamForm
import com.mateuszholik.uicomponents.team.HorizontalTeamItem
import com.mateuszholik.uicomponents.texts.SmallText
import com.mateuszholik.uicomponents.texts.TextWithBackground
import com.mateuszholik.uicomponents.utils.PreviewConstants.TABLE_POSITION

@Composable
fun CompetitionTable(
    tableStandings: List<TablePosition>,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    var columnItemHeight by remember { mutableStateOf(88) }

    LazyRow(
        modifier = modifier.background(color = backgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        positionsColumn(
            tableStandings = tableStandings,
            columnItemHeight = { columnItemHeight }
        )
        teamsColumn(
            tableStandings = tableStandings,
            contentColor = contentColor,
            onColumnItemHeightChanged = {
                if (it > columnItemHeight) {
                    columnItemHeight = it
                }
            }
        )
        textColumn(
            columnItemHeight = columnItemHeight,
            columnHeaderText = "Pts",
            tableStandings = tableStandings,
            contentColor = contentColor,
            dataModifier = { it.points }
        )
        textColumn(
            columnItemHeight = columnItemHeight,
            columnHeaderText = "GP",
            tableStandings = tableStandings,
            contentColor = contentColor,
            dataModifier = { it.playedGames }
        )
        textColumn(
            columnItemHeight = columnItemHeight,
            columnHeaderText = "W",
            tableStandings = tableStandings,
            contentColor = contentColor,
            dataModifier = { it.won }
        )
        textColumn(
            columnItemHeight = columnItemHeight,
            columnHeaderText = "D",
            tableStandings = tableStandings,
            contentColor = contentColor,
            dataModifier = { it.draw }
        )
        textColumn(
            columnItemHeight = columnItemHeight,
            columnHeaderText = "L",
            tableStandings = tableStandings,
            contentColor = contentColor,
            dataModifier = { it.lost }
        )
        textColumn(
            columnItemHeight = columnItemHeight,
            columnHeaderText = "G",
            tableStandings = tableStandings,
            contentColor = contentColor,
            dataModifier = { "${it.goalsScored}:${it.goalsConceded}" }
        )
        teamFormColumn(
            columnItemHeight = columnItemHeight,
            tableStandings = tableStandings,
        )
    }
}

private fun LazyListScope.positionsColumn(
    tableStandings: List<TablePosition>,
    columnItemHeight: () -> Int,
): Unit = item {
    val currentColumnHeight by rememberUpdatedState(columnItemHeight)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "#",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = MaterialTheme.textSizing.extraSmall
        )

        tableStandings.forEachIndexed { index, tableStanding ->
            val isLast = tableStandings.lastIndex == index
            Box(
                modifier = Modifier.height(currentColumnHeight().toDp()),
                contentAlignment = Alignment.Center
            ) {
                TextWithBackground(
                    modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
                    text = "${tableStanding.position}.",
                    backgroundColor = tableStanding.position.toBackgroundColor(isLast),
                    textColor = tableStanding.position.toContentColor(isLast)
                )
            }
        }
    }
}

private fun LazyListScope.teamsColumn(
    tableStandings: List<TablePosition>,
    contentColor: Color,
    onColumnItemHeightChanged: (Int) -> Unit,
): Unit = item {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.competition_table_team_column_header),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = MaterialTheme.textSizing.extraSmall
        )

        tableStandings.forEach {
            HorizontalTeamItem(
                modifier = Modifier.onSizeChanged { size ->
                    onColumnItemHeightChanged(size.height)
                },
                team = it.team,
                textColor = contentColor,
                paddingValues = PaddingValues(MaterialTheme.spacing.extraSmall)
            )
        }
    }
}

private fun <T> LazyListScope.textColumn(
    columnItemHeight: Int,
    columnHeaderText: String,
    tableStandings: List<TablePosition>,
    contentColor: Color,
    dataModifier: (TablePosition) -> T,
): Unit = item {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = columnHeaderText,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = MaterialTheme.textSizing.extraSmall
        )

        tableStandings.forEach {
            Box(
                modifier = Modifier.height(columnItemHeight.toDp()),
                contentAlignment = Alignment.Center
            ) {
                SmallText(
                    text = "${dataModifier(it)}",
                    color = contentColor,
                    padding = MaterialTheme.spacing.extraSmall
                )
            }
        }
    }
}

private fun LazyListScope.teamFormColumn(
    columnItemHeight: Int,
    tableStandings: List<TablePosition>,
): Unit = item {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.competition_table_form_column_header),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = MaterialTheme.textSizing.extraSmall
        )
        tableStandings.forEach { standing ->
            Row(
                modifier = Modifier.height(
                    columnItemHeight.toDp()
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                standing.form.forEach {
                    TextWithBackground(
                        modifier = Modifier.padding(end = MaterialTheme.spacing.extraSmall),
                        text = it.type,
                        backgroundColor = it.toBackgroundColor,
                        textColor = it.toContentColor
                    )
                }
            }
        }
    }
}

@Composable
private fun Int.toDp(): Dp = with(LocalDensity.current) { this@toDp.toDp() }

private val TeamForm.toBackgroundColor: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        TeamForm.WIN -> MaterialTheme.colorScheme.primary
        TeamForm.DRAW -> Color(0xFFDCEB78)
        TeamForm.LOSE -> MaterialTheme.colorScheme.error
        TeamForm.N_A -> MaterialTheme.colorScheme.primary
    }

private val TeamForm.toContentColor: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        TeamForm.WIN -> MaterialTheme.colorScheme.onPrimary
        TeamForm.DRAW -> Color(0xFF1A1E00)
        TeamForm.LOSE -> MaterialTheme.colorScheme.onError
        TeamForm.N_A -> MaterialTheme.colorScheme.onPrimary
    }

@Composable
@ReadOnlyComposable
private fun Int.toBackgroundColor(isLast: Boolean): Color =
    when {
        isLast -> MaterialTheme.colorScheme.error
        this == 1 -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

@Composable
@ReadOnlyComposable
private fun Int.toContentColor(isLast: Boolean): Color =
    when {
        isLast -> MaterialTheme.colorScheme.onPrimary
        this == 1 -> MaterialTheme.colorScheme.onError
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    FootballScoreTheme {
        CompetitionTable(
            modifier = Modifier.fillMaxWidth(),
            tableStandings = listOf(
                TABLE_POSITION,
                TABLE_POSITION.copy(position = 2, points = 64, won = 19, lost = 4),
                TABLE_POSITION.copy(position = 3, points = 59, won = 17, draw = 8, lost = 5),
                TABLE_POSITION.copy(position = 4, points = 40, won = 10, draw = 10, lost = 10)
            )
        )
    }
}
