package glass.yasan.magic.presentation

import androidx.compose.runtime.Composable
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.persistence.PersistentKepkoTheme
import glass.yasan.magic.presentation.navigation.NavigationHost

@OptIn(ExperimentalKepkoApi::class)
@Composable
fun App() {
    PersistentKepkoTheme {
        NavigationHost()
    }
}
