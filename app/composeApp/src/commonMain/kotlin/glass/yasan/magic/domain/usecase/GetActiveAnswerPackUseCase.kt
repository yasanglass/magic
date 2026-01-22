package glass.yasan.magic.domain.usecase

import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import glass.yasan.magic.feature.answers.domain.usecase.GetAllAnswerPacksUseCase
import glass.yasan.magic.feature.answers.domain.usecase.GetDefaultAnswerPackUseCase
import glass.yasan.magic.feature.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.first

class GetActiveAnswerPackUseCase(
    private val settingsRepository: SettingsRepository,
    private val getAllAnswerPacks: GetAllAnswerPacksUseCase,
    private val getDefaultAnswerPack: GetDefaultAnswerPackUseCase,
) {

    suspend operator fun invoke(): AnswerPack<*> {
        val settings = settingsRepository.settings.first()
        val allAnswerPacks = getAllAnswerPacks().first()

        val activeAnswerPackId = settings.activeAnswerPackId

        val activeAnswerPack = allAnswerPacks.firstOrNull { it.id == activeAnswerPackId }
        if (activeAnswerPack != null) return activeAnswerPack

        return getDefaultAnswerPack()
    }

}
