package glass.yasan.magic.ui.magic

import androidx.lifecycle.ViewModel
import glass.yasan.magic.domain.model.Answer
import glass.yasan.magic.domain.model.Answer.Type
import glass.yasan.toolkit.compose.viewmodel.ToolkitViewModel
import glass.yasan.toolkit.compose.viewmodel.ViewAction
import glass.yasan.toolkit.compose.viewmodel.ViewEvent
import glass.yasan.toolkit.compose.viewmodel.ViewState

class MagicViewModel : ToolkitViewModel<
        MagicViewModel.State,
        MagicViewModel.Event,
        MagicViewModel.Action
        >() {

    data class State(
        val answer: Answer = Answer("Ask.", Type.NEUTRAL)
    ) : ViewState

    interface Event : ViewEvent {
        data object AskQuestion : Event
    }

    data object Action : ViewAction

    override fun defaultViewState(): State = State()

    override suspend fun onViewEvent(event: Event) {
        when (event) {
            Event.AskQuestion -> {
                onAskQuestion()
            }
        }
    }

    private val answers = listOf(
        // Affirmative
        Answer("It is certain.", Type.AFFIRMATIVE),
        Answer("It is decidedly so.", Type.AFFIRMATIVE),
        Answer("Without a doubt.", Type.AFFIRMATIVE),
        Answer("Yes - definitely.", Type.AFFIRMATIVE),
        Answer("You may rely on it.", Type.AFFIRMATIVE),
        Answer("As I see it, yes.", Type.AFFIRMATIVE),
        Answer("Most likely.", Type.AFFIRMATIVE),
        Answer("Outlook good.", Type.AFFIRMATIVE),
        Answer("Yes.", Type.AFFIRMATIVE),
        Answer("Signs point to yes.", Type.AFFIRMATIVE),
        // Non-committal
        Answer("Reply hazy, try again.", Type.NON_COMMITTAL),
        Answer("Ask again later.", Type.NON_COMMITTAL),
        Answer("Better not tell you now.", Type.NON_COMMITTAL),
        Answer("Cannot predict now.", Type.NON_COMMITTAL),
        Answer("Concentrate and ask again.", Type.NON_COMMITTAL),
        // Negative
        Answer("Don't count on it.", Type.NEGATIVE),
        Answer("My reply is no.", Type.NEGATIVE),
        Answer("My sources say no.", Type.NEGATIVE),
        Answer("Outlook not so good.", Type.NEGATIVE),
        Answer("Very doubtful.", Type.NEGATIVE)
    )

    private fun onAskQuestion() {
        updateViewState {
            copy(
                answer = answers.random(),
            )
        }
    }

}
