package com.mateuszholik.domain.di

import com.mateuszholik.domain.usecases.GetCombinedCompetitionDetailsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FeatureModuleDependencies {

    fun getCombinedCompetitionDetailsUseCase(): GetCombinedCompetitionDetailsUseCase
}
