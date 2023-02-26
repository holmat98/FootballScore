package com.mateuszholik.uicomponents.match

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mateuszholik.model.MatchInfo
import com.mateuszholik.model.Status
import com.mateuszholik.designsystem.R
import com.mateuszholik.uicomponents.extensions.asHourString

@Composable
fun MatchItem(
    matchInfo: MatchInfo,
    modifier: Modifier = Modifier,
) {
    when (matchInfo.status) {
        Status.FINISHED -> FinishedMatch(
            modifier = modifier,
            homeTeam = matchInfo.homeTeam,
            awayTeam = matchInfo.awayTeam,
            score = matchInfo.score.fullTime
        )
        Status.IN_PLAY -> InPlayMatch(
            modifier = modifier,
            homeTeam = matchInfo.homeTeam,
            awayTeam = matchInfo.awayTeam,
            score = matchInfo.score.fullTime
        )
        Status.PAUSED -> HalfTimeMatch(
            modifier = modifier,
            homeTeam = matchInfo.homeTeam,
            awayTeam = matchInfo.awayTeam,
            score = matchInfo.score.fullTime
        )
        Status.POSTPONED -> ScheduledMatch(
            modifier = modifier,
            homeTeam = matchInfo.homeTeam,
            awayTeam = matchInfo.awayTeam,
            text = stringResource(R.string.match_postponed)
        )
        Status.SCHEDULED,
        Status.TIMED -> ScheduledMatch(
            modifier = modifier,
            homeTeam = matchInfo.homeTeam,
            awayTeam = matchInfo.awayTeam,
            text = matchInfo.utcDate.asHourString()
        )
        Status.CANCELLED -> ScheduledMatch(
            modifier = modifier,
            homeTeam = matchInfo.homeTeam,
            awayTeam = matchInfo.awayTeam,
            text = stringResource(R.string.match_cancelled)
        )
        Status.SUSPENDED -> ScheduledMatch(
            modifier = modifier,
            homeTeam = matchInfo.homeTeam,
            awayTeam = matchInfo.awayTeam,
            text = stringResource(R.string.match_suspended)
        )
    }
}
