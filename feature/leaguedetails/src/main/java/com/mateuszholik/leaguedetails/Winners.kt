package com.mateuszholik.leaguedetails

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mateuszholik.model.Season
import com.mateuszholik.uicomponents.seasonwinner.SeasonWinnerItem

@Composable
fun Winners(seasons: List<Season>) {
    LazyColumn {
        items(items = seasons) {
            SeasonWinnerItem(
                modifier = Modifier.fillMaxWidth(),
                seasonStartDate = it.startDate,
                seasonEndDate = it.endDate,
                winner = it.winner
            )
        }
    }
}
