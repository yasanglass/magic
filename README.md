<p align="center">
  <img src="./assets/banner.png" alt="Banner"/>
</p>

<p align="center">
  <a href="https://github.com/yasanglass/magic/blob/main/LICENSE"><img src="https://img.shields.io/github/license/yasanglass/magic.svg" alt="license"/></a>
  <a href="https://kotlinlang.org"><img src="https://img.shields.io/badge/kotlin-2.3.0-purple.svg?style=flat" alt="kotlin"/></a>
  <a href="https://crowdin.com/project/magic-8-ball"><img src="https://badges.crowdin.net/magic-8-ball/localized.svg" alt="crowdin"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/android.yml"><img src="https://img.shields.io/github/actions/workflow/status/yasanglass/magic/android.yml?label=android" alt="android"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/jvm.yml"><img src="https://img.shields.io/github/actions/workflow/status/yasanglass/magic/jvm.yml?label=jvm" alt="jvm"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/ios.yml"><img src="https://img.shields.io/github/actions/workflow/status/yasanglass/magic/ios.yml?label=ios" alt="ios"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/js.yml"><img src="https://img.shields.io/github/actions/workflow/status/yasanglass/magic/js.yml?label=js" alt="js"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/wasm.yml"><img src="https://img.shields.io/github/actions/workflow/status/yasanglass/magic/wasm.yml?label=wasm" alt="wasm"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/detekt.yml"><img src="https://img.shields.io/github/actions/workflow/status/yasanglass/magic/detekt.yml?label=detekt" alt="detekt"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/snapshots.yml"><img src="https://img.shields.io/github/actions/workflow/status/yasanglass/magic/snapshots.yml?label=snapshots" alt="snapshots"/></a>
</p>

<p align="center">
A simple randomizer app built with Compose Multiplatform
<p align="center">

### Modules

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  subgraph :app
    :app:composeApp["composeApp"]
  end
  subgraph :feature
    :feature:settings["settings"]
  end

  :app:composeApp --> :feature:settings

classDef android-application fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
classDef kotlin-multiplatform fill:#C792EA,stroke:#fff,stroke-width:2px,color:#fff;
class :app:composeApp android-application
class :feature:settings kotlin-multiplatform

classDef focus fill:#769566,stroke:#fff,stroke-width:2px,color:#fff;
class :app:composeApp focus
```