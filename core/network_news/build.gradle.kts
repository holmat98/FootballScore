plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    kotlin (Plugins.KAPT)
    id (Plugins.HILT)
}

android {
    namespace = "com.mateuszholik.network_news"
    compileSdk = DefaultConfig.COMPILE_SDK

    defaultConfig {
        minSdk = DefaultConfig.MIN_SDK
        targetSdk = DefaultConfig.TARGET_SDK

        testInstrumentationRunner = DefaultConfig.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(type = "String", name = "NEWS_API_KEY", value = "\"${project.property("API_KEY") as String}\"")
        buildConfigField(type = "String", name = "NEWS_API_URL", value = "\"https://newsapi.org/v2/\"")
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

    // Retrofit
    implementation(Retrofit.DEPENDENCY)
    implementation(Retrofit.GsonConverter.DEPENDENCY)

    // OkHttp
    implementation(OkHttp.DEPENDENCY)
    implementation(OkHttp.Interceptor.DEPENDENCY)

    // Hilt
    implementation(Hilt.DEPENDENCY)
    kapt(Hilt.Compiler.DEPENDENCY)
}
