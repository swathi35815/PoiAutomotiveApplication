plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.poiautomotiveapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.poiautomotiveapplication"
        minSdk = 29
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Automotive template
    implementation("androidx.car.app:app:1.7.0-alpha02")
    // Automotive core libs
    /*implementation("androidx.car.app:car-app-core:1.3.0")*/
    // For Android Automotive specific functionality
    implementation("androidx.car.app:app-automotive:1.7.0-alpha02")
    // For testing
    testImplementation("androidx.car.app:app-testing:1.7.0-alpha02")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}