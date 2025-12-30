package glass.yasan.magic.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.magic.feature.settings.domain.repository.SettingsRepository
import glass.yasan.magic.presentation.navigation.NavigationHost
import org.koin.compose.koinInject

@Composable
fun App() {
    val navController = rememberNavController()
    val settingsRepository: SettingsRepository = koinInject()
    val settings by settingsRepository.settings.collectAsStateWithLifecycle(initialValue = null)

    settings?.let { nonNullSettings ->
        AnimatedVisibility(
            visible = settings != null,
            enter = fadeIn(),
        ) {
            KepkoTheme(
                style = nonNullSettings.theme.asKepkoThemeStyle(),
            ) {
                NavigationHost(
                    navController = navController,
                    settings = nonNullSettings,
                    updateSettings = settingsRepository::update,
                )
            }
        }
    }
}
