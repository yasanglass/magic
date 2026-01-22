package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.Answer
import glass.yasan.magic.feature.answers.domain.model.CustomAnswer
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import glass.yasan.magic.feature.answers.data.local.db.CustomAnswerEntity
import glass.yasan.magic.feature.answers.data.local.db.CustomAnswerPackEntity
import kotlinx.collections.immutable.toImmutableList

internal class AnswerPackMapper {

    fun map(
        packEntity: CustomAnswerPackEntity,
        answerEntities: List<CustomAnswerEntity>,
    ): CustomAnswerPack = CustomAnswerPack(
        id = packEntity.id,
        name = packEntity.name,
        prompt = packEntity.prompt,
        answers = answerEntities.map(::map).toImmutableList(),
    )

    private fun map(answerEntity: CustomAnswerEntity): CustomAnswer = CustomAnswer(
        id = answerEntity.id,
        text = answerEntity.text,
        type = Answer.Type.fromIdOrDefault(answerEntity.type),
    )

}
