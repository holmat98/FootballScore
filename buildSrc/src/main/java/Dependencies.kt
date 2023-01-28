object Plugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val KOTLIN = "org.jetbrains.kotlin.android"
}

object GradlePlugins {
    private const val kotlinVersion = "1.8.0"
    private const val gradlePluginVersion = "7.4.0"

    const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:$gradlePluginVersion"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
}

object DefaultConfig {
    const val COMPILE_SDK = 33
    const val APPLICATION_ID = "com.mateuszholik.footballscore"
    const val NAMESPACE = APPLICATION_ID
    const val MIN_SDK = 29
    const val TARGET_SDK = 33
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

object Proguard {
    const val FILE = "proguard-android-optimize.txt"
    const val RULES = "proguard-rules.pro"
}

object CoreKtx {
    const val DEPENDENCY = "androidx.core:core-ktx:1.9.0"
}

object Activity {
    const val DEPENDENCY = "androidx.activity:activity-compose:1.6.1"
}

object Compose {
    private const val composeVersion = "1.3.3"

    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.4.0"
    const val UI = "androidx.compose.ui:ui:$composeVersion"
    const val MATERIAL = "androidx.compose.material3:material3:1.0.1"
    const val PREVIEW = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val TESTS = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val NAVIGATION = "androidx.navigation:navigation-compose:2.5.2"
    const val TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:$composeVersion"
}

object MaterialDesign {
    private const val version = "1.8.0"

    const val DEPENDENCY = "com.google.android.material:material:$version"
}

object Testing {

    object JUnit {
        private const val version = "5.9.1"

        const val DEPENDENCY = "org.junit.jupiter:junit-jupiter:$version"
        const val API_DEPENDENCY = "org.junit.jupiter:junit-jupiter-api:$version"
        const val ENGINE = "org.junit.jupiter:junit-jupiter-engine:$version"
    }

    const val ANDROID_J_UNIT = "androidx.test.ext:junit:1.1.5"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:3.5.1"
}

object Lifecycle {
    private const val lifecycleVersion = "2.5.1"

    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
}
