package com.mateuszholik.uicomponents.head2head

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.model.TeamH2HData
import com.mateuszholik.uicomponents.cards.RoundedTextCard
import com.mateuszholik.uicomponents.images.RoundedImage
import com.mateuszholik.uicomponents.utils.PreviewConstants.TEAM_H2H_DATA

@Composable
fun TeamHead2HeadInfo(
    teamCrest: String,
    head2HeadTeam: TeamH2HData,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RoundedImage(
            imageUrl = teamCrest,
            onErrorImageRes = R.drawable.ic_ball,
            padding = MaterialTheme.spacing.small,
            cornerRadius = MaterialTheme.cornerRadius.normal
        )
        Row {
            RoundedTextCard(
                text = "${head2HeadTeam.wins}",
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
            RoundedTextCard(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
                text = "${head2HeadTeam.draws}",
                backgroundColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
            )
            RoundedTextCard(
                modifier = Modifier.padding(end = MaterialTheme.spacing.medium),
                text = "${head2HeadTeam.losses}",
                backgroundColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    FootballScoreTheme {
        TeamHead2HeadInfo(
            teamCrest = "",
            head2HeadTeam = TEAM_H2H_DATA
        )
    }
}
