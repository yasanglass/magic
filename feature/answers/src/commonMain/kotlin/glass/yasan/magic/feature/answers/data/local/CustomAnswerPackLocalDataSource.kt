package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

internal interface CustomAnswerPackLocalDataSource {

    val answerPacks: Flow<ImmutableList<CustomAnswerPack>>

    suspend fun insertAnswerPack(answerPack: CustomAnswerPack)

    suspend fun removeAnswerPack(answerPack: CustomAnswerPack)

}
