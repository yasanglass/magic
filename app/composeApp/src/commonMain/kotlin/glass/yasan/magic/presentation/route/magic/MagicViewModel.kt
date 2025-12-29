package glass.yasan.magic.presentation.route.magic

import glass.yasan.magic.data.local.DefaultAnswerPacks
import glass.yasan.magic.domain.model.Answer
import glass.yasan.toolkit.compose.viewmodel.ToolkitViewModel
import glass.yasan.toolkit.compose.viewmodel.ViewAction
import glass.yasan.toolkit.compose.viewmodel.ViewEvent
import glass.yasan.toolkit.compose.viewmodel.ViewState

class MagicViewModel : ToolkitViewModel<
        MagicViewModel.State,
        MagicViewModel.Event,
        MagicViewModel.Action,
        >() {

    data class State(
        val answer: Answer = Answer.empty,
    ) : ViewState

    interface Event : ViewEvent {

        data object OnClick : Event

    }

    data object Action : ViewAction

    override fun defaultViewState(): State = State()

    override suspend fun onViewEvent(event: Event) {
        when (event) {
            is Event.OnClick -> fetchNewAnswer()
        }
    }

    private fun fetchNewAnswer() {
        updateViewState {
            copy(
                answer = DefaultAnswerPacks.magicEightBallAnswers.random(),
            )
        }
    }

}
