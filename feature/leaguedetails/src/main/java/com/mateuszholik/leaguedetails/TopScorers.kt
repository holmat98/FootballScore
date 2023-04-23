package com.mateuszholik.leaguedetails

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mateuszholik.model.Scorer
import com.mateuszholik.uicomponents.divider.CustomDivider
import com.mateuszholik.uicomponents.scorer.ScorerItem
import com.mateuszholik.uicomponents.scorer.ScorersListLegend

@Composable
internal fun TopScorers(
    topScorers: List<Scorer>
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            ScorersListLegend(modifier = Modifier.fillMaxWidth())
        }

        itemsIndexed(items = topScorers) { index, scorer ->
            ScorerItem(
                position = index + 1,
                scorer = scorer,
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
            if (index < topScorers.lastIndex) {
                CustomDivider()
            }
        }
    }
}
