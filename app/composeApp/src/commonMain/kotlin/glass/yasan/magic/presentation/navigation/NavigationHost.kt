package glass.yasan.magic.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.magic.presentation.route.about.AboutScreen
import glass.yasan.magic.presentation.route.magic.MagicScreen
import glass.yasan.magic.presentation.route.settings.SettingsScreen

@Composable
internal fun NavigationHost(
    navController: NavHostController,
    themeStyle: ThemeStyle,
    onThemeStyleChange: (ThemeStyle) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Magic,
    ) {
        composable<Route.Magic> {
            MagicScreen(navController, themeStyle)
        }
        composable<Route.Settings> {
            SettingsScreen(navController, themeStyle, onThemeStyleChange)
        }
        composable<Route.About> {
            AboutScreen(navController)
        }
    }
}
