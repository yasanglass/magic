pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "magic"
include(":app")
include(":app:composeApp")
include(":core:resources")
include(":feature:answers")
include(":feature:settings")
