package com.mateuszholik.leaguedetails.contract

import androidx.compose.runtime.Composable
import com.mateuszholik.footballscore.contract.LeagueDetailsContract
import com.mateuszholik.leaguedetails.LeagueDetailsScreen

class LeagueDetailsContractImpl : LeagueDetailsContract {

    @Composable
    override fun DisplayLeagueDetails(leagueId: Int) {
        LeagueDetailsScreen(leagueId)
    }
}
