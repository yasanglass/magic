import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.jetbrains.compose.hotreload)
    alias(libs.plugins.roborazzi)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-sensitive-resolution")
    }

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
            }
        }
        binaries.executable()
    }

    js {
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
            }
        }
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        val webMain by creating {
            dependsOn(commonMain.get())
        }
        jsMain {
            dependsOn(webMain)
        }
        wasmJsMain {
            dependsOn(webMain)
        }
        val iosMain by creating {
            dependsOn(commonMain.get())
        }
        iosX64Main {
            dependsOn(iosMain)
        }
        iosArm64Main {
            dependsOn(iosMain)
        }
        iosSimulatorArm64Main {
            dependsOn(iosMain)
        }
        commonMain {
            dependencies {
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(compose.ui)

                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.lifecycle.viewmodel.compose)
                implementation(libs.androidx.navigation.compose)
                implementation(libs.glass.yasan.kepko.component)
                implementation(libs.glass.yasan.kepko.foundation)
                implementation(libs.glass.yasan.toolkit.about)
                implementation(libs.glass.yasan.toolkit.compose)
                implementation(libs.glass.yasan.toolkit.core)
                implementation(libs.glass.yasan.toolkit.koin)
                implementation(libs.jetbrains.kotlinx.serialization.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.koin.core)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.jetbrains.kotlin.test)
                implementation(libs.jetbrains.kotlinx.coroutines.test)
            }
        }
        jvmMain {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.jetbrains.kotlinx.coroutines.swing)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.sergio.sastre.composable.preview.scanner.jvm)
                implementation(libs.roborazzi.compose.desktop)
                implementation(kotlin("reflect"))
                implementation(kotlin("test"))
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTest)
            }
        }
        androidMain {
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.koin.android)
            }
        }
    }
}

android {
    namespace = "glass.yasan.magic"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = "me.yasan.magic_8_ball"
        minSdk = libs.versions.android.sdk.min.get().toInt()
        targetSdk = libs.versions.android.sdk.target.get().toInt()
        versionCode = 1
        versionName = "1.0.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.resources {
    packageOfResClass = "glass.yasan.magic.resources"
}

compose.desktop {
    application {
        mainClass = "glass.yasan.magic.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Magic"
            packageVersion = "1.0.0"
            macOS {
                iconFile.set(project.file("src/jvmMain/resources/app_icon.icns"))
                bundleID = "glass.yasan.magic"
                dockName = "Magic"
            }
            windows {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/app_icon.png"))
            }
            linux {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/app_icon.png"))
            }
        }
    }
}

tasks.register<Delete>("cleanSnapshots") {
    delete(fileTree("src/jvmTest/snapshots") { include("*.png") })
}

tasks.register("cleanRecordSnapshots") {
    description = "Deletes all snapshots and re-records them"
    dependsOn("cleanSnapshots")
    finalizedBy("recordRoborazziJvm")
}

tasks.register("verifySnapshots") {
    description = "Verifies snapshots match baselines"
    dependsOn("verifyRoborazziJvm")
}

val isCleanRecord = providers.gradleProperty("cleanRecordSnapshots").isPresent ||
    gradle.startParameter.taskNames.any { it.contains("cleanRecordSnapshots") }

tasks.named("jvmTest") {
    mustRunAfter("cleanSnapshots")
    if (isCleanRecord) {
        outputs.upToDateWhen { false }
    }
}
