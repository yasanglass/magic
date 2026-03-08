package glass.yasan.magic.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.persistence.LocalKepkoThemeStyle

@Composable
expect fun SystemBarColorsEffect(
    statusBarColor: Color = KepkoTheme.colors.foreground,
    navigationBarColor: Color = KepkoTheme.colors.midground,
    darkIcons: Boolean = LocalKepkoThemeStyle.current.isDark.not(),
)
