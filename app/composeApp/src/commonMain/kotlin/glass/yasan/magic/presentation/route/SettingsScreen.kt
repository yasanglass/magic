package glass.yasan.magic.presentation.route

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import glass.yasan.kepko.component.Text

@Composable
fun SettingsScreen(
    navController: NavHostController,
) {
    Column(
        modifier = Modifier.safeContentPadding(),
    ) {
        Text(
            text = "Go Back",
            modifier = Modifier.clickable { navController.navigateUp() }
        )
        Text("Settings")
    }
}
