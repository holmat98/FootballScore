package com.mateuszholik.uicomponents.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.uicomponents.extensions.asHourString
import com.mateuszholik.uicomponents.extensions.toText
import java.time.LocalDate

@Composable
fun Calendar(
    days: List<LocalDate>,
    selectedDay: LocalDate,
    onDaySelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(modifier = modifier.fillMaxWidth()) {
        itemsIndexed(items = days) { index, day ->
            val dayItemModifier = if (index < days.lastIndex) {
                Modifier.padding(
                    top = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.small,
                )
            } else {
                Modifier.padding(MaterialTheme.spacing.small)
            }
            if (day == selectedDay) {
                SelectedDay(
                    modifier = dayItemModifier,
                    day = day
                )
            } else {
                Day(
                    modifier = dayItemModifier,
                    day = day,
                    onDaySelected = onDaySelected
                )
            }
        }
    }
}

@Composable
private fun Day(
    modifier: Modifier,
    day: LocalDate,
    onDaySelected: (LocalDate) -> Unit,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.cornerRadius.large))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable { onDaySelected(day) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.extraSmall,
                start = MaterialTheme.spacing.extraSmall,
                end = MaterialTheme.spacing.extraSmall
            ),
            text = day.dayOfWeek.toText,
            fontSize = MaterialTheme.textSizing.normal,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            modifier = Modifier.padding(
                bottom = MaterialTheme.spacing.extraSmall,
                start = MaterialTheme.spacing.extraSmall,
                end = MaterialTheme.spacing.extraSmall,
            ),
            text = day.asHourString(),
            fontSize = MaterialTheme.textSizing.normal,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
private fun SelectedDay(
    modifier: Modifier,
    day: LocalDate,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.cornerRadius.large))
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.extraSmall,
                start = MaterialTheme.spacing.extraSmall,
                end = MaterialTheme.spacing.extraSmall
            ),
            text = day.dayOfWeek.toText,
            fontSize = MaterialTheme.textSizing.normal,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            modifier = Modifier.padding(
                bottom = MaterialTheme.spacing.extraSmall,
                start = MaterialTheme.spacing.extraSmall,
                end = MaterialTheme.spacing.extraSmall,
            ),
            text = day.asHourString(),
            fontSize = MaterialTheme.textSizing.normal,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview
@Composable
private fun Preview() {
    FootballScoreTheme {
        Calendar(
            days = listOf(
                LocalDate.of(2023, 2, 18),
                LocalDate.of(2023, 2, 19),
                LocalDate.of(2023, 2, 20),
                LocalDate.of(2023, 2, 21),
                LocalDate.of(2023, 2, 22),
                LocalDate.of(2023, 2, 23),
                LocalDate.of(2023, 2, 24),
                LocalDate.of(2023, 2, 25),
                LocalDate.of(2023, 2, 26),
                LocalDate.of(2023, 2, 27),
                LocalDate.of(2023, 2, 28),
            ),
            selectedDay = LocalDate.of(2023, 2, 20),
            onDaySelected = {}
        )
    }
}
