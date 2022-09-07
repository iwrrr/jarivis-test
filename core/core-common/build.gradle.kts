plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AndroidProjectConfig.compileSdk

    defaultConfig {
        minSdk = AndroidProjectConfig.minSdk
        targetSdk = AndroidProjectConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://imdb-api.com/\"")
        buildConfigField("String", "APPLICATION_ID", "\"${AndroidProjectConfig.applicationId}\"")
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
    }
}

dependencies {
    api(Libraries.androidxCoreKtx)
    api(Libraries.androidxAppcompat)

    testApi(Libraries.junit)
    androidTestApi(Libraries.androidJunit)
    androidTestApi(Libraries.espressoCore)

    api(Libraries.lifecycleViewModelKtx)
    api(Libraries.lifecycleLiveDataKtx)
    api(Libraries.lifecycleRuntimeKtx)

    api(Libraries.retrofitConverterGson)
    api(Libraries.retrofit2)
    api(Libraries.httpLogging)
    api(Libraries.gson)

    api(Libraries.coroutineCore)
    api(Libraries.coroutineAndroid)

    api(Libraries.jetpackDatastore)

    api(Libraries.hiltAndroid)
    kapt(Libraries.hiltAndroidCompiler)
    kapt(Libraries.hiltCompiler)

    api(project(":core:core-ui"))
}