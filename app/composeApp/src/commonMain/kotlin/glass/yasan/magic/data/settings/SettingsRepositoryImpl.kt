package glass.yasan.magic.data.settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getStringFlow
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.magic.domain.model.Settings
import glass.yasan.magic.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalSettingsApi::class)
class SettingsRepositoryImpl(
    private val observableSettings: ObservableSettings,
    private val mapper: SettingsMapper,
) : SettingsRepository {

    override val settings: Flow<Settings> = observableSettings
        .getStringFlow(KEY_THEME_STYLE, DEFAULT_THEME_STYLE)
        .map { mapper.mapToSettings(it) }

    override suspend fun updateThemeStyle(themeStyle: ThemeStyle) {
        observableSettings.putString(KEY_THEME_STYLE, mapper.mapThemeStyleToId(themeStyle))
    }

    private companion object {
        const val KEY_THEME_STYLE = "theme_style"
        const val DEFAULT_THEME_STYLE = ""
    }
}
