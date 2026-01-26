# Keep ViewModel names for Koin dependency injection
-keepnames class * extends androidx.lifecycle.ViewModel

# Kotlin Serialization - keep @Serializable classes in this module
# (Library rules handle the serializer infrastructure)
-keepattributes RuntimeVisibleAnnotations
-keep,includedescriptorclasses class glass.yasan.magic.**$$serializer { *; }
-keepclassmembers class glass.yasan.magic.** {
    *** Companion;
}
-keepclasseswithmembers class glass.yasan.magic.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Navigation - keep argument data classes if using type-safe navigation
# -keep class glass.yasan.magic.navigation.** { *; }

# Suppress common warnings
-dontwarn org.slf4j.**
-dontwarn javax.annotation.**

# Sentry - keep classes for proper stack traces
-keep class io.sentry.** { *; }
-keepnames class io.sentry.** { *; }
-dontwarn io.sentry.**
