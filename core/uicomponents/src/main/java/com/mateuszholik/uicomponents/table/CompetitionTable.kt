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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.TablePosition
import com.mateuszholik.model.TeamForm
import com.mateuszholik.uicomponents.team.HorizontalTeamItem
import com.mateuszholik.uicomponents.texts.SmallText
import com.mateuszholik.uicomponents.texts.TextWithBackground
import com.mateuszholik.uicomponents.texts.TextWithBackgroundColors
import com.mateuszholik.uicomponents.utils.PreviewConstants.TABLE

private val COLUMN_ITEM_HEIGHT = 45.dp
private val TEXT_WITH_BACKGROUND_HEIGHT = 37.5.dp

@Composable
fun CompetitionTable(
    tableStandings: List<TablePosition>,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    LazyRow(
        modifier = modifier.background(color = backgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        item { PositionsColumn(tableStandings = tableStandings) }

        item {
            TeamsColumn(
                tableStandings = tableStandings,
                contentColor = contentColor
            )
        }
        item {
            TextColumn(
                columnHeaderText = stringResource(R.string.competition_table_column_header_points),
                tableStandings = tableStandings,
                contentColor = contentColor,
                dataModifier = { it.points }
            )
        }

        item {
            TextColumn(
                columnHeaderText = stringResource(R.string.competition_table_column_header_games_played),
                tableStandings = tableStandings,
                contentColor = contentColor,
                dataModifier = { it.playedGames }
            )
        }

        item {
            TextColumn(
                columnHeaderText = stringResource(R.string.competition_table_column_header_games_won),
                tableStandings = tableStandings,
                contentColor = contentColor,
                dataModifier = { it.won }
            )
        }

        item {
            TextColumn(
                columnHeaderText = stringResource(R.string.competition_table_column_header_games_drown),
                tableStandings = tableStandings,
                contentColor = contentColor,
                dataModifier = { it.draw }
            )
        }

        item {
            TextColumn(
                columnHeaderText = stringResource(R.string.competition_table_column_header_games_lost),
                tableStandings = tableStandings,
                contentColor = contentColor,
                dataModifier = { it.lost }
            )
        }

        item {
            TextColumn(
                columnHeaderText = stringResource(R.string.competition_table_column_header_goals_difference),
                tableStandings = tableStandings,
                contentColor = contentColor,
                dataModifier = { "${it.goalsScored}:${it.goalsConceded}" }
            )
        }

        item { TeamFormColumn(tableStandings = tableStandings) }
    }
}

@Composable
private fun PositionsColumn(tableStandings: List<TablePosition>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ColumnHeaderText(text = "#")

        tableStandings.forEachIndexed { index, tableStanding ->
            val isLast = tableStandings.lastIndex == index
            Box(
                modifier = Modifier.height(COLUMN_ITEM_HEIGHT),
                contentAlignment = Alignment.Center
            ) {
                TextWithBackground(
                    modifier = Modifier
                        .size(TEXT_WITH_BACKGROUND_HEIGHT)
                        .padding(MaterialTheme.spacing.extraSmall),
                    textSize = MaterialTheme.textSizing.small,
                    text = "${tableStanding.position}.",
                    textWithBackgroundColors = TextWithBackgroundColors(
                        backgroundColor = tableStanding.position.toBackgroundColor(isLast),
                        textColor = tableStanding.position.toContentColor(isLast)
                    )
                )
            }
        }
    }
}

@Composable
private fun TeamsColumn(
    tableStandings: List<TablePosition>,
    contentColor: Color,
) {
    Column {
        ColumnHeaderText(text = stringResource(R.string.competition_table_column_header_team))

        tableStandings.forEach {
            Row(
                modifier = Modifier.height(COLUMN_ITEM_HEIGHT),
                verticalAlignment = CenterVertically
            ) {
                HorizontalTeamItem(
                    team = it.team,
                    imageSize = MaterialTheme.sizing.small,
                    textColor = contentColor,
                    paddingValues = PaddingValues(MaterialTheme.spacing.extraSmall)
                )
            }
        }
    }
}

@Composable
private fun <T> TextColumn(
    columnHeaderText: String,
    tableStandings: List<TablePosition>,
    contentColor: Color,
    dataModifier: (TablePosition) -> T,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColumnHeaderText(text = columnHeaderText)

        tableStandings.forEach {
            Box(
                modifier = Modifier.height(COLUMN_ITEM_HEIGHT),
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

@Composable
private fun TeamFormColumn(tableStandings: List<TablePosition>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ColumnHeaderText(text = stringResource(R.string.competition_table_column_header_form))

        tableStandings.forEach { standing ->
            Row(
                modifier = Modifier.height(COLUMN_ITEM_HEIGHT),
                verticalAlignment = CenterVertically
            ) {
                standing.form.forEach {
                    TextWithBackground(
                        modifier = Modifier
                            .size(TEXT_WITH_BACKGROUND_HEIGHT)
                            .padding(MaterialTheme.spacing.extraSmall),
                        textSize = MaterialTheme.textSizing.small,
                        text = it.type,
                        textWithBackgroundColors = TextWithBackgroundColors(
                            backgroundColor = it.toBackgroundColor,
                            textColor = it.toContentColor
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnHeaderText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontSize = MaterialTheme.textSizing.small,
        fontWeight = FontWeight.Bold
    )
}

private val TeamForm.toBackgroundColor: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        TeamForm.WIN -> MaterialTheme.colorScheme.primary
        TeamForm.DRAW -> Color(0xFFDCEB78)
        TeamForm.LOSE -> MaterialTheme.colorScheme.error
        TeamForm.N_A -> MaterialTheme.colorScheme.surfaceVariant
    }

private val TeamForm.toContentColor: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        TeamForm.WIN -> MaterialTheme.colorScheme.onPrimary
        TeamForm.DRAW -> Color(0xFF1A1E00)
        TeamForm.LOSE -> MaterialTheme.colorScheme.onError
        TeamForm.N_A -> MaterialTheme.colorScheme.onSurfaceVariant
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
            tableStandings = TABLE
        )
    }
}
