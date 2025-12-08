package glass.yasan.magic

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import glass.yasan.magic.di.appModule
import glass.yasan.magic.ui.App
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule)
    }

    ComposeViewport {
        App()
    }
}