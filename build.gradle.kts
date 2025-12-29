plugins {
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.multiplatform) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.jetbrains.compose.hotreload) apply false
    alias(libs.plugins.arturbosch.detekt) apply true
    alias(libs.plugins.jetbrains.kotlinx.kover)
    alias(libs.plugins.iurysouza.modulegraph)
    alias(libs.plugins.roborazzi) apply false
}

dependencies {
    subprojects.forEach { subproject ->
        kover(subproject)
    }
}

kover {
    reports {
        total {
            binary {
                file = layout.buildDirectory.file("reports/kover/report.ic")
            }
        }
    }
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom(
        "$projectDir/detekt/detekt.yml",
        "$projectDir/detekt/detekt-concrete.yml",
    )
    autoCorrect = true
}

allprojects {
    group = "glass.yasan.toolkit"
    version = "1.7.1"
}

fun Project.configureDetekt() {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        buildUponDefaultConfig = true
        config.setFrom(
            "$rootDir/detekt/detekt.yml",
            "$rootDir/detekt/detekt-kepko.yml",
        )
        source.from(
            "src/androidMain/kotlin",
            "src/androidTest/kotlin",
            "src/commonMain/kotlin",
            "src/commonTest/kotlin",
            "src/iosMain/kotlin",
            "src/iosTest/kotlin",
            "src/jvmAndroidMain/kotlin",
            "src/jvmMain/kotlin",
            "src/jvmTest/kotlin"
        )
    }
}

fun Project.configureKover() {
    apply(plugin = "org.jetbrains.kotlinx.kover")
}

subprojects {
    configureDetekt()
    configureKover()
}

moduleGraphConfig {
    readmePath.set("${rootDir}/README.md")
    heading.set("### Modules")
    showFullPath.set(false)
    setStyleByModuleType.set(true)
    nestingEnabled.set(true)
}
