package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import glass.yasan.magic.feature.answers.domain.repository.AnswerRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow

internal class AnswerRepositoryImpl(
    private val localDataSource: AnswerPackLocalDataSource,
) : AnswerRepository {

    override val answerPacks: StateFlow<ImmutableList<AnswerPack>> =
        localDataSource.answerPacks

    override suspend fun insertAnswerPack(answerPack: AnswerPack) {
        localDataSource.insertAnswerPack(answerPack)
    }

    override suspend fun removeAnswerPack(answerPack: AnswerPack) {
        localDataSource.removeAnswerPack(answerPack)
    }
}
