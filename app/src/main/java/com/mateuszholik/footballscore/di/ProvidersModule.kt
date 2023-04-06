package com.mateuszholik.footballscore.di

import com.mateuszholik.footballscore.providers.CurrentActivityProvider
import com.mateuszholik.footballscore.providers.CurrentActivityProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProvidersModule {

    @Provides
    @Singleton
    fun providesCurrentActivityProvider(): CurrentActivityProvider = CurrentActivityProviderImpl()
}
