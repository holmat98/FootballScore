package com.mateuszholik.data.di

import com.mateuszholik.data.repositories.CompetitionRepository
import com.mateuszholik.data.repositories.CompetitionRepositoryImpl
import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.data.repositories.MatchesRepositoryImpl
import com.mateuszholik.network.repositories.CompetitionApiRepository
import com.mateuszholik.network.repositories.MatchesApiRepository
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
    fun providesMatchesRepository(
        matchesApiRepository: MatchesApiRepository,
    ): MatchesRepository =
        MatchesRepositoryImpl(
            matchesApiRepository = matchesApiRepository
        )

    @Provides
    @Singleton
    fun providesCompetitionRepository(
        competitionApiRepository: CompetitionApiRepository,
    ): CompetitionRepository =
        CompetitionRepositoryImpl(
            competitionApiRepository = competitionApiRepository
        )
}
