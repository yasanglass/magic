package glass.yasan.magic.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import glass.yasan.kepko.persistence.PersistentPreferenceThemeScreen
import glass.yasan.magic.presentation.route.about.AboutScreen
import glass.yasan.magic.presentation.route.answerpacks.AnswerPacksScreen
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPackScreen
import glass.yasan.magic.presentation.route.magic.MagicScreen
import glass.yasan.magic.presentation.route.settings.SettingsScreen

@Composable
internal fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Magic,
    ) {
        composable<Route.Magic> {
            MagicScreen(navController)
        }
        composable<Route.Settings> {
            SettingsScreen(navController)
        }
        composable<Route.Settings.About> {
            AboutScreen(navController)
        }
        composable<Route.Settings.Style> {
            PersistentPreferenceThemeScreen(
                onBackClick = navController::navigateUp,
            )
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
