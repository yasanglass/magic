# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Magic 8 Ball - A Kotlin Multiplatform application targeting Android, iOS, JVM Desktop, JavaScript, and WebAssembly using Jetpack Compose.

## Build Commands

```bash
# Build
./gradlew assembleDebug                        # Android debug APK
./gradlew jvmJar                               # JVM/Desktop
./gradlew wasmJsBrowserDevelopmentRun          # Run Wasm in browser
./gradlew linkDebugFrameworkIosSimulatorArm64  # iOS framework

# Test
./gradlew testDebugUnitTest     # Android tests
./gradlew jvmTest               # JVM tests
./gradlew wasmJsTest            # WebAssembly tests
./gradlew iosSimulatorArm64Test # iOS tests

# Code Quality
./gradlew detekt                # Static analysis (strict: 0 issues allowed)
./gradlew lintDebug             # Android lint

# Coverage
./gradlew koverHtmlReport       # HTML coverage report
```

## Architecture

Clean Architecture with MVI pattern:

```
app/composeApp/src/commonMain/kotlin/glass/yasan/magic/
├── data/local/          # Local data sources (DefaultAnswerPacks)
├── di/                  # Koin dependency injection modules
├── domain/model/        # Domain models (Answer)
├── presentation/
│   ├── App.kt           # Root composable with KepkoTheme
│   ├── navigation/      # NavHost and Route definitions
│   └── route/magic/     # MagicScreen + MagicViewModel (MVI pattern)
```

Platform source sets: `androidMain`, `iosMain`, `jvmMain`, `jsMain`, `wasmJsMain`, `webMain` (shared js/wasm)

## Key Conventions

**UI Components**: Use `glass.yasan.kepko` components instead of direct Material3 imports. Detekt enforces this - the following Material3 imports are forbidden:
- Button, Checkbox, Icon, Text, Switch, RadioButton, Slider
- HorizontalDivider, VerticalDivider, Surface
- LinearProgressIndicator, CircularProgressIndicator

**Theming**: Always use `KepkoTheme` for colors and styling.

**Dependencies**: Managed via `gradle/libs.versions.toml` version catalog.

## Platform Expect/Actual Pattern

For platform-specific implementations, use `expect`/`actual` declarations. Example structure:
- `commonMain/.../SystemBarColorsEffect.kt` - expect declaration
- `androidMain/.../SystemBarColorsEffect.android.kt` - Android actual
- `iosMain/.../SystemBarColorsEffect.ios.kt` - iOS actual
- `webMain/.../SystemBarColorsEffect.web.kt` - shared web actual (js + wasmJs)
