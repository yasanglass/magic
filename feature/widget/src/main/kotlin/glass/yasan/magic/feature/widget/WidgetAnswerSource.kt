package glass.yasan.magic.feature.widget

import glass.yasan.magic.feature.answers.domain.model.Answer

interface WidgetAnswerSource {

    suspend fun resolveInitialPrompt(): String

    suspend fun resolveNewAnswer(): NewAnswer

    data class NewAnswer(
        val text: String,
        val type: Answer.Type,
    )
}
