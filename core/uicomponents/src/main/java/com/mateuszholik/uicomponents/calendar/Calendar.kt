package com.mateuszholik.uicomponents.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.theme.textSizing
import com.mateuszholik.uicomponents.R
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun Calendar(
    days: List<LocalDate>,
    selectedDay: LocalDate,
    onDaySelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        items(items = days) { day ->
            if (day == selectedDay) {
                SelectedDay(day = day)
            } else {
                Day(day = day, onDaySelected = onDaySelected)
            }
        }
    }
}

@Composable
private fun Day(
    day: LocalDate,
    onDaySelected: (LocalDate) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.spacing.tiny)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onDaySelected(day) },
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.elevatedCardColors()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.tiny),
                text = day.dayOfWeek.toText(),
                fontSize = MaterialTheme.textSizing.tiny,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier.padding(
                    bottom = MaterialTheme.spacing.tiny
                ),
                text = "${day.dayOfMonth}.${day.month.value}",
                fontSize = MaterialTheme.textSizing.tiny,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun SelectedDay(day: LocalDate) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.spacing.tiny)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.tiny),
                text = day.dayOfWeek.toText(),
                fontSize = MaterialTheme.textSizing.tiny,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                modifier = Modifier.padding(
                    bottom = MaterialTheme.spacing.tiny
                ),
                text = "${day.dayOfMonth}.${day.month.value}",
                fontSize = MaterialTheme.textSizing.tiny,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun DayOfWeek.toText(): String =
    when (this) {
        DayOfWeek.MONDAY -> stringResource(R.string.calendar_monday)
        DayOfWeek.TUESDAY -> stringResource(R.string.calendar_tuesday)
        DayOfWeek.WEDNESDAY -> stringResource(R.string.calendar_wednesday)
        DayOfWeek.THURSDAY -> stringResource(R.string.calendar_thursday)
        DayOfWeek.FRIDAY -> stringResource(R.string.calendar_friday)
        DayOfWeek.SATURDAY -> stringResource(R.string.calendar_saturday)
        DayOfWeek.SUNDAY -> stringResource(R.string.calendar_sunday)
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
            ),
            selectedDay = LocalDate.of(2023, 2, 20),
            onDaySelected = {}
        )
    }
}