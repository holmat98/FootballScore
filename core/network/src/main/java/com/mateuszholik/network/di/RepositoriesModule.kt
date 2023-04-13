package com.mateuszholik.network.di

import com.mateuszholik.network.repositories.CompetitionApiRepository
import com.mateuszholik.network.repositories.CompetitionApiRepositoryImpl
import com.mateuszholik.network.repositories.MatchesApiRepository
import com.mateuszholik.network.repositories.MatchesApiRepositoryImpl
import com.mateuszholik.network.services.CompetitionService
import com.mateuszholik.network.services.MatchesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoriesModule {

    @Provides
    @Singleton
    fun providesMatchesApiRepository(matchesService: MatchesService): MatchesApiRepository =
        MatchesApiRepositoryImpl(matchesService = matchesService)

    @Provides
    @Singleton
    fun providesCompetitionApiRepository(competitionService: CompetitionService): CompetitionApiRepository =
        CompetitionApiRepositoryImpl(competitionService = competitionService)
}
