package com.mateuszholik.leaguedetails.models

import androidx.annotation.StringRes
import com.mateuszholik.designsystem.R

enum class Page(@StringRes val textResId: Int) {

    COMPETITION_TABLE(R.string.competition_details_page_table),
    TOP_SCORERS(R.string.competition_details_page_scorers),
    WINNERS(R.string.competition_details_page_winners)
}
