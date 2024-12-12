plugins {
    id 'com.android.application'
    kotlin 'android'
}

android {
    compileSdkVersion 29
    defaultConfig {
        applicationId "com.example.analogwatchface"
        minSdkVersion 23
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-project.txt'
        }
    }
}

dependencies {
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.wear:wear:1.0.0'
    implementation 'androidx.wear:wear-watchface:1.0.0'
}