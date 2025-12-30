package glass.yasan.magic.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Suppress("UNUSED_PARAMETER")
@Composable
actual fun SystemBarColorsEffect(
    statusBarColor: Color,
    navigationBarColor: Color,
    darkIcons: Boolean,
) {
    // No-op on Desktop
}
