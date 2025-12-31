<p align="center">
  <img src="./assets/banner.png" alt="Banner"/>
</p>

[![license](https://img.shields.io/github/license/yasanglass/magic.svg)](https://github.com/yasanglass/magic/blob/main/LICENSE)
[![crowdin](https://badges.crowdin.net/magic-8-ball/localized.svg)](https://crowdin.com/project/magic-8-ball)
[![android](https://img.shields.io/github/actions/workflow/status/yasanglass/magic/android.yml?label=android)](https://github.com/yasanglass/magic/actions/workflows/android.yml)
[![jvm](https://img.shields.io/github/actions/workflow/status/yasanglass/magic/jvm.yml?label=jvm)](https://github.com/yasanglass/magic/actions/workflows/jvm.yml)
[![ios](https://img.shields.io/github/actions/workflow/status/yasanglass/magic/ios.yml?label=ios)](https://github.com/yasanglass/magic/actions/workflows/ios.yml)
[![js](https://img.shields.io/github/actions/workflow/status/yasanglass/magic/js.yml?label=js)](https://github.com/yasanglass/magic/actions/workflows/js.yml)
[![wasm](https://img.shields.io/github/actions/workflow/status/yasanglass/magic/wasm.yml?label=wasm)](https://github.com/yasanglass/magic/actions/workflows/wasm.yml)
[![detekt](https://img.shields.io/github/actions/workflow/status/yasanglass/magic/detekt.yml?label=detekt)](https://github.com/yasanglass/magic/actions/workflows/detekt.yml)
[![snapshots](https://img.shields.io/github/actions/workflow/status/yasanglass/magic/snapshots.yml?label=snapshots)](https://github.com/yasanglass/magic/actions/workflows/snapshots.yml)

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