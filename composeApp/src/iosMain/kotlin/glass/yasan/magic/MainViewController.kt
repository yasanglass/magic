package glass.yasan.magic

import androidx.compose.ui.window.ComposeUIViewController
import glass.yasan.magic.di.appModule
import org.koin.core.context.startKoin

@Suppress("Unused", "FunctionName")
fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin {
            modules(appModule)
        }
    }
) {
    App()
}
