package glass.yasan.magic.feature.answers.domain.repository

import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

public interface CustomAnswerPackRepository {

    public val answerPacks: Flow<ImmutableList<CustomAnswerPack>>

    public suspend fun insertAnswerPack(answerPack: CustomAnswerPack)

    public suspend fun removeAnswerPack(answerPack: CustomAnswerPack)

}
