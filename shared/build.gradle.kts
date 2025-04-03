plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("co.touchlab.skie") version "0.8.3"
    /*
    SKIE (pronounced as sky) is a special Kotlin native compiler plugin that brings back support for
    some of these features by modifying the Xcode Framework produced by the Kotlin compiler.
    */
    kotlin("plugin.serialization") version "1.9.23"
    alias(libs.plugins.sqlDelight)
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
            //implementation(libs.kotlinx.datetime)
            implementation(libs.koin.core)

            // sqlDelight
            implementation(libs.sql.coroutines.extensions)

            //implementation(libs.stately.common)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            // Android dependencies here (only used in androidMain module)
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
            implementation(libs.ktor.client.android)

            // sqlDelight
            implementation(libs.sql.android.driver)
        }

        iosMain.dependencies {
            // iOS dependencies here (only used in iosMain module)
            implementation(libs.ktor.client.darwin)
            // sqlDelight
            implementation(libs.sql.native.driver)
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

sqldelight {
    databases {
        create("DailyPulseDatabase") {
            packageName.set("com.rocqjones.dailypulse.db")
        }
    }
}