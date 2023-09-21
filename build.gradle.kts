buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath (GradlePlugins.ANDROID_GRADLE_PLUGIN)
        classpath (GradlePlugins.KOTLIN_GRADLE_PLUGIN)
        classpath (Google.Services.DEPENDENCY)
        classpath (Google.Firebase.Crashlytics.GRADLE)
    }
}

plugins {
    id(Hilt.PLUGIN) version Hilt.version apply false
    id(GradlePlugins.KOTLIN_JVM) version GradlePlugins.kotlinVersion apply false
    id(Android.DynamicFeature.DEPENDENCY) version Android.DynamicFeature.version apply false
    id(AndroidGitVersion.PLUGIN) version AndroidGitVersion.VERSION apply false
    id(Google.KSP.PLUGIN) version Google.KSP.version apply false
}
