package com.mateuszholik.uicomponents.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.CompetitionType
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.uicomponents.extensions.asFullDateTimeString
import com.mateuszholik.uicomponents.extensions.backgroundColorForCompetition
import com.mateuszholik.uicomponents.extensions.textColorForCompetition
import com.mateuszholik.uicomponents.score.DetailedScore
import com.mateuszholik.uicomponents.team.VerticalTeamInfo
import com.mateuszholik.uicomponents.utils.PreviewConstants

@Composable
fun MatchScoreHeader(
    competitionType: CompetitionType,
    matchInfo: MatchInfo,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = competitionType.backgroundColorForCompetition),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        VerticalTeamInfo(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = MaterialTheme.spacing.normal),
            textColor = competitionType.textColorForCompetition,
            team = matchInfo.homeTeam
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = matchInfo.utcDate.asFullDateTimeString(),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.textSizing.extraSmall
            )
            DetailedScore(
                modifier = Modifier.padding(top = MaterialTheme.spacing.extraSmall),
                matchScore = matchInfo.score,
                status = matchInfo.status
            )
        }
        VerticalTeamInfo(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = MaterialTheme.spacing.normal),
            textColor = competitionType.textColorForCompetition,
            team = matchInfo.awayTeam
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    FootballScoreTheme {
        MatchScoreHeader(
            competitionType = CompetitionType.SUPER_CUP,
            matchInfo = PreviewConstants.IN_PLAY_MATCH_INFO,
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun ScheduledMatchPreview() {
    FootballScoreTheme {
        MatchScoreHeader(
            competitionType = CompetitionType.LEAGUE,
            matchInfo = PreviewConstants.SCHEDULED_MATCH_INFO,
        )
    }
}
