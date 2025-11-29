package glass.yasan.magic

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import glass.yasan.magic.di.appModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(appModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Magic",
    ) {
        App()
    }
}