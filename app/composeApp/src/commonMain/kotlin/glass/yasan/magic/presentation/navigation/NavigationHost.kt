package glass.yasan.magic.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.magic.presentation.route.about.AboutScreen
import glass.yasan.magic.presentation.route.settings.SettingsScreen
import glass.yasan.magic.presentation.route.magic.MagicScreen

@Composable
internal fun NavigationHost(
    navController: NavHostController,
    themeStyle: MutableState<ThemeStyle>,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Magic,
    ) {
        composable<Route.Magic> {
            MagicScreen(navController, themeStyle)
        }
        composable<Route.Settings> {
            SettingsScreen(navController, themeStyle)
        }
        composable<Route.About> {
            AboutScreen(navController)
        }
    }
}
