package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.BuiltInAnswerPack
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import glass.yasan.magic.feature.answers.domain.repository.AnswerRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow

internal class AnswerRepositoryImpl(
    private val localDataSource: AnswerPackLocalDataSource,
) : AnswerRepository {

    override val builtInAnswerPacks: StateFlow<ImmutableList<BuiltInAnswerPack>> =
        localDataSource.builtInAnswerPacks

    override val customAnswerPacks: StateFlow<ImmutableList<CustomAnswerPack>> =
        localDataSource.customAnswerPacks

    override suspend fun insertAnswerPack(answerPack: CustomAnswerPack) {
        localDataSource.insertAnswerPack(answerPack)
    }

    override suspend fun removeAnswerPack(answerPack: CustomAnswerPack) {
        localDataSource.removeAnswerPack(answerPack)
    }
}
