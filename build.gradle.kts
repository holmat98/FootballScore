buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath (GradlePlugins.ANDROID_GRADLE_PLUGIN)
        classpath (GradlePlugins.KOTLIN_GRADLE_PLUGIN)
    }
}