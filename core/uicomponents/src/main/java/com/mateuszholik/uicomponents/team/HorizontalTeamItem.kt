package com.mateuszholik.uicomponents.team

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.Team
import com.mateuszholik.uicomponents.images.Image
import com.mateuszholik.uicomponents.utils.PreviewConstants

@Composable
internal fun HorizontalTeamItem(
    team: Team,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    paddingValues: PaddingValues = PaddingValues(
        top = MaterialTheme.spacing.tiny,
        bottom = MaterialTheme.spacing.tiny,
        start = MaterialTheme.spacing.small
    ),
) {
    Row(
        modifier = modifier.padding(paddingValues),
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
            color = textColor,
            fontSize = MaterialTheme.textSizing.small,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            HorizontalTeamItem(
                team = PreviewConstants.TEAM_1
            )
        }
    }
}
