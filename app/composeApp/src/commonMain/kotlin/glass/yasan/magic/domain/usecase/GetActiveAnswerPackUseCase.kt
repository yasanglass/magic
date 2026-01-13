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

    suspend operator fun invoke(): AnswerPack {
        val settings = settingsRepository.settings.first()
        val answerPack = answerRepository.answerPacks.value.firstOrNull {
            it.id == settings.activeAnswerPackId
        } ?: DefaultAnswerPacks.default

        return answerPack
    }

}
