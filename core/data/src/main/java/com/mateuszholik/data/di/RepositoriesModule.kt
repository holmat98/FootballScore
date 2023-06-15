package com.mateuszholik.data.di

import com.mateuszholik.common.providers.CurrentDateProvider
import com.mateuszholik.data.repositories.CompetitionRepository
import com.mateuszholik.data.repositories.CompetitionRepositoryImpl
import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.data.repositories.MatchesRepositoryImpl
import com.mateuszholik.data.repositories.NewsRepository
import com.mateuszholik.data.repositories.NewsRepositoryImpl
import com.mateuszholik.database.repositories.MatchesDBRepository
import com.mateuszholik.network.repositories.CompetitionApiRepository
import com.mateuszholik.network.repositories.MatchesApiRepository
import com.mateuszholik.network.repositories.NewsApiRepository
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
        matchesDBRepository: MatchesDBRepository,
        currentDateProvider: CurrentDateProvider,
    ): MatchesRepository =
        MatchesRepositoryImpl(
            matchesApiRepository = matchesApiRepository,
            matchesDBRepository = matchesDBRepository,
            currentDateProvider = currentDateProvider
        )

    @Provides
    @Singleton
    fun providesCompetitionRepository(
        competitionApiRepository: CompetitionApiRepository,
    ): CompetitionRepository =
        CompetitionRepositoryImpl(
            competitionApiRepository = competitionApiRepository
        )

    @Provides
    @Singleton
    fun providesNewsRepository(
        newsApiRepository: NewsApiRepository,
    ): NewsRepository =
        NewsRepositoryImpl(
            newsApiRepository = newsApiRepository
        )
}
