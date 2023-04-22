package com.mateuszholik.uicomponents.seasonwinner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.SeasonWinner
import com.mateuszholik.uicomponents.images.Image
import com.mateuszholik.uicomponents.utils.PreviewConstants.SEASON_WINNER
import java.time.LocalDate

@Composable
fun SeasonWinnerItem(
    seasonStartDate: LocalDate,
    seasonEndDate: LocalDate,
    winner: SeasonWinner?,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(start = MaterialTheme.spacing.small),
            text = "${seasonStartDate.year}/${seasonEndDate.year}",
            color = contentColor,
            fontSize = MaterialTheme.textSizing.small,
            fontWeight = FontWeight.Bold
        )
        winner?.let {
            Image(
                modifier = Modifier
                    .size(MaterialTheme.sizing.medium)
                    .padding(MaterialTheme.spacing.extraSmall),
                url = it.crest,
                onErrorImageRes = R.drawable.ic_ball
            )
            Text(
                text = it.name.uppercase(),
                color = contentColor,
                fontSize = MaterialTheme.textSizing.small
            )
        } ?: run {
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.small),
                text = stringResource(R.string.competition_winner_not_known).uppercase(),
                color = contentColor,
                fontSize = MaterialTheme.textSizing.small,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        SeasonWinnerItem(
            seasonStartDate = LocalDate.of(2022, 8, 1),
            seasonEndDate = LocalDate.of(2023, 5, 31),
            winner = SEASON_WINNER,
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)
        )
    }
}

@Preview
@Composable
private fun PreviewWinnerNotKnown() {
    FootballScoreTheme {
        SeasonWinnerItem(
            seasonStartDate = LocalDate.of(2022, 8, 1),
            seasonEndDate = LocalDate.of(2023, 5, 31),
            winner = null,
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)
        )
    }
}
