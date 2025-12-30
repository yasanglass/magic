package glass.yasan.magic.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import glass.yasan.magic.presentation.route.about.AboutScreen
import glass.yasan.magic.presentation.route.settings.SettingsScreen
import glass.yasan.magic.presentation.route.magic.MagicScreen

@Composable
internal fun NavigationHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Magic,
        enterTransition = {
            slideInVertically(animationSpec = tween(300)) { it }
        },
        exitTransition = {
            fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutVertically(animationSpec = tween(300)) { it }
        },
    ) {
        composable<Route.Magic> {
            MagicScreen(navController)
        }
        composable<Route.Settings> {
            SettingsScreen(navController)
        }
        composable<Route.About> {
            AboutScreen(navController)
        }
    }
}
