package glass.yasan.magic.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.magic.presentation.navigation.NavigationHost

@Composable
fun App() {
    val navController = rememberNavController()
    // TODO persist theme settings
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val themeStyle = rememberSaveable {
        mutableStateOf(ThemeStyle.fromDarkMode(isDark = isSystemInDarkTheme))
    }

    KepkoTheme(
        style = themeStyle.value,
    ) {
        NavigationHost(
            navController = navController,
            themeStyle = themeStyle,
        )
    }
}
