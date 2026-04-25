package glass.yasan.magic.presentation.route.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.component.ButtonText
import glass.yasan.kepko.component.PreferenceAppIdentity
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.persistence.PreviewPersistentKepkoTheme
import glass.yasan.kepko.resource.Icons
import glass.yasan.magic.BuildKonfig
import glass.yasan.magic.presentation.navigation.Route
import glass.yasan.magic.core.resources.Res
import glass.yasan.magic.core.resources.about
import glass.yasan.magic.core.resources.answer_packs
import glass.yasan.magic.core.resources.app_name
import glass.yasan.magic.core.resources.settings
import glass.yasan.magic.core.resources.style
import glass.yasan.magic.presentation.util.SystemBarColorsEffect
import glass.yasan.magic.util.PreviewWithTest
import glass.yasan.toolkit.about.presentation.compose.ToolkitDeveloperBanner
import glass.yasan.toolkit.compose.spacer.verticalSpacerItem
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsScreen(
    navController: NavHostController,
) {
    SystemBarColorsEffect()

    Scaffold(
        title = stringResource(Res.string.settings),
        onBackClick = { navController.navigateUp() },
    ) { contentPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = contentPadding,
        ) {
            verticalSpacerItem(height = 12.dp)
            answerPacksButtonItem { navController.navigate(Route.Settings.AnswerPacks) }
            styleButtonItem { navController.navigate(Route.Settings.Style) }
            aboutButtonItem { navController.navigate(Route.Settings.About) }
            appIdentityItem()
            developerBannerItem()
        }
    }
}

private fun LazyListScope.answerPacksButtonItem(onClick: () -> Unit) {
    item {
        ButtonText(
            text = stringResource(Res.string.answer_packs),
            leadingIcon = null,
            trailingIcon = Icons.chevronForward,
            onClick = onClick,
        )
    }
}

private fun LazyListScope.styleButtonItem(onClick: () -> Unit) {
    item {
        ButtonText(
            text = stringResource(Res.string.style),
            leadingIcon = null,
            trailingIcon = Icons.chevronForward,
            onClick = onClick,
        )
    }
}

private fun LazyListScope.aboutButtonItem(onClick: () -> Unit) {
    item {
        ButtonText(
            text = stringResource(Res.string.about),
            leadingIcon = null,
            trailingIcon = Icons.chevronForward,
            onClick = onClick,
        )
    }
}

private fun LazyListScope.developerBannerItem() {
    item { ToolkitDeveloperBanner() }
}

private fun LazyListScope.appIdentityItem() {
    item {
        val versionName = if (LocalInspectionMode.current) {
            "6.0.0"
        } else {
            BuildKonfig.VERSION_NAME
        }
        val versionCode = if (LocalInspectionMode.current) {
            "600"
        } else {
            BuildKonfig.VERSION_CODE
        }

        PreferenceAppIdentity(
            title = stringResource(Res.string.app_name),
            versionName = versionName,
            extras = arrayOf(versionCode),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@PreviewWithTest
@Composable
internal fun SettingsScreenPreview() {
    PreviewPersistentKepkoTheme {
        SettingsScreen(
            navController = rememberNavController(),
        )
    }
}
