package com.mateuszholik.uicomponents.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.model.CompetitionType
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.uicomponents.extensions.backgroundColorForCompetition
import com.mateuszholik.uicomponents.images.RoundedImageWithBackground
import com.mateuszholik.uicomponents.score.DetailedScore
import com.mateuszholik.uicomponents.utils.PreviewConstants

@Composable
fun SmallMatchScoreHeader(
    competitionType: CompetitionType,
    matchInfo: MatchInfo,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = competitionType.backgroundColorForCompetition),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        RoundedImageWithBackground(
            modifier = Modifier
                .size(MaterialTheme.sizing.medium)
                .padding(MaterialTheme.spacing.none),
            imageUrl = matchInfo.homeTeam.crest,
            innerPadding = MaterialTheme.spacing.extraSmall,
            cornerRadius = MaterialTheme.cornerRadius.normal,
            onErrorImageRes = R.drawable.ic_ball
        )
        DetailedScore(
            modifier = Modifier.padding(
                vertical = MaterialTheme.spacing.small,
                horizontal = MaterialTheme.spacing.medium
            ),
            matchScore = matchInfo.score,
            status = matchInfo.status,
            shouldShowDuration = false
        )
        RoundedImageWithBackground(
            modifier = Modifier
                .size(MaterialTheme.sizing.medium)
                .padding(MaterialTheme.spacing.none),
            imageUrl = matchInfo.awayTeam.crest,
            innerPadding = MaterialTheme.spacing.extraSmall,
            cornerRadius = MaterialTheme.cornerRadius.normal,
            onErrorImageRes = R.drawable.ic_ball
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    FootballScoreTheme {
        SmallMatchScoreHeader(
            competitionType = CompetitionType.SUPER_CUP,
            matchInfo = PreviewConstants.IN_PLAY_MATCH_INFO,
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun ScheduledMatchPreview() {
    FootballScoreTheme {
        SmallMatchScoreHeader(
            competitionType = CompetitionType.LEAGUE,
            matchInfo = PreviewConstants.SCHEDULED_MATCH_INFO,
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun FinishedMatchPreview() {
    FootballScoreTheme {
        SmallMatchScoreHeader(
            competitionType = CompetitionType.SUPER_CUP,
            matchInfo = PreviewConstants.FINISHED_MATCH_INFO,
        )
    }
}
