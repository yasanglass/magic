package glass.yasan.magic.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.presentation.route.about.AboutScreen
import glass.yasan.magic.presentation.route.answerpacks.AnswerPacksScreen
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPackScreen
import glass.yasan.magic.presentation.route.magic.MagicScreen
import glass.yasan.magic.presentation.route.settings.SettingsScreen
import glass.yasan.magic.presentation.route.style.StyleScreen

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
            SettingsScreen(navController, settings)
        }
        composable<Route.Settings.About> {
            AboutScreen(navController)
        }
        composable<Route.Settings.Style> {
            StyleScreen(navController, settings, updateSettings)
        }
        composable<Route.Settings.AnswerPacks> {
            AnswerPacksScreen(navController)
        }
        composable<Route.Settings.AnswerPacks.Edit> { backStackEntry ->
            val route: Route.Settings.AnswerPacks.Edit = backStackEntry.toRoute()

            EditAnswerPackScreen(
                navController = navController,
                answerPackId = route.answerPackId,
            )
        }
    }
}
