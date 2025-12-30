package glass.yasan.magic.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
expect fun SystemBarColorsEffect(
    statusBarColor: Color,
    navigationBarColor: Color = statusBarColor,
    darkIcons: Boolean = true,
)
