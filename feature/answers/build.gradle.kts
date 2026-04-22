import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.sqldelight)
}

kotlin {
    explicitApi()

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            group("nonWeb") {
                withAndroidTarget()
                withJvm()
                withApple()
            }
            group("web") {
                withJs()
                withWasmJs()
            }
        }
    }

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js { browser() }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:resources"))

                implementation(libs.androidx.lifecycle.viewmodel.compose)
                implementation(libs.androidx.navigation.compose)
                implementation(libs.bundles.glass.yasan.kepko)
                implementation(libs.glass.yasan.toolkit.compose)
                implementation(libs.jetbrains.compose.components.resources)
                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.ui)
                implementation(libs.jetbrains.kotlinx.collections.immutable)
                implementation(libs.koin.compose)
                implementation(libs.koin.core)
                implementation(libs.russhwolf.multiplatform.settings)
                implementation(libs.russhwolf.multiplatform.settings.coroutines)
            }
        }

        val nonWebMain by getting {
            dependencies {
                implementation(libs.sqldelight.runtime)
                implementation(libs.sqldelight.coroutines.extensions)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.sqldelight.native.driver)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.sqldelight.android.driver)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.sqldelight.sqlite.driver)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.jetbrains.kotlin.test)
                implementation(libs.jetbrains.kotlinx.coroutines.test)
                implementation(libs.sqldelight.sqlite.driver)
            }
        }
    }
}


sqldelight {
    databases {
        create("MagicDatabase") {
            packageName.set("glass.yasan.magic.feature.answers.data.local.db")
            srcDirs("src/nonWebMain/sqldelight")
        }
    }
    linkSqlite = true
}

android {
    namespace = "glass.yasan.magic.feature.answers"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
