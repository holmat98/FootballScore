plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Google.KSP.PLUGIN)
    id(Plugins.HILT)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // CoreKtx
    implementation(CoreKtx.DEPENDENCY)

    // Room
    implementation(Room.DEPENDENCY)
    annotationProcessor(Room.COMPILER)
    ksp(Room.COMPILER)
    implementation(Room.KTX)

    // Hilt
    implementation(Hilt.DEPENDENCY)
    ksp(Hilt.Compiler.DEPENDENCY)
}
