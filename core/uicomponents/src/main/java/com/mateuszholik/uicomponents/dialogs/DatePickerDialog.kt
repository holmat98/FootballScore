package com.mateuszholik.uicomponents.dialogs

import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mateuszholik.designsystem.theme.FootballScoreTheme
import com.mateuszholik.designsystem.theme.cornerRadius
import com.mateuszholik.designsystem.theme.sizing
import com.mateuszholik.designsystem.theme.spacing
import com.mateuszholik.designsystem.R
import com.mateuszholik.uicomponents.extensions.toText
import java.time.LocalDate

@Composable
fun DatePickerDialog(
    title: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onDateSelected: (LocalDate) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var currentDate by remember { mutableStateOf(LocalDate.now()) }

    Dialog(onDismissRequest = onDismissRequest, properties = DialogProperties()) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(MaterialTheme.cornerRadius.large)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(
                            topStart = MaterialTheme.cornerRadius.large,
                            topEnd = MaterialTheme.cornerRadius.large
                        )
                    )
            ) {

                Text(
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.normal,
                        start = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.large
                    ),
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.extraSmall
                    ),
                    text = "${currentDate.dayOfWeek.toText}, ${currentDate.month.toText} ${currentDate.year}",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.size(MaterialTheme.sizing.tiny))

                Divider()
            }

            CalendarView { selectedDate -> currentDate = selectedDate }

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        bottom = MaterialTheme.spacing.normal,
                        end = MaterialTheme.spacing.normal
                    )
            ) {

                TextButton(
                    modifier = Modifier.padding(end = MaterialTheme.spacing.small),
                    onClick = onDismissRequest
                ) {
                    Text(text = negativeButtonText)
                }

                TextButton(onClick = {
                    onDateSelected(currentDate)
                    onDismissRequest()
                }
                ) {
                    Text(text = positiveButtonText)
                }
            }
        }
    }
}

@Composable
private fun CalendarView(
    onDateSelected: (LocalDate) -> Unit,
) {
    AndroidView(
        factory = {
            CalendarView(ContextThemeWrapper(it, R.style.Theme_CalendarView))
        }
    ) {
        it.setOnDateChangeListener { _, year, month, dayOfMonth ->
            onDateSelected(LocalDate.of(year, month + 1, dayOfMonth))
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    FootballScoreTheme {
        DatePickerDialog(
            title = "Select date",
            positiveButtonText = "OK",
            negativeButtonText = "Cancel",
            onDateSelected = {},
            onDismissRequest = {}
        )
    }
}

