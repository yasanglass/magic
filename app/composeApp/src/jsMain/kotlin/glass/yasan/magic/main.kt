package glass.yasan.magic

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import glass.yasan.magic.di.appModule
import glass.yasan.magic.presentation.App
import kotlinx.browser.document
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule)
    }

    ComposeViewport(document.getElementById("ComposeTarget")!!) {
        App()
    }
}
