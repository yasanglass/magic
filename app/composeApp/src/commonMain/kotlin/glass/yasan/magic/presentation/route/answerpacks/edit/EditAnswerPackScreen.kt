package glass.yasan.magic.presentation.route.answerpacks.edit

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import glass.yasan.kepko.component.ButtonText
import glass.yasan.kepko.component.Icon
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.component.Text
import glass.yasan.kepko.component.TextField
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons
import glass.yasan.magic.core.resources.Res
import glass.yasan.magic.core.resources.answer
import glass.yasan.magic.core.resources.answer_pack_delete
import glass.yasan.magic.core.resources.answer_pack_name
import glass.yasan.magic.core.resources.answer_pack_prompt
import glass.yasan.magic.core.resources.answer_pack_save
import glass.yasan.magic.core.resources.answers
import glass.yasan.magic.core.resources.edit_answer_pack
import glass.yasan.magic.core.resources.error_answer_empty
import glass.yasan.magic.core.resources.error_name_required
import glass.yasan.magic.core.resources.error_prompt_required
import glass.yasan.magic.core.resources.new_answer_pack
import glass.yasan.magic.feature.answers.domain.model.Answer
import glass.yasan.magic.feature.answers.domain.model.CustomAnswer
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.Action
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.Event
import glass.yasan.magic.presentation.route.answerpacks.edit.EditAnswerPacksViewModel.State
import glass.yasan.magic.util.PreviewWithTest
import glass.yasan.toolkit.compose.viewmodel.ViewActionEffect
import glass.yasan.toolkit.compose.color.toContentColor
import glass.yasan.toolkit.compose.spacer.verticalSpacerItem
import glass.yasan.toolkit.compose.viewmodel.rememberSendViewEvent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun EditAnswerPackScreen(
    navController: NavHostController,
    answerPackId: String? = null,
) {
    val isEditMode = answerPackId != null

    val viewModel: EditAnswerPacksViewModel = koinViewModel { parametersOf(answerPackId) }
    val state: State by viewModel.viewState.collectAsStateWithLifecycle()
    val sendEvent: (Event) -> Unit = rememberSendViewEvent(viewModel)

    EditAnswerPackScreen(
        isEditMode = isEditMode,
        state = state,
        sendEvent = sendEvent,
        onBackClick = navController::navigateUp,
    )

    ViewActionEffect(viewModel.viewAction) { action ->
        when (action) {
            is Action.NavigateBack -> navController.navigateUp()
        }
    }
}

@Composable
private fun EditAnswerPackScreen(
    isEditMode: Boolean,
    state: State,
    sendEvent: (Event) -> Unit,
    onBackClick: () -> Unit,
) {
    val title = if (isEditMode) {
        stringResource(Res.string.edit_answer_pack)
    } else {
        stringResource(Res.string.new_answer_pack)
    }

    val enabled = !state.isLoading

    Scaffold(
        title = title,
        onBackClick = onBackClick,
        bottomBar = { BottomBar(isEditMode, enabled, sendEvent) },
    ) { contentPadding ->
        EditAnswerPackContent(
            contentPadding = contentPadding,
            state = state,
            enabled = enabled,
            sendEvent = sendEvent,
        )
    }
}

@Composable
private fun EditAnswerPackContent(
    contentPadding: PaddingValues,
    state: EditAnswerPacksViewModel.State,
    enabled: Boolean,
    sendEvent: (Event) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(
            top = contentPadding.calculateTopPadding(),
            bottom = contentPadding.calculateBottomPadding() + 100.dp,
            start = contentPadding.calculateLeftPadding(LayoutDirection.Ltr),
            end = contentPadding.calculateRightPadding(LayoutDirection.Ltr),
        ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        verticalSpacerItem(height = 12.dp)

        item {
            NameTextField(
                name = state.name,
                error = state.resolveNameError(),
                enabled = enabled,
                sendEvent = sendEvent,
            )
        }

        item {
            PromptTextField(
                prompt = state.prompt,
                error = state.resolvePromptError(),
                enabled = enabled,
                sendEvent = sendEvent,
            )
        }

        item {
            AnswersHeader(
                answerCount = state.answers.size,
                enabled = enabled,
                sendEvent = sendEvent,
            )
        }

        items(
            items = state.answers,
            key = { it.id },
        ) { answer ->
            AnswerItem(
                answer = answer,
                error = state.resolveAnswerError(answer.id),
                deletable = state.answers.size > 2,
                enabled = enabled,
                sendEvent = sendEvent,
                modifier = Modifier.animateItem(
                    fadeInSpec = tween(durationMillis = 200),
                    fadeOutSpec = tween(durationMillis = 200),
                    placementSpec = tween(durationMillis = 200),
                ),
            )
        }

        verticalSpacerItem(height = 12.dp)
    }
}

@Composable
private fun BottomBar(
    isEditMode: Boolean,
    enabled: Boolean,
    sendEvent: (Event) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp,
            ),
    ) {
        ButtonText(
            text = stringResource(Res.string.answer_pack_save),
            onClick = { sendEvent(Event.OnSaveAnswerPackClick) },
            containerColor = KepkoTheme.colors.content,
            fillWidth = false,
            enabled = enabled,
            modifier = Modifier.weight(1f),
        )
        if (isEditMode) {
            ButtonText(
                text = stringResource(Res.string.answer_pack_delete),
                containerColor = KepkoTheme.colors.danger,
                fillWidth = false,
                enabled = enabled,
                onClick = { sendEvent(Event.OnDeleteAnswerPackClick) },
            )
        }
    }
}

@Composable
private fun NameTextField(
    name: String,
    error: State.Error.NameRequired?,
    enabled: Boolean,
    sendEvent: (Event) -> Unit,
) {
    Column {
        TextField(
            label = stringResource(Res.string.answer_pack_name),
            value = name,
            onValueChange = { sendEvent(Event.OnNameValueChange(it)) },
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
        )
        error?.let {
            Text(
                text = it.resolveString(),
                color = KepkoTheme.colors.danger,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
            )
        }
    }
}

@Composable
private fun PromptTextField(
    prompt: String,
    error: State.Error.PromptRequired?,
    enabled: Boolean,
    sendEvent: (Event) -> Unit,
) {
    Column {
        TextField(
            label = stringResource(Res.string.answer_pack_prompt),
            value = prompt,
            onValueChange = { sendEvent(Event.OnPromptValueChange(it)) },
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
        )
        error?.let {
            Text(
                text = it.resolveString(),
                color = KepkoTheme.colors.danger,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
            )
        }
    }
}

@Composable
private fun AnswersHeader(
    answerCount: Int,
    enabled: Boolean,
    sendEvent: (Event) -> Unit,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            AnimatedAnswerCount(
                count = answerCount,
                modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
            )
            Icon(
                painter = Icons.add,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(
                        enabled = enabled,
                        onClick = { sendEvent(Event.OnNewAnswerClick) },
                    )
                    .padding(16.dp),
            )
        }
    }
}

@Composable
private fun AnimatedAnswerCount(
    count: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically { -it } togetherWith slideOutVertically { it }
                } else {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                }
            },
            label = "answerCount",
        ) { animatedCount ->
            Text(
                text = "$animatedCount ",
                fontWeight = FontWeight.Medium,
            )
        }
        Text(
            text = stringResource(Res.string.answers),
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
private fun State.Error.resolveString(): String = when (this) {
    is State.Error.NameRequired -> stringResource(Res.string.error_name_required)
    is State.Error.PromptRequired -> stringResource(Res.string.error_prompt_required)
    is State.Error.AnswerTextEmpty -> stringResource(Res.string.error_answer_empty)
}

private fun State.resolveNameError(): State.Error.NameRequired? =
    errors.filterIsInstance<State.Error.NameRequired>().firstOrNull()

private fun State.resolvePromptError(): State.Error.PromptRequired? =
    errors.filterIsInstance<State.Error.PromptRequired>().firstOrNull()

private fun State.resolveAnswerError(answerId: String): State.Error.AnswerTextEmpty? =
    errors.filterIsInstance<State.Error.AnswerTextEmpty>().find { it.answerId == answerId }

@Composable
private fun AnswerTypeChip(
    type: Answer.Type,
    isSelected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val containerColor = if (enabled) type.resolveColor() else KepkoTheme.colors.contentDisabled
    val contentColor = containerColor.toContentColor()
    val alpha by animateFloatAsState(if (isSelected) 1f else 0f)
    val horizontalPadding by animateDpAsState(if (isSelected) 16.dp else 0.dp)

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .clickable(enabled = enabled, onClick = onClick)
            .background(containerColor),
    ) {
        Icon(
            painter = Icons.check,
            contentDescription = null,
            color = contentColor,
            modifier = Modifier
                .padding(all = 4.dp)
                .padding(horizontal = horizontalPadding)
                .alpha(alpha),
        )
    }
}

@Composable
private fun AnswerTypeSelector(
    selectedType: Answer.Type,
    answerId: String,
    enabled: Boolean,
    sendEvent: (Event) -> Unit,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
    ) {
        Answer.Type.entries.forEach { type ->
            AnswerTypeChip(
                type = type,
                isSelected = selectedType == type,
                enabled = enabled,
                onClick = { sendEvent(Event.OnAnswerTypeSelect(answerId, type)) },
            )
        }
    }
}

@Composable
private fun AnswerItem(
    answer: CustomAnswer,
    error: State.Error.AnswerTextEmpty?,
    deletable: Boolean,
    sendEvent: (Event) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(bottom = 16.dp),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                TextField(
                    label = stringResource(Res.string.answer),
                    value = answer.text,
                    onValueChange = { sendEvent(Event.OnAnswerTextValueChange(answer.id, it)) },
                    enabled = enabled,
                    modifier = Modifier.weight(1f),
                )
                AnimatedVisibility(
                    visible = deletable,
                    enter = expandHorizontally(),
                    exit = shrinkHorizontally(),
                ) {
                    Icon(
                        painter = Icons.delete,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(
                                enabled = enabled,
                                onClick = { sendEvent(Event.OnAnswerDeleteClick(answerId = answer.id)) },
                            )
                            .padding(16.dp),
                    )
                }
            }
            error?.let {
                Text(
                    text = it.resolveString(),
                    color = KepkoTheme.colors.danger,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                )
            }
        }
        AnswerTypeSelector(
            selectedType = answer.type,
            answerId = answer.id,
            enabled = enabled,
            sendEvent = sendEvent,
        )
    }
}

@PreviewWithTest
@Composable
internal fun EditAnswerPackScreenPreview() {
    PreviewContent(isEditMode = true, answerCount = 2)
}

@PreviewWithTest
@Composable
internal fun CreateAnswerPackScreenPreview() {
    PreviewContent(isEditMode = false, answerCount = 3)
}

@PreviewWithTest
@Composable
internal fun LoadingAnswerPackScreenPreview() {
    PreviewContent(isEditMode = true, isLoading = true)
}

@Composable
private fun PreviewContent(
    isEditMode: Boolean,
    isLoading: Boolean = false,
    answerCount: Int = 4,
) {
    val allAnswers = persistentListOf(
        CustomAnswer(text = if (isEditMode) "Yes, definitely!" else "", type = Answer.Type.SUCCESS),
        CustomAnswer(text = if (isEditMode) "Maybe later" else "", type = Answer.Type.CAUTION),
        CustomAnswer(text = if (isEditMode) "No way!" else "", type = Answer.Type.DANGER),
        CustomAnswer(type = Answer.Type.GENERIC),
    )
    KepkoTheme {
        EditAnswerPackScreen(
            isEditMode = isEditMode,
            state = State(
                isLoading = isLoading,
                name = if (isEditMode) "My Custom Pack" else "",
                prompt = if (isEditMode) "Ask" else "",
                answers = allAnswers.take(answerCount).toImmutableList(),
            ),
            sendEvent = {},
            onBackClick = {},
        )
    }
}
