package com.mateuszholik.footballscore.providers

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.mateuszholik.footballscore.callbacks.ActivityLifecycleCallbacks
import java.lang.ref.WeakReference

internal interface CurrentActivityProvider {

    val currentActivity: Activity?

    fun startWatchingForCurrentActivityChanges(application: Application)
}

internal class CurrentActivityProviderImpl : CurrentActivityProvider {

    private var _currentActivity: WeakReference<Activity>? = null
    override val currentActivity: Activity?
        get() = _currentActivity?.get()

    private val activityLifecycleCallbacks = object : ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            _currentActivity = WeakReference(activity)
        }

        override fun onActivityDestroyed(activity: Activity) {
            _currentActivity = null
        }
    }

    override fun startWatchingForCurrentActivityChanges(application: Application) {
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
}
