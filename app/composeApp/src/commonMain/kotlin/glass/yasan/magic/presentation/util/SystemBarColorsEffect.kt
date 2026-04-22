package glass.yasan.magic.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.persistence.LocalKepkoColorPalette

@Composable
expect fun SystemBarColorsEffect(
    statusBarColor: Color = KepkoTheme.colors.foreground,
    navigationBarColor: Color = KepkoTheme.colors.midground,
    darkIcons: Boolean = LocalKepkoColorPalette.current.isDark.not(),
)
