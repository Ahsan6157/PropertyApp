plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id ("kotlin-parcelize")
//    id("kotlin-kapt")

}

android {
    /*buildFeatures {
        dataBinding = true
    }*/
    namespace = "com.example.propertyapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.propertyapp"
        minSdk = 24
        targetSdk = 33
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

    dataBinding{
        enable = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.room:room-ktx:2.5.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //    room
    implementation("androidx.room:room-common:2.5.2")
//    annotationProcessor("androidx.room:room-compiler:2.5.2")
    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:2.5.2")
//coroutines libraries
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //for livedata
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
//    recycler view
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    /*splash screen*/
    /*implementation("androidx.core:core-splashscreen:1.0.1")*/

}