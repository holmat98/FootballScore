package com.mateuszholik.domain.di

import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.data.repositories.CompetitionRepository
import com.mateuszholik.domain.usecases.DeleteWatchedGameUseCase
import com.mateuszholik.domain.usecases.DeleteWatchedGameUseCaseImpl
import com.mateuszholik.domain.usecases.GetCombinedCompetitionDetailsUseCase
import com.mateuszholik.domain.usecases.GetCombinedCompetitionDetailsUseCaseImpl
import com.mateuszholik.domain.usecases.GetHead2HeadUseCase
import com.mateuszholik.domain.usecases.GetHead2HeadUseCaseImpl
import com.mateuszholik.domain.usecases.GetMatchUseCase
import com.mateuszholik.domain.usecases.GetMatchUseCaseImpl
import com.mateuszholik.domain.usecases.GetMatchesForDateUseCase
import com.mateuszholik.domain.usecases.GetMatchesForDateUseCaseImpl
import com.mateuszholik.domain.usecases.GetTopSportsNewsUseCase
import com.mateuszholik.domain.usecases.GetTopSportsNewsUseCaseImpl
import com.mateuszholik.domain.usecases.GetWatchedGamesIdsUseCase
import com.mateuszholik.domain.usecases.GetWatchedGamesIdsUseCaseImpl
import com.mateuszholik.domain.usecases.GetWatchedGamesUseCase
import com.mateuszholik.domain.usecases.GetWatchedGamesUseCaseImpl
import com.mateuszholik.domain.usecases.InsertWatchedGameUseCase
import com.mateuszholik.domain.usecases.InsertWatchedGameUseCaseImpl
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

    @Binds
    abstract fun bindsGetWatchedGamesUseCase(
        getWatchedGamesUseCaseImpl: GetWatchedGamesUseCaseImpl
    ): GetWatchedGamesUseCase

    @Binds
    abstract fun bindsInsertWatchedGameUseCase(
        insertWatchedGameUseCaseImpl: InsertWatchedGameUseCaseImpl
    ): InsertWatchedGameUseCase

    @Binds
    abstract fun bindsDeleteWatchedGameUseCase(
        deleteWatchedGameUseCaseImpl: DeleteWatchedGameUseCaseImpl
    ): DeleteWatchedGameUseCase

    @Binds
    abstract fun bindsGetWatchedGamesIdsUseCase(
        getWatchedGamesIdsUseCaseImpl: GetWatchedGamesIdsUseCaseImpl
    ): GetWatchedGamesIdsUseCase

    @Binds
    abstract fun bindsGetTopSportsNewsUseCase(
        getTopSportsNewsUseCaseImpl: GetTopSportsNewsUseCaseImpl
    ): GetTopSportsNewsUseCase
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
