// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")
plugins {
    kotlin("android") version  libs.versions.kotlin.asProvider().get() apply false
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.android.lib) apply false
}

buildscript {

    repositories {
        google()
        mav