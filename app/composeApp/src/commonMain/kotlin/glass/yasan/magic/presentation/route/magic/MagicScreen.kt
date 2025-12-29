package glass.yasan.magic.presentation.route.magic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import glass.yasan.kepko.component.Text
import glass.yasan.magic.resources.Res
import glass.yasan.magic.resources.app_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun MagicScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(stringResource(Res.string.app_name))
    }
}
