package com.mateuszholik.footballscore.logging

import android.util.Log.ERROR
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {

    private val crashlytics = FirebaseCrashlytics.getInstance()

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        crashlytics.log(message)

        if (priority == ERROR) {
            if (t == null) {
                crashlytics.recordException(Throwable(message))
            } else {
                crashlytics.recordException(t)
            }
        }
    }
}
