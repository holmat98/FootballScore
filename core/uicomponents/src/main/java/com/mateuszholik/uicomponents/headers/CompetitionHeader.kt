package com.mateuszholik.uicomponents.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.Competition
import com.mateuszholik.model.CompetitionType


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CompetitionHeader(
    competition: Competition,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(competition.type.backgroundColorForCompetition),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier
                .width(MaterialTheme.sizing.smallImage.width)
                .padding(MaterialTheme.spacing.tiny)
                .height(MaterialTheme.sizing.smallImage.height)
                .clip(RoundedCornerShape(MaterialTheme.cornerRadius.small))
                .background(MaterialTheme.colorScheme.surface),
            model = competition.emblem,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.tiny),
            text = competition.name,
            color = competition.type.textColorForCompetition,
            fontSize = MaterialTheme.textSizing.normal
        )
    }
}

private val CompetitionType.backgroundColorForCompetition: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        CompetitionType.CUP -> MaterialTheme.colorScheme.secondary
        CompetitionType.LEAGUE -> MaterialTheme.colorScheme.secondaryContainer
        CompetitionType.SUPER_CUP -> MaterialTheme.colorScheme.tertiary
        CompetitionType.PLAYOFFS -> MaterialTheme.colorScheme.tertiaryContainer
    }

private val CompetitionType.textColorForCompetition: Color
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        CompetitionType.CUP -> MaterialTheme.colorScheme.onSecondary
        CompetitionType.LEAGUE -> MaterialTheme.colorScheme.onSecondaryContainer
        CompetitionType.SUPER_CUP -> MaterialTheme.colorScheme.onTertiary
        CompetitionType.PLAYOFFS -> MaterialTheme.colorScheme.onTertiaryContainer
    }

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        Column {
            CompetitionHeader(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                competition = Competition(
                    id = 2021,
                    code = "PL",
                    name = "Premier League",
                    emblem = "",
                    type = CompetitionType.LEAGUE,
                    countryName = "England",
                    countryCode = "ENG",
                    countryFlag = ""
                )
            )
            CompetitionHeader(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                competition = Competition(
                    id = 2021,
                    code = "PL",
                    name = "Premier League",
                    emblem = "",
                    type = CompetitionType.CUP,
                    countryName = "England",
                    countryCode = "ENG",
                    countryFlag = ""
                )
            )
            CompetitionHeader(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                competition = Competition(
                    id = 2021,
                    code = "PL",
                    name = "Premier League",
                    emblem = "",
                    type = CompetitionType.PLAYOFFS,
                    countryName = "England",
                    countryCode = "ENG",
                    countryFlag = ""
                )
            )
            CompetitionHeader(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                competition = Competition(
                    id = 2021,
                    code = "PL",
                    name = "Premier League",
                    emblem = "",
                    type = CompetitionType.SUPER_CUP,
                    countryName = "England",
                    countryCode = "ENG",
                    countryFlag = ""
                )
            )
        }
    }
}
