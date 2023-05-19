package com.mateuszholik.leaguedetails.contract

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mateuszholik.domain.di.FeatureModuleDependencies
import com.mateuszholik.domain.usecases.GetCombinedCompetitionDetailsUseCase
import com.mateuszholik.footballscore.contract.LeagueDetailsContract
import com.mateuszholik.leaguedetails.LeagueDetailsScreen
import com.mateuszholik.leaguedetails.LeagueDetailsViewModelFactory
import com.mateuszholik.leaguedetails.di.DaggerLeagueDetailsComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

/*
 * Reflection is used to create instance of this class.
 * After every change to name or package please update CONTRACT_IMPL_NAME variable in companion object of
 * LeagueDetailsContract interface.
 */
class LeagueDetailsContractImpl : LeagueDetailsContract {

    @Inject
    lateinit var getCombinedCompetitionDetailsUseCase: GetCombinedCompetitionDetailsUseCase

    @Composable
    override fun DisplayLeagueDetails(
        modifier: Modifier,
        leagueId: Int,
        onBackPressed: () -> Unit,
    ) {
        val activity = LocalContext.current as Activity
        initCoreDependencies(activity)

        LeagueDetailsScreen(
            modifier = modifier,
            onBackPressed = onBackPressed,
            viewModel = viewModel(
                factory = LeagueDetailsViewModelFactory(
                    getCombinedCompetitionDetailsUseCase = getCombinedCompetitionDetailsUseCase,
                    leagueId = leagueId
                )
            )
        )
    }

    private fun initCoreDependencies(activity: Activity) {
        val featureModuleDependencies = EntryPointAccessors.fromApplication(
            activity.applicationContext,
            FeatureModuleDependencies::class.java
        )

        DaggerLeagueDetailsComponent.factory().create(
            featureModuleDependencies = featureModuleDependencies,
            leagueDetailsContractImpl = this,
            application = activity.application
        ).inject(this)
    }
}
