package com.mateuszholik.uicomponents.referee

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.model.Referee
import com.mateuszholik.designsystem.R
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.RefereeType
import com.mateuszholik.uicomponents.utils.PreviewConstants

@Composable
fun RefereeItem(
    referee: Referee,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            painter = painterResource(R.drawable.ic_referee),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface)
        )
        Text(
            text = "${referee.type.toText} - ${referee.name}",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.textSizing.small,
            fontWeight = FontWeight.Bold
        )
    }
}

private val RefereeType.toText: String
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        RefereeType.REFEREE -> stringResource(R.string.referee_main)
        RefereeType.ASSISTANT_REFEREE_N1,
        RefereeType.ASSISTANT_REFEREE_N2 -> stringResource(R.string.referee_assistant)
        RefereeType.FOURTH_OFFICIAL -> stringResource(R.string.referee_technical)
        RefereeType.VIDEO_ASSISTANT_REFEREE_N1,
        RefereeType.VIDEO_ASSISTANT_REFEREE_N2 -> stringResource(R.string.referee_var)
    }

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        RefereeItem(
            referee = PreviewConstants.REFEREE
        )
    }
}
