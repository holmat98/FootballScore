package com.mateuszholik.footballscore.contract

import androidx.compose.runtime.Composable

interface LeagueDetailsContract {

    @Composable
    fun DisplayLeagueDetails(
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
