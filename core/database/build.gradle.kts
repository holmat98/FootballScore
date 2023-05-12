plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    kotlin (Plugins.KAPT)
    id (Plugins.HILT)
}

android {
    namespace = "com.mateuszholik.database"
    compileSdk = DefaultConfig.COMPILE_SDK

    defaultConfig {
        minSdk = DefaultConfig.MIN_SDK

        testInstrumentationRunner = DefaultConfig.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // CoreKtx
    implementation(CoreKtx.DEPENDENCY)

    // Room
    implementation(Room.DEPENDENCY)
    annotationProcessor(Room.COMPILER)
    kapt(Room.COMPILER)
    implementation(Room.KTX)

    // Hilt
    implementation(Hilt.DEPENDENCY)
    kapt(Hilt.Compiler.DEPENDENCY)
}
