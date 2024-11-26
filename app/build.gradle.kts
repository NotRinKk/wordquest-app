plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "2.0.21"
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "app.wordquest"
    compileSdk = 34

    defaultConfig {
        applicationId = "app.wordquest"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.21")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.navigation:navigation-compose:2.8.4")
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")



    //Hilt
    kapt ("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")


    implementation ("androidx.work:work-runtime-ktx:2.7.1")

//    kapt ("com.google.dagger:hilt-android-compiler:2.51.1")
//    //implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
//    //kapt ("androidx.hilt:hilt-compiler:1.2.0")
//    implementation("com.google.dagger:hilt-android:2.51.1")
//    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
//    //implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")


    implementation("io.ktor:ktor-client-core:3.0.1")
    implementation ("io.ktor:ktor-client-android:3.0.1")
    implementation ("io.ktor:ktor-client-content-negotiation:3.0.1")
    implementation("io.ktor:ktor-client-json:3.0.1")
    implementation ("io.ktor:ktor-client-serialization:3.0.1")
    implementation ("io.ktor:ktor-serialization-kotlinx-json:3.0.1")
    implementation ("io.ktor:ktor-client-logging:3.0.1")
    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.21")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}