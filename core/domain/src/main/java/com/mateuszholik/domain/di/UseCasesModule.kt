package com.mateuszholik.domain.di

import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.data.repositories.CompetitionRepository
import com.mateuszholik.domain.usecases.GetCombinedCompetitionDetailsUseCase
import com.mateuszholik.domain.usecases.GetCombinedCompetitionDetailsUseCaseImpl
import com.mateuszholik.domain.usecases.GetHead2HeadUseCase
import com.mateuszholik.domain.usecases.GetHead2HeadUseCaseImpl
import com.mateuszholik.domain.usecases.GetMatchUseCase
import com.mateuszholik.domain.usecases.GetMatchUseCaseImpl
import com.mateuszholik.domain.usecases.GetMatchesForDateUseCase
import com.mateuszholik.domain.usecases.GetMatchesForDateUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UseCasesModule {

    @Binds
    abstract fun bindsGetMatchesForDateUseCase(
        getMatchesForDateUseCaseImpl: GetMatchesForDateUseCaseImpl
    ): GetMatchesForDateUseCase

    @Binds
    abstract fun bindsMatchUseCase(
        getMatchUseCaseImpl: GetMatchUseCaseImpl
    ): GetMatchUseCase

    @Binds
    abstract fun bindsHead2HeadUseCase(
        getHead2HeadUseCaseImpl: GetHead2HeadUseCaseImpl
    ): GetHead2HeadUseCase
}

@Module
@InstallIn(SingletonComponent::class)
internal object UseCasesSingletonComponentModule{

    @Provides
    fun providesGetCombinedCompetitionDetailsUseCase(
        competitionRepository: CompetitionRepository,
        dispatchersProvider: DispatchersProvider,
    ): GetCombinedCompetitionDetailsUseCase =
        GetCombinedCompetitionDetailsUseCaseImpl(
            competitionRepository = competitionRepository,
            dispatchersProvider = dispatchersProvider
        )
}
