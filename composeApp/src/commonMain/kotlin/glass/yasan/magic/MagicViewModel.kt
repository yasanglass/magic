package glass.yasan.magic

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class AnswerType {
    NEUTRAL,
    AFFIRMATIVE,
    NON_COMMITTAL,
    NEGATIVE
}

data class Answer(
    val text: String,
    val type: AnswerType
)

class MagicViewModel : ViewModel() {

    private val answers = listOf(
        // Affirmative
        Answer("It is certain.", AnswerType.AFFIRMATIVE),
        Answer("It is decidedly so.", AnswerType.AFFIRMATIVE),
        Answer("Without a doubt.", AnswerType.AFFIRMATIVE),
        Answer("Yes - definitely.", AnswerType.AFFIRMATIVE),
        Answer("You may rely on it.", AnswerType.AFFIRMATIVE),
        Answer("As I see it, yes.", AnswerType.AFFIRMATIVE),
        Answer("Most likely.", AnswerType.AFFIRMATIVE),
        Answer("Outlook good.", AnswerType.AFFIRMATIVE),
        Answer("Yes.", AnswerType.AFFIRMATIVE),
        Answer("Signs point to yes.", AnswerType.AFFIRMATIVE),
        // Non-committal
        Answer("Reply hazy, try again.", AnswerType.NON_COMMITTAL),
        Answer("Ask again later.", AnswerType.NON_COMMITTAL),
        Answer("Better not tell you now.", AnswerType.NON_COMMITTAL),
        Answer("Cannot predict now.", AnswerType.NON_COMMITTAL),
        Answer("Concentrate and ask again.", AnswerType.NON_COMMITTAL),
        // Negative
        Answer("Don't count on it.", AnswerType.NEGATIVE),
        Answer("My reply is no.", AnswerType.NEGATIVE),
        Answer("My sources say no.", AnswerType.NEGATIVE),
        Answer("Outlook not so good.", AnswerType.NEGATIVE),
        Answer("Very doubtful.", AnswerType.NEGATIVE)
    )

    private val _currentAnswer = MutableStateFlow(Answer("Ask.", AnswerType.NEUTRAL))
    val currentAnswer: StateFlow<Answer> = _currentAnswer.asStateFlow()

    fun getNewAnswer() {
        _currentAnswer.value = answers.random()
    }
}
