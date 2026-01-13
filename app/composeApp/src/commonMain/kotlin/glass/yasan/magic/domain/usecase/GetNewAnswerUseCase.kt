package glass.yasan.magic.domain.usecase

import glass.yasan.magic.feature.answers.domain.model.Answer

class GetNewAnswerUseCase(
    private val getActiveAnswerPack: GetActiveAnswerPackUseCase,
) {

    suspend operator fun invoke(): Answer = getActiveAnswerPack().answers.random()

}
