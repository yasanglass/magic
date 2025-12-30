package glass.yasan.magic.feature.settings.data

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.feature.settings.domain.repository.SettingsRepository
import glass.yasan.toolkit.core.coroutines.ApplicationScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@OptIn(ExperimentalSettingsApi::class)
internal class SettingsRepositoryImpl(
    private val applicationScope: ApplicationScope,
    private val observableSettings: ObservableSettings,
    private val mapper: SettingsMapper,
) : SettingsRepository {

    private companion object {
        const val KEY_THEME = "theme"
    }

    private val updateMutex = Mutex()

    private val theme: Flow<String?> = observableSettings.getStringOrNullFlow(KEY_THEME)

    override val settings: Flow<Settings> = theme.map(mapper::map)

    override fun update(transform: Settings.() -> Settings) {
        applicationScope.launch {
            updateMutex.withLock {
                val current = settings.first()
                val new = transform(current)

                if (current.theme.id != new.theme.id) {
                    observableSettings.putString(KEY_THEME, new.theme.id)
                }
            }
        }
    }

}
