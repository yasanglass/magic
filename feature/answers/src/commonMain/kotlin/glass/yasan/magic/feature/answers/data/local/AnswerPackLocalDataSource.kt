package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow

internal interface AnswerPackLocalDataSource {

    val answerPacks: StateFlow<ImmutableList<AnswerPack>>

    suspend fun insertAnswerPack(answerPack: AnswerPack)

    suspend fun removeAnswerPack(answerPack: AnswerPack)

}
