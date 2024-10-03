plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    kotlin("plugin.parcelize")
}

android {
    namespace = "com.example.newstatussaver"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newstatussaver"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures{
        viewBinding = true
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
    // Core libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.recyclerview.v121)

    // Image loading
    implementation(libs.glide.v4120)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Lifecycle and ViewModel
    implementation(libs.androidx.lifecycle.livedata.ktx.v283)
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v283)
    // UI components
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.squareup.okhttp)

    // OkHttp Logging Interceptor
    implementation(libs.logging.interceptor) // Check for the latest version

    implementation(libs.androidx.core.splashscreen)

//    implementation(libs.x.libs.include)
//    implementation(libs.kotlin.stdlib.jdk7)
}