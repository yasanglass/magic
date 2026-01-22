package glass.yasan.magic.presentation.route.answerpacks.edit

import glass.yasan.magic.feature.answers.data.provider.BuiltInAnswerPackProviderImpl
import glass.yasan.magic.feature.answers.domain.model.Answer
import glass.yasan.magic.feature.answers.domain.model.CustomAnswer
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.Action
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.Event.OnAnswerDeleteClick
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.Event.OnAnswerTextValueChange
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.Event.OnAnswerTypeSelect
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.Event.OnDeleteAnswerPackClick
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.Event.OnNameValueChange
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.Event.OnNewAnswerClick
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.Event.OnPromptValueChange
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.Event.OnSaveAnswerPackClick
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.State
import glass.yasan.magic.test.FakeCustomAnswerPackRepository
import glass.yasan.magic.test.FakeSettingsRepository
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalUuidApi::class)
internal class EditAnswerPacksViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val builtInAnswerPackProvider = BuiltInAnswerPackProviderImpl()
    private lateinit var customAnswerPackRepository: FakeCustomAnswerPackRepository
    private lateinit var settingsRepository: FakeSettingsRepository

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        customAnswerPackRepository = FakeCustomAnswerPackRepository()
        settingsRepository = FakeSettingsRepository()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel(answerPackId: String? = null): EditAnswerPacksViewModel = EditAnswerPacksViewModel(
        answerPackId = answerPackId,
        builtInAnswerPackProvider = builtInAnswerPackProvider,
        customAnswerPackRepository = customAnswerPackRepository,
        settingsRepository = settingsRepository,
    )

    @Test
    fun whenCreatingNewPack_thenHasDefaultValues() = runTest {
        // when
        val viewModel = createViewModel()
        advanceUntilIdle()

        // then
        val state = viewModel.viewState.value
        assertFalse(state.isLoading)
        assertEquals("", state.name)
        assertEquals("", state.prompt)
        assertEquals(2, state.answers.size)
        assertTrue(state.errors.isEmpty())
    }

    @Test
    fun givenValidData_whenSaveClicked_thenInsertsPackAndNavigatesBack() = runTest {
        // given
        val viewModel = createViewModel()
        advanceUntilIdle()

        val name = "Test Pack"
        val prompt = "Ask a question"
        viewModel.onViewEvent(OnNameValueChange(name))
        viewModel.onViewEvent(OnPromptValueChange(prompt))
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[0].id,
                newValue = "Yes",
            )
        )
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[1].id,
                newValue = "No",
            )
        )
        advanceUntilIdle()

        // when
        viewModel.onViewEvent(OnSaveAnswerPackClick)
        advanceUntilIdle()

        // then
        val packs = customAnswerPackRepository.currentPacks
        assertEquals(1, packs.size)
        assertEquals(name, packs[0].name)
        assertEquals(prompt, packs[0].prompt)

        val action = viewModel.viewAction.first()
        assertEquals(Action.NavigateBack, action)
    }

    @Test
    fun givenEmptyName_whenSaveClicked_thenShowsNameRequiredError() = runTest {
        // given
        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.onViewEvent(OnPromptValueChange("Ask a question"))
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[0].id,
                newValue = "Yes",
            )
        )
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[1].id,
                newValue = "No",
            )
        )
        advanceUntilIdle()

        // when
        viewModel.onViewEvent(OnSaveAnswerPackClick)
        advanceUntilIdle()

        // then
        val state = viewModel.viewState.value
        assertTrue(state.errors.any { it is State.Error.NameRequired })
        assertTrue(customAnswerPackRepository.currentPacks.isEmpty())
    }

    @Test
    fun givenEmptyPrompt_whenSaveClicked_thenShowsPromptRequiredError() = runTest {
        // given
        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.onViewEvent(OnNameValueChange("Test Pack"))
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[0].id,
                newValue = "Yes",
            )
        )
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[1].id,
                newValue = "No",
            )
        )
        advanceUntilIdle()

        // when
        viewModel.onViewEvent(OnSaveAnswerPackClick)
        advanceUntilIdle()

        // then
        val state = viewModel.viewState.value
        assertTrue(state.errors.any { it is State.Error.PromptRequired })
        assertTrue(customAnswerPackRepository.currentPacks.isEmpty())
    }

    @Test
    fun givenEmptyAnswerText_whenSaveClicked_thenShowsAnswerTextEmptyError() = runTest {
        // given
        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.onViewEvent(OnNameValueChange("Test Pack"))
        viewModel.onViewEvent(OnPromptValueChange("Ask a question"))
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[0].id,
                newValue = "Yes",
            )
        )
        advanceUntilIdle()

        // when
        viewModel.onViewEvent(OnSaveAnswerPackClick)
        advanceUntilIdle()

        // then
        val state = viewModel.viewState.value
        assertTrue(state.errors.any { it is State.Error.AnswerTextEmpty })
        assertTrue(customAnswerPackRepository.currentPacks.isEmpty())
    }

    @Test
    fun givenExistingPack_whenDeleteClicked_thenRemovesPackAndNavigatesBack() = runTest {
        // given
        val existingPack = CustomAnswerPack(
            id = "test-pack-id",
            name = "Existing Pack",
            prompt = "Prompt",
            answers = persistentListOf(
                CustomAnswer(id = "answer-1", text = "Yes"),
                CustomAnswer(id = "answer-2", text = "No"),
            ),
        )
        customAnswerPackRepository.setInitialPacks(listOf(existingPack))

        val viewModel = createViewModel(answerPackId = existingPack.id)
        advanceUntilIdle()

        // when
        viewModel.onViewEvent(OnDeleteAnswerPackClick)
        advanceUntilIdle()

        // then
        assertTrue(customAnswerPackRepository.currentPacks.isEmpty())

        val action = viewModel.viewAction.first()
        assertEquals(Action.NavigateBack, action)
    }

    @Test
    fun givenPackIsActive_whenDeleteClicked_thenResetsToDefaultPack() = runTest {
        // given
        val existingPack = CustomAnswerPack(
            id = "test-pack-id",
            name = "Existing Pack",
            prompt = "Prompt",
            answers = persistentListOf(
                CustomAnswer(id = "answer-1", text = "Yes"),
                CustomAnswer(id = "answer-2", text = "No"),
            ),
        )
        customAnswerPackRepository.setInitialPacks(listOf(existingPack))
        settingsRepository.setInitialSettings(
            Settings.default.copy(activeAnswerPackId = existingPack.id)
        )

        val viewModel = createViewModel(answerPackId = existingPack.id)
        advanceUntilIdle()

        // when
        viewModel.onViewEvent(OnDeleteAnswerPackClick)
        advanceUntilIdle()

        // then
        assertEquals(builtInAnswerPackProvider.getDefault().id, settingsRepository.currentSettings.activeAnswerPackId)
    }

    @Test
    fun givenNonExistentPack_whenDeleteClicked_thenDoesNothing() = runTest {
        // given
        val viewModel = createViewModel(answerPackId = "non-existent-id")
        advanceUntilIdle()

        // when
        viewModel.onViewEvent(OnDeleteAnswerPackClick)
        advanceUntilIdle()

        // then
        assertFalse(viewModel.viewState.value.isLoading)
    }

    @Test
    fun whenAnswerTextChanged_thenUpdatesAnswerText() = runTest {
        val viewModel = createViewModel()
        advanceUntilIdle()

        // when
        val answerId = viewModel.viewState.value.answers[0].id
        val newText = "Updated answer"
        viewModel.onViewEvent(OnAnswerTextValueChange(answerId = answerId, newValue = newText))
        advanceUntilIdle()

        // then
        val state = viewModel.viewState.value
        assertEquals(newText, state.answers.find { it.id == answerId }?.text)
    }

    @Test
    fun whenAnswerTypeSelected_thenUpdatesAnswerType() = runTest {
        val viewModel = createViewModel()
        advanceUntilIdle()

        // when
        val answerId = viewModel.viewState.value.answers[0].id
        viewModel.onViewEvent(OnAnswerTypeSelect(answerId = answerId, newType = Answer.Type.SUCCESS))
        advanceUntilIdle()

        // then
        val state = viewModel.viewState.value
        assertEquals(Answer.Type.SUCCESS, state.answers.find { it.id == answerId }?.type)
    }

    @Test
    fun whenAnswerDeleteClicked_thenRemovesAnswer() = runTest {
        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.onViewEvent(OnNewAnswerClick)
        advanceUntilIdle()
        assertEquals(3, viewModel.viewState.value.answers.size)

        // when
        val answerIdToDelete = viewModel.viewState.value.answers[0].id
        viewModel.onViewEvent(OnAnswerDeleteClick(answerId = answerIdToDelete))
        advanceUntilIdle()

        // then
        val state = viewModel.viewState.value
        assertEquals(2, state.answers.size)
        assertFalse(state.answers.any { it.id == answerIdToDelete })
    }

    @Test
    fun givenAnswerHasError_whenAnswerDeleteClicked_thenClearsError() = runTest {
        // given
        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.onViewEvent(OnNewAnswerClick)
        advanceUntilIdle()

        viewModel.onViewEvent(OnNameValueChange("Test"))
        viewModel.onViewEvent(OnPromptValueChange("Prompt"))
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[0].id,
                newValue = "Answer 1",
            )
        )
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[1].id,
                newValue = "Answer 2",
            )
        )
        advanceUntilIdle()

        val emptyAnswerId = viewModel.viewState.value.answers[2].id
        viewModel.onViewEvent(OnSaveAnswerPackClick)
        advanceUntilIdle()

        assertTrue(viewModel.viewState.value.errors.any {
            it is State.Error.AnswerTextEmpty && it.answerId == emptyAnswerId
        })

        // when
        viewModel.onViewEvent(OnAnswerDeleteClick(answerId = emptyAnswerId))
        advanceUntilIdle()

        // then
        assertFalse(viewModel.viewState.value.errors.any {
            it is State.Error.AnswerTextEmpty && it.answerId == emptyAnswerId
        })
    }

    @Test
    fun whenNewAnswerClicked_thenAddsNewAnswer() = runTest {
        val viewModel = createViewModel()
        advanceUntilIdle()

        val initialCount = viewModel.viewState.value.answers.size

        // when
        viewModel.onViewEvent(OnNewAnswerClick)
        advanceUntilIdle()

        // then
        assertEquals(initialCount + 1, viewModel.viewState.value.answers.size)
    }

    @Test
    fun givenExistingPack_whenEditing_thenLoadsPackData() = runTest {
        // given
        val existingPack = CustomAnswerPack(
            id = "test-pack-id",
            name = "Existing Pack",
            prompt = "Existing Prompt",
            answers = persistentListOf(
                CustomAnswer(id = "answer-1", text = "Yes", type = Answer.Type.SUCCESS),
                CustomAnswer(id = "answer-2", text = "No", type = Answer.Type.DANGER),
            ),
        )
        customAnswerPackRepository.setInitialPacks(listOf(existingPack))

        // when
        val viewModel = createViewModel(answerPackId = existingPack.id)
        advanceUntilIdle()

        // then
        val state = viewModel.viewState.value
        assertFalse(state.isLoading)
        assertEquals(existingPack.name, state.name)
        assertEquals(existingPack.prompt, state.prompt)
        assertEquals(existingPack.answers.size, state.answers.size)
        assertEquals(existingPack.answers[0].text, state.answers[0].text)
        assertEquals(existingPack.answers[0].type, state.answers[0].type)
        assertEquals(existingPack.answers[1].text, state.answers[1].text)
        assertEquals(existingPack.answers[1].type, state.answers[1].type)
    }

    @Test
    fun givenPackNotFound_whenEditing_thenShowsEmptyState() = runTest {
        // given / when
        val viewModel = createViewModel(answerPackId = "non-existent-id")
        advanceUntilIdle()

        // then
        val state = viewModel.viewState.value
        assertFalse(state.isLoading)
        assertEquals("", state.name)
        assertEquals("", state.prompt)
        assertEquals(2, state.answers.size)
    }

    @Test
    fun whenNameChanged_thenUpdatesName() = runTest {
        val viewModel = createViewModel()
        advanceUntilIdle()

        // when
        val newName = "New Name"
        viewModel.onViewEvent(OnNameValueChange(newName))
        advanceUntilIdle()

        // then
        assertEquals(newName, viewModel.viewState.value.name)
    }

    @Test
    fun givenNameRequiredError_whenNameChanged_thenClearsError() = runTest {
        // given
        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.onViewEvent(OnPromptValueChange("Prompt"))
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[0].id,
                newValue = "Answer 1",
            )
        )
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[1].id,
                newValue = "Answer 2",
            )
        )
        advanceUntilIdle()

        viewModel.onViewEvent(OnSaveAnswerPackClick)
        advanceUntilIdle()

        assertTrue(viewModel.viewState.value.errors.any { it is State.Error.NameRequired })

        // when
        viewModel.onViewEvent(OnNameValueChange("Valid Name"))
        advanceUntilIdle()

        // then
        assertFalse(viewModel.viewState.value.errors.any { it is State.Error.NameRequired })
    }

    @Test
    fun whenPromptChanged_thenUpdatesPrompt() = runTest {
        val viewModel = createViewModel()
        advanceUntilIdle()

        // when
        val newPrompt = "New Prompt"
        viewModel.onViewEvent(OnPromptValueChange(newPrompt))
        advanceUntilIdle()

        // then
        assertEquals(newPrompt, viewModel.viewState.value.prompt)
    }

    @Test
    fun givenPromptRequiredError_whenPromptChanged_thenClearsError() = runTest {
        // given
        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.onViewEvent(OnNameValueChange("Name"))
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[0].id,
                newValue = "Answer 1",
            )
        )
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[1].id,
                newValue = "Answer 2",
            )
        )
        advanceUntilIdle()

        viewModel.onViewEvent(OnSaveAnswerPackClick)
        advanceUntilIdle()

        assertTrue(viewModel.viewState.value.errors.any { it is State.Error.PromptRequired })

        // when
        viewModel.onViewEvent(OnPromptValueChange("Valid Prompt"))
        advanceUntilIdle()

        // then
        assertFalse(viewModel.viewState.value.errors.any { it is State.Error.PromptRequired })
    }

    @Test
    fun givenAnswerTextEmptyError_whenAnswerTextChanged_thenClearsError() = runTest {
        // given
        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.onViewEvent(OnNameValueChange("Name"))
        viewModel.onViewEvent(OnPromptValueChange("Prompt"))
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[0].id,
                newValue = "Answer 1",
            )
        )
        advanceUntilIdle()

        val emptyAnswerId = viewModel.viewState.value.answers[1].id
        viewModel.onViewEvent(OnSaveAnswerPackClick)
        advanceUntilIdle()

        assertTrue(viewModel.viewState.value.errors.any {
            it is State.Error.AnswerTextEmpty && it.answerId == emptyAnswerId
        })

        // when
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = emptyAnswerId,
                newValue = "Answer 2",
            )
        )
        advanceUntilIdle()

        // then
        assertFalse(viewModel.viewState.value.errors.any {
            it is State.Error.AnswerTextEmpty && it.answerId == emptyAnswerId
        })
    }

    @Test
    fun givenValidData_whenSaveClicked_thenSetsPackAsActive() = runTest {
        // given
        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.onViewEvent(OnNameValueChange("Test Pack"))
        viewModel.onViewEvent(OnPromptValueChange("Ask a question"))
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[0].id,
                newValue = "Yes",
            )
        )
        viewModel.onViewEvent(
            OnAnswerTextValueChange(
                answerId = viewModel.viewState.value.answers[1].id,
                newValue = "No",
            )
        )
        advanceUntilIdle()

        // when
        viewModel.onViewEvent(OnSaveAnswerPackClick)
        advanceUntilIdle()

        // then
        val savedPack = customAnswerPackRepository.currentPacks.first()
        assertEquals(savedPack.id, settingsRepository.currentSettings.activeAnswerPackId)
    }

    @Test
    fun givenExistingPack_whenSaveClicked_thenUpdatesExistingPack() = runTest {
        // given
        val existingPack = CustomAnswerPack(
            id = "test-pack-id",
            name = "Original Name",
            prompt = "Original Prompt",
            answers = persistentListOf(
                CustomAnswer(id = "answer-1", text = "Yes"),
                CustomAnswer(id = "answer-2", text = "No"),
            ),
        )
        customAnswerPackRepository.setInitialPacks(listOf(existingPack))

        val viewModel = createViewModel(answerPackId = existingPack.id)
        advanceUntilIdle()

        val updatedName = "Updated Name"
        val updatedPrompt = "Updated Prompt"
        viewModel.onViewEvent(OnNameValueChange(updatedName))
        viewModel.onViewEvent(OnPromptValueChange(updatedPrompt))
        advanceUntilIdle()

        // when
        viewModel.onViewEvent(OnSaveAnswerPackClick)
        advanceUntilIdle()

        // then
        val packs = customAnswerPackRepository.currentPacks
        assertEquals(1, packs.size)
        assertEquals(existingPack.id, packs[0].id)
        assertEquals(updatedName, packs[0].name)
        assertEquals(updatedPrompt, packs[0].prompt)
    }

}
