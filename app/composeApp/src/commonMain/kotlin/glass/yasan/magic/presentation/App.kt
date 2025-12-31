package glass.yasan.magic.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.component.Surface
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.feature.settings.domain.repository.SettingsRepository
import glass.yasan.magic.presentation.navigation.NavigationHost
import org.koin.compose.koinInject

@Composable
fun App() {
    val navController = rememberNavController()
    val settingsRepository: SettingsRepository = koinInject()
    val settings by settingsRepository.settings.collectAsStateWithLifecycle(initialValue = null)

    App(
        navController = navController,
        settings = settings,
        updateSettings = settingsRepository::update,
    )
}

@Composable
private fun App(
    navController: NavHostController,
    settings: Settings?,
    updateSettings: (Settings.() -> Settings) -> Unit,
) {
    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxSize(),
    ) {
        AnimatedVisibility(
            visible = settings != null,
            enter = fadeIn(),
            modifier = Modifier.fillMaxSize(),
        ) {
            settings?.let { nonNullSettings ->
                KepkoTheme(
                    style = nonNullSettings.theme.asKepkoThemeStyle(),
                ) {
                    NavigationHost(
                        navController = navController,
                        settings = nonNullSettings,
                        updateSettings = updateSettings,
                    )
                }
            }
        }
    }
}
