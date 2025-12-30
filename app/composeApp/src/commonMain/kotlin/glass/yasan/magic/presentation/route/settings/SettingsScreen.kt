package glass.yasan.magic.presentation.route.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import glass.yasan.kepko.component.ButtonText
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.component.ic_chevron_forward
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.magic.presentation.navigation.Route
import glass.yasan.magic.presentation.util.SystemBarColorsEffect
import glass.yasan.magic.resources.Res
import glass.yasan.magic.resources.about
import glass.yasan.magic.resources.settings
import glass.yasan.toolkit.compose.spacer.verticalSpacerItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import glass.yasan.kepko.component.Res as KepkoComponentRes

@Composable
fun SettingsScreen(
    navController: NavHostController,
) {
    SystemBarColorsEffect(
        statusBarColor = KepkoTheme.colors.foreground,
        navigationBarColor = KepkoTheme.colors.midground,
    )

    Scaffold(
        title = stringResource(Res.string.settings),
        onBackClick = { navController.navigateUp() },
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding),
        ) {
            verticalSpacerItem(height = 16.dp)
            item {
                ButtonText(
                    text = stringResource(Res.string.about),
                    onClick = { navController.navigate(Route.About) },
                    leadingIcon = null,
                    trailingIcon = painterResource(KepkoComponentRes.drawable.ic_chevron_forward),
                )
            }
        }
    }
}
