package glass.yasan.magic.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.persistence.PersistentKepkoTheme
import glass.yasan.magic.presentation.navigation.NavigationHost

@Composable
fun App() {
    PersistentKepkoTheme {
        NavigationHost(
            navController = rememberNavController(),
        )
    }
}
