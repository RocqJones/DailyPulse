plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            // Android dependencies here (only used in androidMain module)
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
        }

        iosMain.dependencies {
            // iOS dependencies here (only used in iosMain module)
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
