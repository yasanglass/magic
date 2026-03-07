package glass.yasan.magic.presentation.route.privacy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import glass.yasan.kepko.component.PreferenceSwitch
import glass.yasan.kepko.component.Scaffold
import glass.yasan.magic.core.resources.Res
import glass.yasan.magic.core.resources.answer_packs
import glass.yasan.magic.core.resources.privacy_error_reporting_description
import glass.yasan.magic.core.resources.privacy_error_reporting_title
import glass.yasan.magic.core.resources.privacy_product_analytics_description
import glass.yasan.magic.core.resources.privacy_product_analytics_title
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.toolkit.compose.spacer.verticalSpacerItem
import org.jetbrains.compose.resources.stringResource

@Composable
fun PrivacySettingsScreen(
    navController: NavHostController,
    settings: Settings,
    updateSettings: (Settings.() -> Settings) -> Unit,
) {
    Scaffold(
        title = stringResource(Res.string.answer_packs),
        onBackClick = navController::navigateUp,
    ) { contentPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = contentPadding,
        ) {
            verticalSpacerItem(height = 12.dp)
            item {
                PreferenceSwitch(
                    title = stringResource(Res.string.privacy_product_analytics_title),
                    description = stringResource(Res.string.privacy_product_analytics_description),
                    checked = settings.analyticsEnabled,
                    onCheckedChange = { updateSettings { copy(analyticsEnabled = it) } },
                )
            }
            item {
                PreferenceSwitch(
                    title = stringResource(Res.string.privacy_error_reporting_title),
                    description = stringResource(Res.string.privacy_error_reporting_description),
                    checked = settings.errorReportingEnabled,
                    onCheckedChange = { updateSettings { copy(errorReportingEnabled = it) } },
                )
            }
        }
    }
}
