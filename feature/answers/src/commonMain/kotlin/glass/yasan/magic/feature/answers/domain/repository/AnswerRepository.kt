package glass.yasan.magic.feature.answers.domain.repository

import glass.yasan.magic.feature.answers.domain.model.BuiltInAnswerPack
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow

public interface AnswerRepository {

    public val builtInAnswerPacks: StateFlow<ImmutableList<BuiltInAnswerPack>>
    public val customAnswerPacks: StateFlow<ImmutableList<CustomAnswerPack>>

    public suspend fun insertAnswerPack(answerPack: CustomAnswerPack)

    public suspend fun removeAnswerPack(answerPack: CustomAnswerPack)

}
