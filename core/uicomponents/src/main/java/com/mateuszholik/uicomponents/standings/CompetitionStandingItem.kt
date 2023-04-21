package com.mateuszholik.uicomponents.standings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.model.TablePosition
import com.mateuszholik.model.TeamForm
import com.mateuszholik.uicomponents.images.RoundedImage
import com.mateuszholik.uicomponents.texts.SmallText
import com.mateuszholik.uicomponents.texts.TextWithBackground
import com.mateuszholik.uicomponents.utils.PreviewConstants.TABLE_POSITION

@Composable
fun CompetitionStandingItem(
    tablePosition: TablePosition,
    backgroundColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.background(color = backgroundColor),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            TextWithBackground(
                text = "${tablePosition.position}.",
                backgroundColor = tablePosition.position.toBackgroundColor,
                textColor = tablePosition.position.toContentColor
            )
        }

        item {
            RoundedImage(
                imageUrl = tablePosition.team.crest,
                cornerRadius = MaterialTheme.cornerRadius.medium,
                size = MaterialTheme.sizing.normal,
                onErrorImageRes = R.drawable.ic_ball,
                padding = MaterialTheme.spacing.extraSmall
            )
        }

        item {
            SmallText(
                text = tablePosition.team.name,
                color = contentColor,
                padding = MaterialTheme.cornerRadius.none
            )
        }

        item {
            SmallText(
                text = "${tablePosition.points}",
                color = contentColor,
                padding = MaterialTheme.cornerRadius.none
            )
        }

        item {
            SmallText(
                text = "${tablePosition.playedGames}",
                color = contentColor,
                padding = MaterialTheme.cornerRadius.none
            )
        }

        item {
            SmallText(
                text = "${tablePosition.won}",
                color = contentColor,
                padding = MaterialTheme.cornerRadius.none
            )
        }

        item {
            SmallText(
                text = "${tablePosition.draw}",
                color = contentColor,
                padding = MaterialTheme.cornerRadius.none
            )
        }

        item {
            SmallText(
                text = "${tablePosition.lost}",
                color = contentColor,
                padding = MaterialTheme.cornerRadius.none
            )
        }

        item {
            SmallText(
                text = "${tablePosition.goalsScored}",
                color = contentColor,
                padding = MaterialTheme.cornerRadius.none
            )
        }

        item {
            SmallText(
                text = "${tablePosition.goalsConceded}",
                color = contentColor,
                padding = MaterialTheme.cornerRadius.none
            )
        }

        item {
            SmallText(
                text = "${tablePosition.goalsDifference}",
                color = contentColor,
                padding = MaterialTheme.cornerRadius.none
            )
        }

        items(items = tablePosition.form) {
            when (it) {
                TeamForm.WIN -> TextWithBackground(
                    text = TeamForm.WIN.type,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.onPrimary
                )
                TeamForm.DRAW -> TextWithBackground(
                    text = TeamForm.DRAW.type,
                    backgroundColor = Color(0xFFDCEB78),
                    textColor = Color(0xFF1A1E00)
                )
                TeamForm.LOSE -> TextWithBackground(
                    text = TeamForm.LOSE.type,
                    backgroundColor = MaterialTheme.colorScheme.error,
                    textColor = MaterialTheme.colorScheme.onError
                )
                TeamForm.N_A -> Unit
            }
        }
    }
}

private val Int.toBackgroundColor: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        in 1..3 -> MaterialTheme.colorScheme.primary
        in 18..22 -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

private val Int.toContentColor: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        in 1..3 -> MaterialTheme.colorScheme.onPrimary
        in 18..22 -> MaterialTheme.colorScheme.onError
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        LazyColumn {
            item {
                CompetitionStandingItem(
                    modifier = Modifier.fillMaxWidth(),
                    tablePosition = TABLE_POSITION,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            }

            item {
                CompetitionStandingItem(
                    modifier = Modifier.fillMaxWidth(),
                    tablePosition = TABLE_POSITION.copy(position = 2),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            }

            item {
                CompetitionStandingItem(
                    modifier = Modifier.fillMaxWidth(),
                    tablePosition = TABLE_POSITION.copy(position = 3),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            }

            item {
                CompetitionStandingItem(
                    modifier = Modifier.fillMaxWidth(),
                    tablePosition = TABLE_POSITION.copy(position = 4),
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
