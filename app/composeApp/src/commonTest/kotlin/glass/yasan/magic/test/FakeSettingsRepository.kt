package glass.yasan.magic.test

import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.feature.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class FakeSettingsRepository : SettingsRepository {

    private val _settings = MutableStateFlow(Settings.default)

    override val settings: Flow<Settings> = _settings

    val currentSettings: Settings
        get() = _settings.value

    override fun update(transform: Settings.() -> Settings) {
        _settings.update { current -> transform(current) }
    }

    fun setInitialSettings(settings: Settings) {
        _settings.value = settings
    }

}
