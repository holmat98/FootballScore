package com.mateuszholik.uicomponents.head2head

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.model.TeamH2HData
import com.mateuszholik.uicomponents.cards.RoundedColumnTextCard
import com.mateuszholik.uicomponents.images.RoundedImage
import com.mateuszholik.uicomponents.utils.PreviewConstants.TEAM_H2H_DATA
import com.mateuszholik.uicomponents.utils.PreviewConstants.TEAM_H2H_DATA_2

@Composable
fun TeamHead2HeadInfo(
    homeTeamCrest: String,
    awayTeamCrest: String,
    homeTeamH2hData: TeamH2HData,
    awayTeamH2HData: TeamH2HData,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,) {
            RoundedImage(
                imageUrl = homeTeamCrest,
                onErrorImageRes = R.drawable.ic_ball,
                padding = MaterialTheme.spacing.extraSmall,
                cornerRadius = MaterialTheme.cornerRadius.normal
            )
            RoundedImage(
                imageUrl = awayTeamCrest,
                onErrorImageRes = R.drawable.ic_ball,
                padding = MaterialTheme.spacing.extraSmall,
                cornerRadius = MaterialTheme.cornerRadius.normal
            )
        }
        RoundedColumnTextCard(
            topText = "${homeTeamH2hData.wins}",
            bottomText = "${awayTeamH2HData.wins}"
        )
        RoundedColumnTextCard(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            topText = "${homeTeamH2hData.draws}",
            bottomText = "${awayTeamH2HData.draws}",
            backgroundColor = Color(0xFFDCEB78),
            contentColor = Color(0xFF1A1E00),
        )
        RoundedColumnTextCard(
            topText = "${homeTeamH2hData.losses}",
            bottomText = "${awayTeamH2HData.losses}",
            backgroundColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError,
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    FootballScoreTheme {
        TeamHead2HeadInfo(
            homeTeamCrest = "",
            awayTeamCrest = "",
            homeTeamH2hData = TEAM_H2H_DATA,
            awayTeamH2HData = TEAM_H2H_DATA_2
        )
    }
}
