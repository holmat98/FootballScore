package com.mateuszholik.footballscore

import android.app.Application
import com.mateuszholik.footballscore.logging.CrashReportingTree
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        setUpTimber()
    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}
