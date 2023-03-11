package com.mateuszholik.uicomponents.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.model.Competition
import com.mateuszholik.model.CompetitionType
import com.mateuszholik.designsystem.R
import com.mateuszholik.uicomponents.extensions.backgroundColorForCompetition
import com.mateuszholik.uicomponents.extensions.textColorForCompetition
import com.mateuszholik.uicomponents.images.RoundedImage


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
        RoundedImage(
            imageUrl = competition.emblem,
            cornerRadius = MaterialTheme.cornerRadius.medium,
            padding = MaterialTheme.spacing.small,
            size = MaterialTheme.sizing.normal,
            onErrorImageRes = R.drawable.ic_ball
        )
        Column {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.tiny),
                text = competition.name.uppercase(),
                color = competition.type.textColorForCompetition,
                fontSize = MaterialTheme.textSizing.small,
                fontWeight = FontWeight.Bold
            )
            Row {
                RoundedImage(
                    imageUrl = competition.countryFlag,
                    padding = MaterialTheme.spacing.tiny,
                    innerPadding = MaterialTheme.spacing.none,
                    cornerRadius = MaterialTheme.cornerRadius.none,
                    size = MaterialTheme.sizing.extraSmall,
                    backgroundColor = Color.Transparent,
                    onErrorImageRes = R.drawable.ic_ball
                )
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.tiny),
                    text = competition.countryName.uppercase(),
                    color = competition.type.textColorForCompetition,
                    fontSize = MaterialTheme.textSizing.extraSmall,
                )
            }
        }
    }
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
