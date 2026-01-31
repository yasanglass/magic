package glass.yasan.magic.feature.settings.domain.model

import androidx.compose.runtime.Composable
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.magic.core.resources.Res
import glass.yasan.magic.core.resources.theme_style_system
import org.jetbrains.compose.resources.stringResource

data class Settings(
    val theme: Theme,
    val activeAnswerPackId: String,
    val analyticsEnabled: Boolean,
    val errorReportingEnabled: Boolean,
) {

    companion object {
        val default: Settings = Settings(
            theme = Theme.SYSTEM,
            activeAnswerPackId = "magic-8-ball",
            analyticsEnabled = false,
            errorReportingEnabled = false,
        )
    }

    enum class Theme(
        val id: String,
        val title: @Composable () -> String,
    ) {
        SYSTEM(
            id = "system",
            title = { stringResource(Res.string.theme_style_system) }
        ),
        LIGHT(
            id = ThemeStyle.LIGHT.id,
            title = ThemeStyle.LIGHT.title,
        ),
        DARK(
            id = ThemeStyle.DARK.id,
            title = ThemeStyle.DARK.title,
        ),
        BLACK(
            id = ThemeStyle.BLACK.id,
            title = ThemeStyle.BLACK.title,
        ),
        SOLARIZED_LIGHT(
            id = ThemeStyle.SOLARIZED_LIGHT.id,
            title = ThemeStyle.SOLARIZED_LIGHT.title,
        ),
        SOLARIZED_DARK(
            id = ThemeStyle.SOLARIZED_DARK.id,
            title = ThemeStyle.SOLARIZED_DARK.title,
        ),
        ;

        companion object {
            fun fromId(id: String?): Theme? = entries.find { it.id == id }
        }

        @Composable
        fun asKepkoThemeStyle(): ThemeStyle = ThemeStyle.fromIdOrSystemDarkTheme(id)

    }

}
