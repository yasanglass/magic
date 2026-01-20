package glass.yasan.magic.presentation.route.answerpacks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import glass.yasan.kepko.component.ButtonText
import glass.yasan.kepko.component.Icon
import glass.yasan.kepko.component.Midground
import glass.yasan.kepko.component.PreferenceRadioButton
import glass.yasan.kepko.component.Scaffold
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.resource.Icons
import glass.yasan.magic.core.resources.Res
import glass.yasan.magic.core.resources.answer_pack_magic_8_ball
import glass.yasan.magic.core.resources.answer_pack_prompt_magic_8_ball
import glass.yasan.magic.core.resources.answer_packs
import glass.yasan.magic.core.resources.new_answer_pack
import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import glass.yasan.magic.feature.answers.domain.model.BuiltInAnswerPack
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import glass.yasan.magic.presentation.navigation.Route
import glass.yasan.magic.presentation.route.answerpacks.AnswerPacksViewModel.Action
import glass.yasan.magic.presentation.route.answerpacks.AnswerPacksViewModel.Event
import glass.yasan.magic.util.PreviewWithTest
import glass.yasan.toolkit.compose.spacer.verticalSpacerItem
import glass.yasan.toolkit.compose.viewmodel.ViewActionEffect
import glass.yasan.toolkit.compose.viewmodel.rememberSendViewEvent
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AnswerPacksScreen(
    navController: NavHostController,
) {
    val viewModel: AnswerPacksViewModel = koinViewModel()
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val sendEvent = rememberSendViewEvent(viewModel)

    AnswerPacksScreen(
        state = state,
        sendEvent = sendEvent,
        onBackClick = { navController.navigateUp() },
    )

    ViewActionEffect(viewModel.viewAction) { action ->
        when (action) {
            is Action.NavigateToEditAnswerPack -> {
                navController.navigate(
                    route = Route.EditAnswerPack(
                        answerPackId = action.id,
                    ),
                )
            }
        }
    }
}

@Composable
private fun AnswerPacksScreen(
    state: AnswerPacksViewModel.State,
    sendEvent: (Event) -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        title = stringResource(Res.string.answer_packs),
        onBackClick = onBackClick,
    ) { contentPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = contentPadding,
        ) {
            verticalSpacerItem(height = 12.dp)

            item {
                ButtonText(
                    text = stringResource(Res.string.new_answer_pack),
                    containerColor = KepkoTheme.colors.content,
                    leadingIcon = Icons.add,
                    trailingIcon = null,
                    onClick = { sendEvent(Event.OnNewAnswerPackClick) },
                )
            }

            verticalSpacerItem(height = 8.dp)

            items(items = state.answerPacks) { pack ->
                AnswerPackItem(
                    pack = pack,
                    selected = pack.id == state.activeAnswerPackId,
                    onClick = {
                        sendEvent(
                            Event.OnSelectAnswerPackClick(
                                id = pack.id,
                            )
                        )
                    },
                    onEditClick = {
                        sendEvent(
                            Event.OnEditAnswerPackClick(
                                id = pack.id,
                            )
                        )
                    },
                )
            }

            verticalSpacerItem(height = 12.dp)
        }
    }
}

@Composable
private fun AnswerPackItem(
    pack: AnswerPack<*>,
    selected: Boolean,
    onClick: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        PreferenceRadioButton(
            title = pack.resolveName(),
            selected = selected,
            onClick = onClick,
            reverse = true,
            modifier = Modifier.weight(1f),
        )
        if (pack.isEditable) {
            Icon(
                painter = Icons.edit,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(
                        onClick = onEditClick,
                    )
                    .padding(16.dp),
            )
        }
    }
}

@PreviewWithTest
@Composable
internal fun AnswerPackItemPreview() {
    KepkoTheme {
        Midground {
            AnswerPackItem(
                pack = CustomAnswerPack(
                    id = "preview-1",
                    name = "Classic",
                    prompt = "Ask the Magic 8 Ball",
                    answers = persistentListOf(),
                ),
                selected = true,
                onClick = {},
                onEditClick = {},
            )
        }
    }
}

@PreviewWithTest
@Composable
internal fun AnswerPacksScreenPreview() {
    KepkoTheme {
        AnswerPacksScreen(
            state = AnswerPacksViewModel.State(
                answerPacks = persistentListOf(
                    BuiltInAnswerPack(
                        id = "preview-1",
                        nameStringRes = Res.string.answer_pack_magic_8_ball,
                        promptStringRes = Res.string.answer_pack_prompt_magic_8_ball,
                        answers = persistentListOf(),
                    ),
                    CustomAnswerPack(
                        id = "preview-2",
                        name = "Sarcastic",
                        prompt = "Ask the Sarcastic Ball",
                        answers = persistentListOf(),
                    ),
                    CustomAnswerPack(
                        id = "preview-3",
                        name = "Optimistic",
                        prompt = "Ask the Optimistic Ball",
                        answers = persistentListOf(),
                    ),
                ),
                activeAnswerPackId = "preview-1",
            ),
            sendEvent = {},
            onBackClick = {},
        )
    }
}
