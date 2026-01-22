package glass.yasan.magic.feature.answers.data.repository

import glass.yasan.magic.feature.answers.data.local.CustomAnswerPackLocalDataSource
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import glass.yasan.magic.feature.answers.domain.repository.CustomAnswerPackRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

internal class CustomAnswerPackRepositoryImpl(
    private val localDataSource: CustomAnswerPackLocalDataSource,
) : CustomAnswerPackRepository {

    override val answerPacks: Flow<ImmutableList<CustomAnswerPack>> =
        localDataSource.answerPacks

    override suspend fun insertAnswerPack(answerPack: CustomAnswerPack) {
        localDataSource.insertAnswerPack(answerPack)
    }

    override suspend fun removeAnswerPack(answerPack: CustomAnswerPack) {
        localDataSource.removeAnswerPack(answerPack)
    }
}
