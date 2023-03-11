package com.mateuszholik.uicomponents.match

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mateuszholik.model.Match
import com.mateuszholik.model.Status
import com.mateuszholik.designsystem.R
import com.mateuszholik.uicomponents.extensions.asHourString

@Composable
fun MatchItem(
    match: Match,
    modifier: Modifier = Modifier,
) {
    when (match.status) {
        Status.FINISHED -> FinishedMatch(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam,
            score = match.score.fullTime
        )
        Status.IN_PLAY -> InPlayMatch(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam,
            score = match.score.fullTime
        )
        Status.PAUSED -> HalfTimeMatch(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam,
            score = match.score.fullTime
        )
        Status.POSTPONED -> ScheduledMatch(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam,
            text = stringResource(R.string.match_postponed)
        )
        Status.SCHEDULED,
        Status.TIMED -> ScheduledMatch(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam,
            text = match.utcDate.asHourString()
        )
        Status.CANCELLED -> ScheduledMatch(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam,
            text = stringResource(R.string.match_cancelled)
        )
        Status.SUSPENDED -> ScheduledMatch(
            modifier = modifier,
            homeTeam = match.homeTeam,
            awayTeam = match.awayTeam,
            text = stringResource(R.string.match_suspended)
        )
    }
}
