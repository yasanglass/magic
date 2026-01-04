package glass.yasan.magic.feature.answers.domain.usecase

import glass.yasan.magic.feature.answers.data.local.DefaultAnswerPacks
import glass.yasan.magic.feature.answers.domain.model.Answer

public class GetNewAnswerUseCase {
    public operator fun invoke(): Answer = DefaultAnswerPacks.magicEightBallAnswers.random()
}
