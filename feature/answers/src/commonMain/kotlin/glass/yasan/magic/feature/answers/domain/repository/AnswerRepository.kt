package glass.yasan.magic.feature.answers.domain.repository

import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow

public interface AnswerRepository {

    public val answerPacks: StateFlow<ImmutableList<AnswerPack>>

    public fun insertAnswerPack(answerPack: AnswerPack)

    public fun removeAnswerPack(answerPack: AnswerPack)
}
