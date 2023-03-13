plugins {
    id (Plugins.ANDROID_APPLICATION)
    id (Plugins.KOTLIN_ANDROID)
    id (Plugins.GOOGLE_SERVICES)
    id (Plugins.FIREBASE_CRASHLYTICS)
    kotlin (Plugins.KAPT)
    id (Plugins.HILT)
}

android {
    namespace = DefaultConfig.NAMESPACE
    compileSdk = DefaultConfig.COMPILE_SDK

    defaultConfig {
        applicationId = DefaultConfig.APPLICATION_ID
        minSdk = DefaultConfig.MIN_SDK
        targetSdk = DefaultConfig.TARGET_SDK
        versionCode = DefaultConfig.VERSION_CODE
        versionName = DefaultConfig.VERSION_NAME

        testInstrumentationRunner = DefaultConfig.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile(Proguard.FILE),
                Proguard.RULES_FILE
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.KOTLIN_COMPILER_EXTENSION_VERSION
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    dynamicFeatures += setOf(":feature:leaguedetails")
}

dependencies {

    // Modules
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":feature:matches"))
    implementation(project(":feature:matchdetails"))

    // Activity
    implementation(Activity.DEPENDENCY)

    // Compose
    implementation(Compose.UI)
    implementation(Compose.MATERIAL)
    implementation(Compose.PREVIEW)
    implementation(Compose.NAVIGATION)
    androidTestImplementation(Compose.TESTS)
    debugImplementation(Compose.UI_TOOLING)
    debugImplementation(Compose.TEST_MANIFEST)

    // Material Design
    implementation(MaterialDesign.DEPENDENCY)

    // Tests
    testImplementation(Testing.JUnit.DEPENDENCY)
    testCompileOnly(Testing.JUnit.API_DEPENDENCY)
    testRuntimeOnly(Testing.JUnit.ENGINE)
    androidTestImplementation(Testing.ANDROID_J_UNIT)
    androidTestImplementation(Testing.ESPRESSO)

    // Core ktx
    implementation(CoreKtx.DEPENDENCY)

    // Lifecycle
    implementation(Lifecycle.LIFECYCLE_RUNTIME)
    implementation(Lifecycle.VIEW_MODEL)

    // Timber
    implementation(Timber.DEPENDENCY)

    // Crashlytics
    implementation(Google.Firebase.Crashlytics.DEPENDENCY)

    // LeakCanary
    debugImplementation(LeakCanary.DEPENDENCY)

    // Accompanist
    implementation(Accompanist.SYSTEM_UI_CONTROLLER)

    // Hilt
    implementation(Hilt.DEPENDENCY)
    kapt(Hilt.Compiler.DEPENDENCY)

    // Play core
    implementation(Google.PlayCore.DEPENDENCY)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
