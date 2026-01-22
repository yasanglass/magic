package glass.yasan.magic.presentation.route.answerpacks

import androidx.lifecycle.viewModelScope
import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import glass.yasan.magic.feature.answers.domain.usecase.GetAllAnswerPacksUseCase
import glass.yasan.magic.feature.settings.domain.repository.SettingsRepository
import glass.yasan.toolkit.compose.viewmodel.ToolkitViewModel
import glass.yasan.toolkit.compose.viewmodel.ViewAction
import glass.yasan.toolkit.compose.viewmodel.ViewEvent
import glass.yasan.toolkit.compose.viewmodel.ViewState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class AnswerPacksViewModel(
    getAllAnswerPacks: GetAllAnswerPacksUseCase,
    private val settingsRepository: SettingsRepository,
) : ToolkitViewModel<
        AnswerPacksViewModel.State,
        AnswerPacksViewModel.Event,
        AnswerPacksViewModel.Action,
        >() {

    data class State(
        val answerPacks: ImmutableList<AnswerPack<*>> = persistentListOf(),
        val activeAnswerPackId: String? = null,
    ) : ViewState

    sealed interface Event : ViewEvent {
        data class OnSelectAnswerPackClick(
            val id: String,
        ) : Event

        data class OnEditAnswerPackClick(
            val id: String,
        ) : Event

        data object OnNewAnswerPackClick : Event
    }

    sealed interface Action : ViewAction {
        data class NavigateToEditAnswerPack(
            val id: String?,
        ) : Action
    }

    override fun defaultViewState(): State = State()

    override suspend fun onViewEvent(event: Event) {
        when (event) {
            is Event.OnSelectAnswerPackClick -> {
                settingsRepository.update { copy(activeAnswerPackId = event.id) }
            }

            is Event.OnEditAnswerPackClick -> {
                sendViewAction(Action.NavigateToEditAnswerPack(id = event.id))
            }

            is Event.OnNewAnswerPackClick -> {
                sendViewAction(Action.NavigateToEditAnswerPack(id = null))
            }
        }
    }

    init {
        getAllAnswerPacks()
            .onEach { answerPacks ->
                updateViewState { copy(answerPacks = answerPacks) }
            }
            .launchIn(viewModelScope)

        settingsRepository.settings
            .onEach { settings ->
                updateViewState { copy(activeAnswerPackId = settings.activeAnswerPackId) }
            }
            .launchIn(viewModelScope)
    }

}
