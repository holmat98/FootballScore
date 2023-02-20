package com.mateuszholik.data.di

import com.mateuszholik.data.repositories.MatchesRepository
import com.mateuszholik.data.repositories.MatchesRepositoryImpl
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
}
