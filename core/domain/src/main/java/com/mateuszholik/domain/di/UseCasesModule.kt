package com.mateuszholik.domain.di

import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.data.repositories.CompetitionRepository
import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.domain.usecases.GetCombinedCompetitionDetailsUseCase
import com.mateuszholik.domain.usecases.GetCombinedCompetitionDetailsUseCaseImpl
import com.mateuszholik.domain.usecases.GetHead2HeadUseCase
import com.mateuszholik.domain.usecases.GetHead2HeadUseCaseImpl
import com.mateuszholik.domain.usecases.GetMatchUseCase
import com.mateuszholik.domain.usecases.GetMatchUseCaseImpl
import com.mateuszholik.domain.usecases.GetMatchesForDateUseCase
import com.mateuszholik.domain.usecases.GetMatchesForDateUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object UseCasesModule {

    @Provides
    fun providesGetMatchesForDateUseCase(
        matchesRepository: MatchesRepository,
        dispatchersProvider: DispatchersProvider,
    ): GetMatchesForDateUseCase =
        GetMatchesForDateUseCaseImpl(
            matchesRepository = matchesRepository,
            dispatchersProvider = dispatchersProvider
        )

    @Provides
    fun providesMatchUseCase(
        matchesRepository: MatchesRepository,
        dispatchersProvider: DispatchersProvider,
    ): GetMatchUseCase =
        GetMatchUseCaseImpl(
            matchesRepository = matchesRepository,
            dispatchersProvider = dispatchersProvider
        )

    @Provides
    fun providesHead2HeadUseCase(
        matchesRepository: MatchesRepository,
        dispatchersProvider: DispatchersProvider,
    ): GetHead2HeadUseCase =
        GetHead2HeadUseCaseImpl(
            matchesRepository = matchesRepository,
            dispatchersProvider = dispatchersProvider
        )
}

@Module
@InstallIn(SingletonComponent::class)
internal object UseCasesSingletonComponentModule{

    @Provides
    fun providesGetCombinedCompetitionDetailsUseCase(
        competitionRepository: CompetitionRepository,
    ): GetCombinedCompetitionDetailsUseCase =
        GetCombinedCompetitionDetailsUseCaseImpl(
            competitionRepository = competitionRepository
        )
}
