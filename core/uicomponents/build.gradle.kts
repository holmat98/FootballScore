plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

android {
    namespace = "com.mateuszholik.uicomponents"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))

    // CoreKtx
    implementation(CoreKtx.DEPENDENCY)

    // Compose
    implementation(Compose.UI)
    implementation(Compose.MATERIAL)
    implementation(Compose.PREVIEW)
    implementation(Compose.NAVIGATION)
    androidTestImplementation(Compose.TESTS)
    debugImplementation(Compose.UI_TOOLING)
    debugImplementation(Compose.TEST_MANIFEST)

    // Coil
    implementation(Coil.DEPENDENCY)
    implementation(Coil.Svg.DEPENDENCY)

    // Lottie
    implementation(Lottie.DEPENDENCY)

    // Constraint Layout
    implementation(ConstraintLayout.DEPENDENCY)
}
