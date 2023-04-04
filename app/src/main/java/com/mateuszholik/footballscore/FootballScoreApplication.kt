package com.mateuszholik.footballscore

import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.mateuszholik.footballscore.logging.CrashReportingTree
import com.mateuszholik.footballscore.providers.CurrentActivityProvider
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class FootballScoreApplication : SplitCompatApplication() {

    @Inject
    internal lateinit var currentActivityProvider: CurrentActivityProvider

    override fun onCreate() {
        super.onCreate()

        setUpTimber()
        currentActivityProvider.startWatchingForCurrentActivityChanges(this)
    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}
