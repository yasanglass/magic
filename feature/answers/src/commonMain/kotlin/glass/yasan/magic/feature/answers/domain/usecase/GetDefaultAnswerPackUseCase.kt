package glass.yasan.magic.feature.answers.domain.usecase

import glass.yasan.magic.feature.answers.domain.model.BuiltInAnswerPack
import glass.yasan.magic.feature.answers.domain.provider.BuiltInAnswerPackProvider

public class GetDefaultAnswerPackUseCase(
    private val builtInAnswerPackProvider: BuiltInAnswerPackProvider,
) {

    public operator fun invoke(): BuiltInAnswerPack = builtInAnswerPackProvider.getDefault()

}
