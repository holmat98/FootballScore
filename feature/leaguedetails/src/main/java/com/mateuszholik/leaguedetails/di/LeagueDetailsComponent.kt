package com.mateuszholik.leaguedetails.di

import android.app.Application
import com.mateuszholik.domain.di.FeatureModuleDependencies
import com.mateuszholik.leaguedetails.contract.LeagueDetailsContractImpl
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [FeatureModuleDependencies::class]
)
interface LeagueDetailsComponent {

    fun inject(leagueDetailsContractImpl: LeagueDetailsContractImpl)

    @Component.Factory
    interface Factory {
        fun create(
            featureModuleDependencies: FeatureModuleDependencies,
            @BindsInstance leagueDetailsContractImpl: LeagueDetailsContractImpl,
            @BindsInstance application: Application,
        ): LeagueDetailsComponent
    }
}
