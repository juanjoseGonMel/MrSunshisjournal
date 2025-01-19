plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    //Nav comp
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)

    //id("kotlin-kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.modulo10.juandev.mrsunshisjournal"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.modulo10.juandev.mrsunshisjournal"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "2.0"

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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Glide
    implementation(libs.glide)


    //Room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    //Corrutinas con alcance lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //Para Nav Compom
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)

    //ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    //LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.material.dialogs)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}