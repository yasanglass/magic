package glass.yasan.magic.widget

import glass.yasan.magic.domain.usecase.GetActiveAnswerPackUseCase
import glass.yasan.magic.domain.usecase.GetNewAnswerUseCase
import glass.yasan.magic.feature.answers.domain.model.BuiltInAnswer
import glass.yasan.magic.feature.answers.domain.model.BuiltInAnswerPack
import glass.yasan.magic.feature.answers.domain.model.CustomAnswer
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import glass.yasan.magic.feature.widget.WidgetAnswerSource
import glass.yasan.magic.feature.widget.WidgetAnswerSource.NewAnswer
import org.jetbrains.compose.resources.getString

class WidgetAnswerSourceImpl(
    private val getActiveAnswerPack: GetActiveAnswerPackUseCase,
    private val getNewAnswer: GetNewAnswerUseCase,
) : WidgetAnswerSource {

    override suspend fun resolveInitialPrompt(): String =
        when (val pack = getActiveAnswerPack()) {
            is BuiltInAnswerPack -> getString(pack.promptStringRes)
            is CustomAnswerPack -> pack.prompt
        }

    override suspend fun resolveNewAnswer(): NewAnswer {
        val answer = getNewAnswer()
        val text = when (answer) {
            is BuiltInAnswer -> getString(answer.textStringRes)
            is CustomAnswer -> answer.text
        }
        return NewAnswer(text = text, type = answer.type)
    }
}
