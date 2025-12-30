package glass.yasan.magic.feature.settings.domain.repository

import glass.yasan.magic.feature.settings.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val settings: Flow<Settings>

    fun update(transform: Settings.() -> Settings)

}
