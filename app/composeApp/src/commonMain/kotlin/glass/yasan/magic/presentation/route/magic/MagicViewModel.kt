package glass.yasan.magic.presentation.route.magic

import glass.yasan.magic.feature.answers.domain.model.Answer
import glass.yasan.magic.feature.answers.domain.usecase.GetNewAnswerUseCase
import glass.yasan.magic.presentation.route.magic.MagicViewModel.Action.NavigateToSettings
import glass.yasan.toolkit.compose.viewmodel.ToolkitViewModel
import glass.yasan.toolkit.compose.viewmodel.ViewAction
import glass.yasan.toolkit.compose.viewmodel.ViewEvent
import glass.yasan.toolkit.compose.viewmodel.ViewState

class MagicViewModel(
    private val getNewAnswer: GetNewAnswerUseCase,
) : ToolkitViewModel<
        MagicViewModel.State,
        MagicViewModel.Event,
        MagicViewModel.Action,
        >() {

    data class State(
        val answer: Answer = Answer.empty,
    ) : ViewState

    interface Event : ViewEvent {

        data object Ask : Event
        data object OpenSettings : Event

    }

    interface Action : ViewAction {
        data object NavigateToSettings : Action
    }

    override fun defaultViewState(): State = State()

    override suspend fun onViewEvent(event: Event) {
        when (event) {
            is Event.Ask -> fetchNewAnswer()
            is Event.OpenSettings -> openSettings()
        }
    }

    private fun fetchNewAnswer() {
        updateViewState {
            copy(
                answer = getNewAnswer(),
            )
        }
    }

    private fun openSettings() {
        sendViewAction(NavigateToSettings)
    }

}
