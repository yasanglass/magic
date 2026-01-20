package glass.yasan.magic.presentation.route.magic

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import glass.yasan.magic.domain.usecase.GetActiveAnswerPackUseCase
import glass.yasan.magic.feature.answers.domain.model.Answer
import glass.yasan.magic.domain.usecase.GetNewAnswerUseCase
import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import glass.yasan.magic.presentation.route.magic.MagicViewModel.Action.NavigateToSettings
import glass.yasan.toolkit.compose.viewmodel.ToolkitViewModel
import glass.yasan.toolkit.compose.viewmodel.ViewAction
import glass.yasan.toolkit.compose.viewmodel.ViewEvent
import glass.yasan.toolkit.compose.viewmodel.ViewState
import kotlinx.coroutines.launch

class MagicViewModel(
    private val getActiveAnswerPack: GetActiveAnswerPackUseCase,
    private val getNewAnswer: GetNewAnswerUseCase,
) : ToolkitViewModel<
        MagicViewModel.State,
        MagicViewModel.Event,
        MagicViewModel.Action,
        >() {

    data class State(
        val pack: AnswerPack<*>? = null,
        val answer: Answer? = null,
        val isLoading: Boolean = true,
        val showAdditionalContent: Boolean = true,
    ) : ViewState {

        val text: String?
            @Composable
            get() {
                if (isLoading) return null

                return answer?.resolveText() ?: pack?.resolvePrompt()
            }

    }

    interface Event : ViewEvent {

        data object RequestNewAnswer : Event
        data object OpenSettings : Event

    }

    interface Action : ViewAction {
        data object NavigateToSettings : Action
    }

    override fun defaultViewState(): State = State()

    init {
        initializeViewState()
    }

    override suspend fun onViewEvent(event: Event) {
        when (event) {
            is Event.RequestNewAnswer -> fetchNewAnswer()
            is Event.OpenSettings -> openSettings()
        }
    }

    private fun initializeViewState() {
        viewModelScope.launch {
            val activeAnswerPack = getActiveAnswerPack()

            updateViewState {
                copy(
                    pack = activeAnswerPack,
                    isLoading = false,
                )
            }
        }
    }

    private suspend fun fetchNewAnswer() {
        val newAnswer = getNewAnswer()

        updateViewState {
            copy(
                answer = newAnswer,
                isLoading = false,
                showAdditionalContent = false,
            )
        }
    }

    private fun openSettings() {
        sendViewAction(NavigateToSettings)
    }

}
