package com.mateuszholik.network.di

import com.mateuszholik.network.repositories.MatchesApiRepository
import com.mateuszholik.network.repositories.MatchesApiRepositoryImpl
import com.mateuszholik.network.services.MatchesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoriesModule {

    @Provides
    @Singleton
    fun providesMatchesApiRepository(matchesService: MatchesService): MatchesApiRepository =
        MatchesApiRepositoryImpl(
            matchesService = matchesService,
            ioDispatcher = Dispatchers.IO
        )
}
