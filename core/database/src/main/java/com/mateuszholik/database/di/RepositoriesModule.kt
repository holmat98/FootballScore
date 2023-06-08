package com.mateuszholik.database.di

import com.mateuszholik.database.repositories.MatchesDBRepository
import com.mateuszholik.database.repositories.MatchesDBRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoriesModule {

    @Singleton
    @Binds
    abstract fun bindsMatchesDBRepository(
        matchesDBRepositoryImpl: MatchesDBRepositoryImpl
    ): MatchesDBRepository
}
