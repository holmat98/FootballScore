package com.mateuszholik.footballscore.contract

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface LeagueDetailsContract {

    @Composable
    fun DisplayLeagueDetails(
        modifier: Modifier = Modifier,
        leagueId: Int,
        onBackPressed: () -> Unit,
    )

    companion object {
        private const val CONTRACT_IMPL_NAME =
            "com.mateuszholik.leaguedetails.contract.LeagueDetailsContractImpl"

        private var instance: LeagueDetailsContract? = null

        internal fun getInstance(): LeagueDetailsContract =
            instance ?: (Class.forName(CONTRACT_IMPL_NAME)
                .newInstance() as LeagueDetailsContract).also { instance = it }
    }
}
