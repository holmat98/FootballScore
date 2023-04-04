package com.mateuszholik.leaguedetails.contract

import androidx.compose.runtime.Composable
import com.mateuszholik.footballscore.contract.LeagueDetailsContract
import com.mateuszholik.leaguedetails.LeagueDetailsScreen

/*
 * Reflection is used to create instance of this class.
 * After every change to name or package please update CONTRACT_IMPL_NAME variable in companion object of
 * LeagueDetailsContract interface.
 */
class LeagueDetailsContractImpl : LeagueDetailsContract {

    @Composable
    override fun DisplayLeagueDetails(leagueId: Int) {
        LeagueDetailsScreen(leagueId)
    }
}
