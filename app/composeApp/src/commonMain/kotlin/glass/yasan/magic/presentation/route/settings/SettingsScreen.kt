package glass.yasan.magic.presentation.route.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import glass.yasan.kepko.component.ButtonText
import glass.yasan.kepko.component.PreferenceAppIdentity
import glass.yasan.kepko.component.PreferenceRadioGroup
import glass.yasan.kepko.component.PreferenceRadioGroupItem
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.magic.BuildKonfig
import glass.yasan.kepko.resource.ic_chevron_forward
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.presentation.navigation.Route
import glass.yasan.magic.presentation.util.SystemBarColorsEffect
import glass.yasan.magic.resources.Res
import glass.yasan.magic.resources.about
import glass.yasan.magic.resources.app_name
import glass.yasan.magic.resources.settings
import glass.yasan.magic.resources.theme
import glass.yasan.toolkit.about.presentation.compose.ToolkitDeveloperBanner
import glass.yasan.toolkit.compose.spacer.verticalSpacerItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import glass.yasan.kepko.resource.Res as KepkoRes

@Composable
fun SettingsScreen(
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
        title = stringResource(Res.string.settings),
        onBackClick = { navController.navigateUp() },
    ) { contentPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(contentPadding),
        ) {
            verticalSpacerItem(height = 16.dp)
            themePreferenceItem(settings, updateSettings)
            aboutButtonItem { navController.navigate(Route.About) }
            appIdentityItem()
            developerBannerItem()
        }
    }
}

private fun LazyListScope.aboutButtonItem(onClick: () -> Unit) {
    item {
        ButtonText(
            text = stringResource(Res.string.about),
            leadingIcon = null,
            trailingIcon = painterResource(KepkoRes.drawable.ic_chevron_forward),
            onClick = onClick,
        )
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

private fun LazyListScope.developerBannerItem() {
    item { ToolkitDeveloperBanner() }
}

private fun LazyListScope.appIdentityItem() {
    item {
        PreferenceAppIdentity(
            title = stringResource(Res.string.app_name),
            versionName = BuildKonfig.VERSION_NAME,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
