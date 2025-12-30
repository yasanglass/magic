package glass.yasan.magic.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
actual fun SystemBarColorsEffect(
    statusBarColor: Color,
    navigationBarColor: Color,
) {
    // No-op on Web
}
