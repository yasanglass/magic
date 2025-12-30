package glass.yasan.magic.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.magic.domain.model.Settings
import glass.yasan.magic.domain.repository.SettingsRepository
import glass.yasan.magic.presentation.navigation.NavigationHost
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun App() {
    val navController = rememberNavController()
    val settingsRepository: SettingsRepository = koinInject()
    val scope = rememberCoroutineScope()

    val isSystemInDarkTheme = isSystemInDarkTheme()
    val settings by settingsRepository.settings.collectAsStateWithLifecycle(
        initialValue = Settings(themeStyle = null),
    )
    val themeStyle = settings.themeStyle ?: ThemeStyle.fromDarkMode(isDark = isSystemInDarkTheme)

    KepkoTheme(
        style = themeStyle,
    ) {
        NavigationHost(
            navController = navController,
            themeStyle = themeStyle,
            onThemeStyleChange = { newThemeStyle ->
                scope.launch { settingsRepository.updateThemeStyle(newThemeStyle) }
            },
        )
    }
}
