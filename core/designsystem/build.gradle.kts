plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

android {
    namespace = "com.mateuszholik.designsystem"
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

    implementation(CoreKtx.DEPENDENCY)

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
}
