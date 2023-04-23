package com.mateuszholik.leaguedetails

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mateuszholik.model.Season
import com.mateuszholik.uicomponents.divider.CustomDivider
import com.mateuszholik.uicomponents.seasonwinner.SeasonWinnerItem

@Composable
fun Winners(seasons: List<Season>) {
    LazyColumn {
        itemsIndexed(items = seasons) { index, season ->
            SeasonWinnerItem(
                modifier = Modifier.fillMaxWidth(),
                seasonStartDate = season.startDate,
                seasonEndDate = season.endDate,
                winner = season.winner
            )
            if (index < seasons.lastIndex) {
                CustomDivider()
            }
        }
    }
}
