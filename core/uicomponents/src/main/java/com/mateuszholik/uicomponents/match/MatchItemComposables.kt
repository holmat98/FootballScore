package com.mateuszholik.uicomponents.match

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.Score
import com.mateuszholik.model.Team
import com.mateuszholik.designsystem.R
import com.mateuszholik.uicomponents.images.Image

@Composable
internal fun Teams(
    modifier: Modifier,
    homeTeam: Team,
    awayTeam: Team,
) {
    Column(modifier = modifier) {
        TeamItem(team = homeTeam)
        TeamItem(team = awayTeam)
    }
}

@Composable
internal fun TeamItem(team: Team) {
    Row(
        modifier = Modifier.padding(
            top = MaterialTheme.spacing.tiny,
            bottom = MaterialTheme.spacing.tiny,
            start = MaterialTheme.spacing.small
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(MaterialTheme.sizing.small)
                .padding(MaterialTheme.spacing.extraSmall),
            url = team.crest,
            onErrorImageRes = R.drawable.ic_ball
        )
        Text(
            text = team.name.uppercase(),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.textSizing.small,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
internal fun Score(
    modifier: Modifier,
    score: Score,
    scoreColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(
                vertical = MaterialTheme.spacing.tiny,
                horizontal = MaterialTheme.spacing.extraSmall,
            ),
            text = "${score.home}",
            fontSize = MaterialTheme.textSizing.small,
            fontWeight = FontWeight.Bold,
            color = scoreColor
        )
        Text(
            modifier = Modifier.padding(
                vertical = MaterialTheme.spacing.tiny,
                horizontal = MaterialTheme.spacing.extraSmall,
            ),
            text = "${score.away}",
            fontSize = MaterialTheme.textSizing.small,
            fontWeight = FontWeight.Bold,
            color = scoreColor
        )
    }
}
