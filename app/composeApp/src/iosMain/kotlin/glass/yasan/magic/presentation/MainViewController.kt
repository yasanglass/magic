package glass.yasan.magic.presentation

import androidx.compose.ui.window.ComposeUIViewController
import glass.yasan.magic.di.appModule
import glass.yasan.magic.feature.errors.domain.ErrorReporting
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin

@Suppress("Unused", "FunctionName")
fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin {
            modules(appModule)
        }
        getKoin().get<ErrorReporting>().init()
    }
) {
    App()
}
