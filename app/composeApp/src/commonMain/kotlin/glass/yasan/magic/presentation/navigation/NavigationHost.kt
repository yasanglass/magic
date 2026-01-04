package glass.yasan.magic.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.presentation.route.about.AboutScreen
import glass.yasan.magic.presentation.route.answerpacks.AnswerPacksScreen
import glass.yasan.magic.presentation.route.magic.MagicScreen
import glass.yasan.magic.presentation.route.settings.SettingsScreen

@Composable
internal fun NavigationHost(
    navController: NavHostController,
    settings: Settings,
    updateSettings: (Settings.() -> Settings) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Magic,
    ) {
        composable<Route.Magic> {
            MagicScreen(navController, settings)
        }
        composable<Route.Settings> {
            SettingsScreen(navController, settings, updateSettings)
        }
        composable<Route.About> {
            AboutScreen(navController)
        }
        composable<Route.AnswerPacks> {
            AnswerPacksScreen(navController, settings, updateSettings)
        }
    }
}
