<p align="center">
  <img src="./assets/banner.png" alt="Banner"/>
</p>

<p align="center">
  <a href="https://crowdin.com/project/magic-8-ball"><img alt="Crowdin" src="https://badges.crowdin.net/magic-8-ball/localized.svg"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/detekt.yml"><img alt="Detekt" src="https://github.com/yasanglass/magic/actions/workflows/detekt.yml/badge.svg"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/android.yml"><img alt="Android" src="https://github.com/yasanglass/magic/actions/workflows/android.yml/badge.svg"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/jvm.yml"><img alt="JVM" src="https://github.com/yasanglass/magic/actions/workflows/jvm.yml/badge.svg"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/ios.yml"><img alt="iOS" src="https://github.com/yasanglass/magic/actions/workflows/ios.yml/badge.svg"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/js.yml"><img alt="JS" src="https://github.com/yasanglass/magic/actions/workflows/js.yml/badge.svg"/></a>
  <a href="https://github.com/yasanglass/magic/actions/workflows/wasm.yml"><img alt="Wasm" src="https://github.com/yasanglass/magic/actions/workflows/wasm.yml/badge.svg"/></a>
</p>

### Modules

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  :app["app"]
  :feature["feature"]
  subgraph :app
    :app:composeApp["composeApp"]
    :app:composeApp["composeApp"]
  end
  subgraph :feature
    :feature:settings["settings"]
    :feature:settings["settings"]
  end

  :app:composeApp --> :feature:settings
  : --> :app
  : --> :feature
  : --> :app:composeApp
  : --> :feature:settings

classDef android-application fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
classDef kotlin-multiplatform fill:#C792EA,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :app:composeApp android-application
class :feature:settings kotlin-multiplatform
class : unknown
class :app unknown
class :feature unknown

```