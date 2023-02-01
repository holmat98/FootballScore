plugins {
    id (Plugins.ANDROID_APPLICATION)
    id (Plugins.KOTLIN)
    id (Plugins.GOOGLE_SERVICES)
    id (Plugins.FIREBASE_CRASHLYTICS)
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

        buildConfigField(type = "String", name = "API_KEY", value = "\"${project.property("API_KEY") as String}\"")
        buildConfigField(type = "String", name = "API_URL", value = "\"http://api.football-data.org/v4\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile(Proguard.FILE),
                Proguard.RULES
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
}

dependencies {

    // Modules
    implementation(project(":core:designsystem"))

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
    implementation(GoogleServices.Firebase.Crashlytics.DEPENDENCY)

    // LeakCanary
    debugImplementation(LeakCanary.DEPENDENCY)

    // Accompanist
    implementation(Accompanist.SYSTEM_UI_CONTROLLER)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
