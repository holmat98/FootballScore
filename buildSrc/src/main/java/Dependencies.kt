object Plugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val KOTLIN = "kotlin"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val FIREBASE_CRASHLYTICS = "com.google.firebase.crashlytics"
    const val GOOGLE_SERVICES = "com.google.gms.google-services"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KAPT = "kapt"
    const val HILT = "com.google.dagger.hilt.android"
    const val DYNAMIC_FEATURE = "com.android.dynamic-feature"
}

object GradlePlugins {
    const val kotlinVersion = "1.9.10"
    private const val gradlePluginVersion = "8.1.2"

    const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:$gradlePluginVersion"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val KOTLIN_JVM = "org.jetbrains.kotlin.jvm"
}

object DefaultConfig {
    const val COMPILE_SDK = 34
    const val APPLICATION_ID = "com.mateuszholik.footballscore"
    const val NAMESPACE = APPLICATION_ID
    const val MIN_SDK = 29
    const val TARGET_SDK = 34
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

object Proguard {
    const val FILE = "proguard-android-optimize.txt"
    const val RULES_FILE = "proguard-rules.pro"
}

object CoreKtx {
    const val DEPENDENCY = "androidx.core:core-ktx:1.12.0"
}

object Activity {
    const val DEPENDENCY = "androidx.activity:activity-compose:1.7.2"
}

object Compose {
    private const val composeVersion = "1.5.2"

    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.3"
    const val UI = "androidx.compose.ui:ui:$composeVersion"
    const val MATERIAL = "androidx.compose.material3:material3:1.2.0-alpha02"
    const val PREVIEW = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val TESTS = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val NAVIGATION = "androidx.navigation:navigation-compose:2.7.3"
    const val TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:$composeVersion"
    const val MATERIAL_DESIGN_ICONS = "androidx.compose.material:material-icons-extended:$composeVersion"
}

object ConstraintLayout {
    private const val version = "1.0.1"

    const val DEPENDENCY = "androidx.constraintlayout:constraintlayout-compose:$version"
}

object MaterialDesign {
    private const val version = "1.9.0"

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
    private const val lifecycleVersion = "2.6.2"

    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"

    object Compose {
        private const val version = "2.6.2"
        const val DEPENDENCY = "androidx.lifecycle:lifecycle-runtime-compose:$version"
    }
}

object Timber {
    private const val version = "5.0.1"

    const val DEPENDENCY = "com.jakewharton.timber:timber:$version"
}

object Google {

    object KSP {
        const val version = "1.9.10-1.0.13"

        const val PLUGIN = "com.google.devtools.ksp"
    }

    object Services {
        private const val version = "4.3.15"

        const val DEPENDENCY = "com.google.gms:google-services:$version"
    }

    object PlayCore {
        private const val version = "2.1.0"

        const val DEPENDENCY = "com.google.android.play:feature-delivery:$version"
        const val DEPENDENCY_KTX = "com.google.android.play:feature-delivery-ktx:$version"
    }

    object Firebase {
        object Crashlytics {
            private const val gradleVersion = "2.9.9"
            private const val version = "18.4.3"

            const val GRADLE = "com.google.firebase:firebase-crashlytics-gradle:$gradleVersion"
            const val DEPENDENCY = "com.google.firebase:firebase-crashlytics:$version"
        }
    }
}

object Android {

    object DynamicFeature {
        const val version = "7.4.0"
        const val DEPENDENCY = "com.android.dynamic-feature"
    }
}

object LeakCanary {
    private const val version = "2.10"

    const val DEPENDENCY = "com.squareup.leakcanary:leakcanary-android:$version"
}

object Accompanist {

    const val SYSTEM_UI_CONTROLLER = "com.google.accompanist:accompanist-systemuicontroller:0.32.0"
}

object Retrofit {
    private const val version = "2.9.0"

    const val DEPENDENCY = "com.squareup.retrofit2:retrofit:$version"

    object GsonConverter {
        const val DEPENDENCY = "com.squareup.retrofit2:converter-gson:$version"
    }
}

object OkHttp {
    private const val version = "4.10.0"

    const val DEPENDENCY = "com.squareup.okhttp3:okhttp:$version"

    object Interceptor {
        const val DEPENDENCY = "com.squareup.okhttp3:logging-interceptor:$version"
    }
}

object Hilt {
    const val version = "2.48"

    const val PLUGIN = "com.google.dagger.hilt.android"
    const val DEPENDENCY = "com.google.dagger:hilt-android:$version"

    object Compiler {
        const val DEPENDENCY = "com.google.dagger:hilt-android-compiler:$version"
    }

    object Compose {
        private const val version = "1.0.0"

        const val DEPENDENCY = "androidx.hilt:hilt-navigation-compose:$version"
    }
}

object Coil {
    private const val version = "2.4.0"

    const val DEPENDENCY = "io.coil-kt:coil-compose:$version"

    object Svg {
        const val DEPENDENCY = "io.coil-kt:coil-svg:$version"
    }
}

object Lottie {
    private const val version = "6.1.0"

    const val DEPENDENCY = "com.airbnb.android:lottie-compose:$version"
}

object AndroidGitVersion {
    const val VERSION = "0.4.14"
    const val PLUGIN = "com.gladed.androidgitversion"
}

object Room {
    private const val version = "2.5.2"

    const val DEPENDENCY = "androidx.room:room-runtime:$version"
    const val COMPILER = "androidx.room:room-compiler:$version"
    const val KTX = "androidx.room:room-ktx:$version"
}
