package glass.yasan.magic.presentation.route.about

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import glass.yasan.kepko.component.Scaffold
import glass.yasan.magic.core.resources.Res
import glass.yasan.magic.core.resources.about
import glass.yasan.toolkit.about.presentation.compose.ToolkitDeveloperContent
import glass.yasan.toolkit.compose.spacer.verticalSpacerItem
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutScreen(
    navController: NavHostController,
) {
    Scaffold(
        title = stringResource(Res.string.about),
        onBackClick = { navController.navigateUp() },
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding),
        ) {
            item { ToolkitDeveloperContent() }
            verticalSpacerItem(height = 32.dp)
        }
    }
}
