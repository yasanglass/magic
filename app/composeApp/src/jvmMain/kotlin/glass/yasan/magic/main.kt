package glass.yasan.magic

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import glass.yasan.magic.di.appModule
import glass.yasan.magic.presentation.App
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(appModule)
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Magic",
        ) {
            App()
        }
    }
}
