package glass.yasan.magic.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import glass.yasan.magic.presentation.route.SettingsScreen
import glass.yasan.magic.presentation.route.magic.MagicScreen

@Composable
internal fun NavigationHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Magic,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            )
        },
    ) {
        composable<Route.Magic> {
            MagicScreen(navController)
        }
        composable<Route.Settings> {
            SettingsScreen(navController)
        }
    }
}
