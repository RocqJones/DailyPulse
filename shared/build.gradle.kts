plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("co.touchlab.skie") version "0.7.1"
    /*
    SKIE (pronounced as sky) is a special Kotlin native compiler plugin that brings back support for
    some of these features by modifying the Xcode Framework produced by the Kotlin compiler.
    */
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // put your multiplatform dependencies here
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            // Android dependencies here (only used in androidMain module)
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
            implementation(libs.ktor.client.android)
        }

        iosMain.dependencies {
            // iOS dependencies here (only used in iosMain module)
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.rocqjones.dailypulse"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
