package glass.yasan.magic.data.settings

import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.magic.domain.model.Settings

class SettingsMapper {

    fun mapToSettings(themeStyleId: String): Settings {
        val themeStyle = ThemeStyle.fromId(themeStyleId)
        return Settings(themeStyle = themeStyle)
    }

    fun mapThemeStyleToId(themeStyle: ThemeStyle): String = themeStyle.id
}
