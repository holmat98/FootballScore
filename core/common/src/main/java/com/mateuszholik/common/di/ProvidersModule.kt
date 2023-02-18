package com.mateuszholik.common.di

import com.mateuszholik.common.providers.DispatchersProvider
import com.mateuszholik.common.providers.DispatchersProviderImpl
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
    fun providesDispatchersProvider(): DispatchersProvider = DispatchersProviderImpl()
}
