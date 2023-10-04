plugins {
    id(Plugins.ANDROID_LIBRARY)
    id (Plugins.KOTLIN_ANDROID)
    id(Google.KSP.PLUGIN)
    id (Plugins.HILT)
}

android {
    namespace = "com.mateuszholik.watchedmatches"
    compileSdk = DefaultConfig.COMPILE_SDK

    defaultConfig {
        minSdk = DefaultConfig.MIN_SDK

        testInstrumentationRunner = DefaultConfig.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile(Proguard.FILE),
                Proguard.RULES_FILE
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.KOTLIN_COMPILER_EXTENSION_VERSION
    }
}

dependencies {

    // Modules
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:uicomponents"))

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

    // Core ktx
    implementation(CoreKtx.DEPENDENCY)

    // Lifecycle
    implementation(Lifecycle.LIFECYCLE_RUNTIME)
    implementation(Lifecycle.VIEW_MODEL)
    implementation(Lifecycle.Compose.DEPENDENCY)

    // Timber
    implementation(Timber.DEPENDENCY)

    // Hilt
    implementation(Hilt.DEPENDENCY)
    implementation(Hilt.Compose.DEPENDENCY)
    ksp(Hilt.Compiler.DEPENDENCY)
}
