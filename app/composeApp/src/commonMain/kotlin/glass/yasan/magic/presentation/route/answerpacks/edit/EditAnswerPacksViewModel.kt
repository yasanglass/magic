package glass.yasan.magic.presentation.route.answerpacks.edit

import androidx.lifecycle.viewModelScope
import glass.yasan.magic.feature.answers.data.local.DefaultAnswerPacks
import glass.yasan.magic.feature.answers.domain.model.Answer
import glass.yasan.magic.feature.answers.domain.model.CustomAnswer
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import glass.yasan.magic.feature.answers.domain.repository.AnswerRepository
import glass.yasan.magic.feature.settings.domain.repository.SettingsRepository
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.State.Error
import kotlinx.coroutines.launch
import glass.yasan.toolkit.compose.viewmodel.ToolkitViewModel
import glass.yasan.toolkit.compose.viewmodel.ViewAction
import glass.yasan.toolkit.compose.viewmodel.ViewEvent
import glass.yasan.toolkit.compose.viewmodel.ViewState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
internal class EditAnswerPacksViewModel(
    private val answerPackId: String?,
    private val answerRepository: AnswerRepository,
    private val settingsRepository: SettingsRepository,
) : ToolkitViewModel<
        EditAnswerPacksViewModel.State,
        EditAnswerPacksViewModel.Event,
        EditAnswerPacksViewModel.Action,
        >() {

    private val operationMutex = Mutex()

    data class State(
        val isLoading: Boolean = true,
        val name: String = "",
        val prompt: String = "",
        val answers: ImmutableList<CustomAnswer> = persistentListOf(
            CustomAnswer(),
            CustomAnswer(),
        ),
        val errors: ImmutableSet<Error> = persistentSetOf(),
    ) : ViewState {

        sealed interface Error {
            data object NameRequired : Error
            data object PromptRequired : Error
            data class AnswerTextEmpty(val answerId: String) : Error
        }

    }

    sealed interface Event : ViewEvent {

        data class OnNameValueChange(
            val newValue: String,
        ) : Event

        data class OnPromptValueChange(
            val newValue: String,
        ) : Event

        data class OnAnswerTextValueChange(
            val answerId: String,
            val newValue: String,
        ) : Event

        data class OnAnswerDeleteClick(
            val answerId: String,
        ) : Event

        data class OnAnswerTypeSelect(
            val answerId: String,
            val newType: Answer.Type,
        ) : Event

        data object OnNewAnswerClick : Event
        data object OnSaveAnswerPackClick : Event
        data object OnDeleteAnswerPackClick : Event

    }

    sealed interface Action : ViewAction {
        data object NavigateBack : Action
    }

    init {
        val pack = answerPackId?.let { id ->
            answerRepository.customAnswerPacks.value.find { it.id == id }
        }

        if (answerPackId != null && pack == null) {
            logger.e { "Could not find an answer pack for the given id: $answerPackId" }
        }

        if (pack != null) {
            updateViewState {
                copy(
                    isLoading = false,
                    name = pack.name,
                    prompt = pack.prompt,
                    answers = pack.answers,
                )
            }
        } else {
            updateViewState { copy(isLoading = false) }
        }
    }

    override fun defaultViewState(): State = State()

    override suspend fun onViewEvent(event: Event) {
        when (event) {
            is Event.OnNameValueChange -> onNameValueChange(event.newValue)
            is Event.OnPromptValueChange -> onPromptValueChange(event.newValue)
            is Event.OnNewAnswerClick -> onNewAnswerClick()
            is Event.OnAnswerTextValueChange -> onAnswerTextValueChange(event.answerId, event.newValue)
            is Event.OnAnswerDeleteClick -> onAnswerDeleteClick(event.answerId)
            is Event.OnAnswerTypeSelect -> onAnswerTypeSelect(event.answerId, event.newType)
            is Event.OnDeleteAnswerPackClick -> onDeleteAnswerPackClick()
            is Event.OnSaveAnswerPackClick -> onSaveAnswerPackClick()
        }
    }

    private fun onNameValueChange(newValue: String) {
        updateViewState {
            copy(
                name = newValue,
                errors = errors.filterNot { it is Error.NameRequired }.toImmutableSet(),
            )
        }
    }

    private fun onPromptValueChange(newValue: String) {
        updateViewState {
            copy(
                prompt = newValue,
                errors = errors.filterNot { it is Error.PromptRequired }.toImmutableSet(),
            )
        }
    }

    private fun onNewAnswerClick() {
        updateViewState {
            copy(
                answers = (answers + CustomAnswer()).toImmutableList(),
            )
        }
    }

    private fun onAnswerTextValueChange(answerId: String, newValue: String) {
        updateViewState {
            copy(
                answers = answers
                    .map { if (it.id == answerId) it.copy(text = newValue) else it }.toImmutableList(),
                errors = errors
                    .filterNot { it is Error.AnswerTextEmpty && it.answerId == answerId }.toImmutableSet(),
            )
        }
    }

    private fun onAnswerDeleteClick(answerId: String) {
        updateViewState {
            copy(
                answers = answers.filter { it.id != answerId }.toImmutableList(),
                errors = errors.filterNot {
                    it is Error.AnswerTextEmpty && it.answerId == answerId
                }.toImmutableSet(),
            )
        }
    }

    private fun onAnswerTypeSelect(answerId: String, type: Answer.Type) {
        updateViewState {
            copy(
                answers = answers.map { if (it.id == answerId) it.copy(type = type) else it }.toImmutableList(),
            )
        }
    }

    private fun onSaveAnswerPackClick() {
        viewModelScope.launch {
            if (!operationMutex.tryLock()) return@launch
            try {
                val state = viewState.value
                updateViewState { copy(isLoading = true) }

                val errors = getValidationErrors(state)
                if (errors.isNotEmpty()) {
                    updateViewState { copy(isLoading = false, errors = errors) }
                    return@launch
                }

                val pack = state.asCustomAnswerPack(answerPackId)
                answerRepository.insertAnswerPack(pack)
                setActiveAnswerPack(pack.id)

                sendViewAction(NavigateBack)
            } finally {
                operationMutex.unlock()
            }
        }
    }

    private fun onDeleteAnswerPackClick() {
        viewModelScope.launch {
            if (!operationMutex.tryLock()) return@launch
            try {
                val pack = answerPackId?.let { id ->
                    answerRepository.customAnswerPacks.value.find { it.id == id }
                }

                if (pack == null) {
                    logger.e { "Could not find an answer pack for the given id: $answerPackId" }
                    return@launch
                }

                updateViewState { copy(isLoading = true) }

                answerRepository.removeAnswerPack(pack)
                if (settingsRepository.settings.first().activeAnswerPackId == pack.id) {
                    setActiveAnswerPack(DefaultAnswerPacks.default.id)
                }

                sendViewAction(NavigateBack)
            } finally {
                operationMutex.unlock()
            }
        }
    }

    private fun setActiveAnswerPack(packId: String) {
        settingsRepository.update {
            copy(activeAnswerPackId = packId)
        }
    }

    private fun getValidationErrors(state: State): ImmutableSet<Error> = buildSet {
        if (state.name.isBlank()) add(Error.NameRequired)
        if (state.prompt.isBlank()) add(Error.PromptRequired)
        state.answers
            .filter { it.text.isBlank() }
            .forEach { add(Error.AnswerTextEmpty(answerId = it.id)) }
    }.toImmutableSet()

    private fun State.asCustomAnswerPack(
        id: String? = null,
    ): CustomAnswerPack = CustomAnswerPack(
        id = id ?: Uuid.random().toString(),
        name = name,
        prompt = prompt,
        answers = answers,
    )

}
