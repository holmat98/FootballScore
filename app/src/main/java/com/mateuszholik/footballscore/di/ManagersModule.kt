package com.mateuszholik.footballscore.di

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManager
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManagerImpl
import com.mateuszholik.footballscore.providers.CurrentActivityProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ManagersModule {

    @Singleton
    @Provides
    fun providesSplitInstallManager(@ApplicationContext context: Context): SplitInstallManager =
        SplitInstallManagerFactory.create(context)

    @Singleton
    @Provides
    fun providesDynamicModuleInstallationManager(
        currentActivityProvider: CurrentActivityProvider,
        splitInstallManager: SplitInstallManager,
    ): DynamicModuleInstallationManager =
        DynamicModuleInstallationManagerImpl(
            currentActivityProvider = currentActivityProvider,
            splitInstallManager = splitInstallManager
        )
}
