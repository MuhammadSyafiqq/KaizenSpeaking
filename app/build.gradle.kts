plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.kaizenspeaking"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.kaizenspeaking"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "KaizenSpeaking-v1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }



}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp3.okhttp)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.glide)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.espresso.contrib)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.core.splashscreen)

    implementation(libs.mpandroidchart)
    implementation(libs.simple.gauge.android)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)

    implementation(libs.androidx.localbroadcastmanager)

    implementation(libs.androidx.security.crypto)

    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test:rules:1.5.0")

    testImplementation ("junit:junit:4.13.2")
    testImplementation ("org.mockito:mockito-core:4.11.0")
    testImplementation ("androidx.arch.core:core-testing:2.1.0") // Untuk LiveData testing

    // Testing
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("io.mockk:mockk:1.12.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")

    testImplementation ("org.robolectric:robolectric:4.9")
    testImplementation ("androidx.test:core-ktx:1.5.0")
    testImplementation ("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation ("androidx.test:rules:1.5.0")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.6.1")

    testImplementation ("io.mockk:mockk:1.13.2")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    testImplementation ("io.mockk:mockk:1.13.8")
    testImplementation ("io.mockk:mockk-android:1.13.8")
    testImplementation ("io.mockk:mockk-agent:1.13.8")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")

    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.1.0")

    testImplementation ("androidx.test.espresso.idling:idling-concurrent:3.4.0")

    // For testing
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("org.mockito:mockito-core:4.6.1")
    testImplementation ("org.mockito:mockito-inline:4.6.1")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")

    androidTestImplementation(libs.espresso.intents)

    testImplementation ("com.squareup.okhttp3:okhttp:4.12.0")


    // You might also need these related dependencies
    testImplementation ("junit:junit:4.13.2")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")





}