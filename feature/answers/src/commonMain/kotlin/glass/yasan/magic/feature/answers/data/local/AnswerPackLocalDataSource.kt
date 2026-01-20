package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.BuiltInAnswerPack
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow

internal interface AnswerPackLocalDataSource {

    val builtInAnswerPacks: StateFlow<ImmutableList<BuiltInAnswerPack>>
    val customAnswerPacks: StateFlow<ImmutableList<CustomAnswerPack>>

    suspend fun insertAnswerPack(answerPack: CustomAnswerPack)

    suspend fun removeAnswerPack(answerPack: CustomAnswerPack)

}
