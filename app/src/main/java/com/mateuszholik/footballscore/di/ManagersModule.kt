package com.mateuszholik.footballscore.di

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManager
import com.mateuszholik.footballscore.managers.DynamicModuleInstallationManagerImpl
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
        splitInstallManager: SplitInstallManager,
    ): DynamicModuleInstallationManager =
        DynamicModuleInstallationManagerImpl(
            splitInstallManager = splitInstallManager
        )
}
