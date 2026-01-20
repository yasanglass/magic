package glass.yasan.magic.domain.usecase

import glass.yasan.magic.feature.answers.data.local.DefaultAnswerPacks
import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import glass.yasan.magic.feature.answers.domain.repository.AnswerRepository
import glass.yasan.magic.feature.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.first

class GetActiveAnswerPackUseCase(
    private val settingsRepository: SettingsRepository,
    private val answerRepository: AnswerRepository,
) {

    suspend operator fun invoke(): AnswerPack<*> {
        val settings = settingsRepository.settings.first()
        val activeId = settings.activeAnswerPackId

        val builtIn = answerRepository.builtInAnswerPacks.value.firstOrNull { it.id == activeId }
        if (builtIn != null) return builtIn

        val custom = answerRepository.customAnswerPacks.value.firstOrNull { it.id == activeId }
        if (custom != null) return custom

        return DefaultAnswerPacks.default
    }

}
