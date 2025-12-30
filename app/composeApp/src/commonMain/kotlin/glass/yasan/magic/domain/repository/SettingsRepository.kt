package glass.yasan.magic.domain.repository

import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.magic.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settings: Flow<Settings>
    suspend fun updateThemeStyle(themeStyle: ThemeStyle)
}
