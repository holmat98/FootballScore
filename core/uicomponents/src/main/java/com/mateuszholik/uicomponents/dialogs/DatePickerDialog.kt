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
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(
                            topStart = MaterialTheme.cornerRadius.large,
                            topEnd = MaterialTheme.cornerRadius.large
                        )
                    )
                    .padding(MaterialTheme.spacing.medium)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.size(MaterialTheme.sizing.small))

                Text(
                    text = "${currentDate.dayOfWeek.toText}, ${currentDate.month.toText} ${currentDate.year}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.size(MaterialTheme.sizing.extraSmall))
            }

            CalendarView { selectedDate -> currentDate = selectedDate }

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        bottom = MaterialTheme.spacing.medium,
                        end = MaterialTheme.spacing.medium
                    )
            ) {

                TextButton(onClick = onDismissRequest) {
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
            CalendarView(ContextThemeWrapper(it, R.style.CalenderViewCustom))
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
            positiveButtonText = "ok",
            negativeButtonText = "cancel",
            onDateSelected = {},
            onDismissRequest = {}
        )
    }
}

