package com.mateuszholik.uicomponents.head2head

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.uicomponents.images.Image
import com.mateuszholik.uicomponents.texts.TextWithBackground
import com.mateuszholik.uicomponents.texts.TextWithBackgroundColors
import com.mateuszholik.uicomponents.utils.PreviewConstants.TEAM_H2H_DATA

@Composable
fun TeamHead2HeadInfo(
    homeTeamCrest: String,
    awayTeamCrest: String,
    wins: Int,
    draws: Int,
    losses: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier.size(MaterialTheme.sizing.medium),
            url = homeTeamCrest,
            onErrorImageRes = R.drawable.ic_ball
        )

        TextWithBackground(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.normal)
                .size(MaterialTheme.sizing.medium),
            text = "$wins",
            textSize = MaterialTheme.textSizing.normal,
            textPadding = MaterialTheme.spacing.none,
            shape = RoundedCornerShape(MaterialTheme.cornerRadius.medium)
        )

        TextWithBackground(
            modifier = Modifier.size(MaterialTheme.sizing.medium),
            text = "$draws",
            textSize = MaterialTheme.textSizing.normal,
            textPadding = MaterialTheme.spacing.none,
            textWithBackgroundColors = TextWithBackgroundColors(
                backgroundColor = Color(0xFFDCEB78),
                textColor = Color(0xFF1A1E00)
            ),
            shape = RoundedCornerShape(MaterialTheme.cornerRadius.medium)
        )

        TextWithBackground(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.normal)
                .size(MaterialTheme.sizing.medium),
            text = "$losses",
            textSize = MaterialTheme.textSizing.normal,
            textPadding = MaterialTheme.spacing.none,
            shape = RoundedCornerShape(MaterialTheme.cornerRadius.medium)
        )

        Image(
            modifier = Modifier.size(MaterialTheme.sizing.medium),
            url = awayTeamCrest,
            onErrorImageRes = R.drawable.ic_ball
        )
    }
}

@Preview()
@Composable
private fun Preview() {
    FootballScoreTheme {
        Surface {
            TeamHead2HeadInfo(
                homeTeamCrest = "",
                awayTeamCrest = "",
                wins = TEAM_H2H_DATA.wins,
                draws = TEAM_H2H_DATA.draws,
                losses = TEAM_H2H_DATA.losses,
            )
        }
    }
}
