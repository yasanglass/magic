package glass.yasan.magic.feature.settings.domain.model

import androidx.compose.runtime.Composable
import glass.yasan.kepko.foundation.theme.ThemeStyle

data class Settings(
    val theme: Theme,
) {

    companion object {
        val default: Settings = Settings(
            theme = Theme.SYSTEM,
        )
    }

    enum class Theme(
        val id: String,
    ) {
        SYSTEM(
            id = "system",
        ),
        LIGHT(
            id = ThemeStyle.LIGHT.id,
        ),
        DARK(
            id = ThemeStyle.DARK.id,
        ),
        BLACK(
            id = ThemeStyle.BLACK.id,
        ),
        SOLARIZED_LIGHT(
            id = ThemeStyle.SOLARIZED_LIGHT.id,
        ),
        SOLARIZED_DARK(
            id = ThemeStyle.SOLARIZED_DARK.id,
        ),
        ;

        companion object {
            fun fromId(id: String?): Theme? = entries.find { it.id == id }
        }

        @Composable
        fun asKepkoThemeStyle(): ThemeStyle = ThemeStyle.fromId(id) ?: ThemeStyle.fromSystemDarkTheme()

    }

}
