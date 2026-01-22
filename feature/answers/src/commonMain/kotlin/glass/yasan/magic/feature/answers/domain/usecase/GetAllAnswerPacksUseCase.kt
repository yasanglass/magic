package glass.yasan.magic.feature.answers.domain.usecase

import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import glass.yasan.magic.feature.answers.domain.provider.BuiltInAnswerPackProvider
import glass.yasan.magic.feature.answers.domain.repository.CustomAnswerPackRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public class GetAllAnswerPacksUseCase(
    private val builtInAnswerPackProvider: BuiltInAnswerPackProvider,
    private val customAnswerPackRepository: CustomAnswerPackRepository,
) {

    public operator fun invoke(): Flow<ImmutableList<AnswerPack<*>>> =
        customAnswerPackRepository.answerPacks.map { customPacks ->
            (builtInAnswerPackProvider.getAll() + customPacks).toImmutableList()
        }

}
