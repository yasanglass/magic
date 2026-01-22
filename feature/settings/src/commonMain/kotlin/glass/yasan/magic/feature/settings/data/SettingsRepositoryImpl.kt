package glass.yasan.magic.feature.settings.data

import co.touchlab.kermit.Logger
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.feature.settings.domain.repository.SettingsRepository
import glass.yasan.toolkit.core.coroutines.ApplicationScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
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
        const val KEY_ANSWER_PACK = "answer_pack"
    }

    private val logger = Logger.withTag("SettingsRepositoryImpl")
    private val updateMutex = Mutex()

    private val theme: Flow<String?> = observableSettings.getStringOrNullFlow(KEY_THEME)
        .catch { e ->
            logger.e(e) { "Failed to read theme setting" }
            emit(null)
        }
    private val answerPackId: Flow<String?> = observableSettings.getStringOrNullFlow(KEY_ANSWER_PACK)
        .catch { e ->
            logger.e(e) { "Failed to read answer pack setting" }
            emit(null)
        }

    override val settings: Flow<Settings> = combine(
        theme,
        answerPackId,
    ) { theme, answerPackId ->
        mapper.map(
            theme = theme,
            answerPackId = answerPackId,
        )
    }

    override fun update(transform: Settings.() -> Settings) {
        applicationScope.launch {
            updateMutex.withLock {
                val current = settings.first()
                val new = transform(current)

                if (current.theme.id != new.theme.id) {
                    observableSettings.putString(KEY_THEME, new.theme.id)
                }
                if (current.activeAnswerPackId != new.activeAnswerPackId) {
                    observableSettings.putString(KEY_ANSWER_PACK, new.activeAnswerPackId)
                }
            }
        }
    }

}
