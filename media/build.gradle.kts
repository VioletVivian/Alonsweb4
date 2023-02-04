@Suppress("UnstableApiUsage")
plugins {
    id(libs.plugins.android.lib.get().pluginId)
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.ksp)
}

android {
    compileSdk =  libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk =  libs.versions.minSdk.get().toInt()
        targetSdk =  libs.versions.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardF