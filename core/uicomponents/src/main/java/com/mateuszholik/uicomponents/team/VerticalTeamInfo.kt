package com.mateuszholik.uicomponents.team

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.mateuszholik.model.Team
import com.mateuszholik.uicomponents.images.RoundedImage
import com.mateuszholik.uicomponents.utils.PreviewConstants

@Composable
internal fun VerticalTeamInfo(
    modifier: Modifier,
    textColor: Color,
    team: Team,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundedImage(
            imageUrl = team.crest,
            onErrorImageRes = R.drawable.ic_ball,
            size = MaterialTheme.sizing.medium,
            padding = MaterialTheme.spacing.none,
            innerPadding = MaterialTheme.spacing.extraSmall,
            cornerRadius = MaterialTheme.cornerRadius.medium
        )
        Text(
            text = team.name,
            color = textColor,
            fontSize = MaterialTheme.textSizing.normal
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        VerticalTeamInfo(
            modifier = Modifier,
            textColor = MaterialTheme.colorScheme.onSurface,
            team = PreviewConstants.TEAM_1
        )
    }
}
