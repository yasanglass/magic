package glass.yasan.magic.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.magic.presentation.navigation.NavigationHost

@Composable
fun App() {
    val navController = rememberNavController()

    KepkoTheme {
        NavigationHost(
            navController = navController,
        )
    }
}
