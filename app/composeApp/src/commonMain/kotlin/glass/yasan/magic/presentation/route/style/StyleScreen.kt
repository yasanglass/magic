package glass.yasan.magic.presentation.route.style

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.component.PreferenceRadioGroup
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.magic.core.resources.Res
import glass.yasan.magic.core.resources.style
import glass.yasan.magic.core.resources.theme
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.presentation.util.SystemBarColorsEffect
import glass.yasan.magic.util.PreviewWithTest
import glass.yasan.toolkit.compose.spacer.verticalSpacerItem
import org.jetbrains.compose.resources.stringResource

@Composable
fun StyleScreen(
    navController: NavHostController,
    settings: Settings,
    updateSettings: (Settings.() -> Settings) -> Unit,
) {
    SystemBarColorsEffect(
        statusBarColor = KepkoTheme.colors.foreground,
        navigationBarColor = KepkoTheme.colors.midground,
        darkIcons = !settings.theme.asKepkoThemeStyle().isDark,
    )

    Scaffold(
        title = stringResource(Res.string.style),
        onBackClick = { navController.navigateUp() },
    ) { contentPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = contentPadding,
        ) {
            verticalSpacerItem(height = 12.dp)
            themePreferenceItem(settings, updateSettings)
        }
    }
}

private fun LazyListScope.themePreferenceItem(
    settings: Settings,
    updateSettings: (Settings.() -> Settings) -> Unit,
) {
    item {
        val items = remember {
            Settings.Theme.entries.map { theme ->
                PreferenceRadioGroupItem(
                    id = theme.id,
                    title = theme.title,
                )
            }
        }

        PreferenceRadioGroup(
            title = stringResource(Res.string.theme),
            items = items,
            selectedId = settings.theme.id,
            onSelectId = { id ->
                updateSettings {
                    copy(
                        theme = Settings.Theme.fromId(id) ?: error("Unexpected theme selected: $id")
                    )
                }
            },
        )
    }
}

@PreviewWithTest
@Composable
internal fun StyleScreenPreview() {
    KepkoTheme {
        StyleScreen(
            navController = rememberNavController(),
            settings = Settings.default,
            updateSettings = {}
        )
    }
}
