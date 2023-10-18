package com.mateuszholik.uicomponents.headers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.mateuszholik.uicomponents.images.RoundedImageWithBackground

@Composable
fun CompetitionHeader(
    competition: Competition,
    modifier: Modifier = Modifier,
) {
    with(competition) {
        CompetitionHeader(
            competitionType = type,
            emblem = emblem,
            name = name,
            countryFlag = countryFlag,
            countryName = countryName,
            modifier = modifier
        )
    }
}

@Composable
fun CompetitionHeader(
    competitionType: CompetitionType,
    emblem: String,
    name: String,
    countryFlag: String,
    countryName: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(competitionType.backgroundColorForCompetition),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundedImageWithBackground(
            modifier = Modifier
                .size(MaterialTheme.sizing.normal)
                .padding(MaterialTheme.spacing.small),
            imageUrl = emblem,
            cornerRadius = MaterialTheme.cornerRadius.medium,
            onErrorImageRes = R.drawable.ic_ball
        )
        Column {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.tiny),
                text = name.uppercase(),
                color = competitionType.textColorForCompetition,
                fontSize = MaterialTheme.textSizing.small,
                fontWeight = FontWeight.Bold
            )
            Row {
                RoundedImageWithBackground(
                    modifier = Modifier
                        .size(MaterialTheme.sizing.extraSmall)
                        .padding(MaterialTheme.spacing.tiny),
                    imageUrl = countryFlag,
                    innerPadding = MaterialTheme.spacing.none,
                    cornerRadius = MaterialTheme.cornerRadius.none,
                    backgroundColor = Color.Transparent,
                    onErrorImageRes = R.drawable.ic_ball
                )
                Text(
                    modifier = Modifier.padding(MaterialTheme.spacing.tiny),
                    text = countryName.uppercase(),
                    color = competitionType.textColorForCompetition,
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
