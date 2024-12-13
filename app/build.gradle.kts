plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.analogwatchface"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.analogwatchface"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.wear:wear:1.3.0")
    implementation("androidx.wear:wear-watchface:1.3.0")
}