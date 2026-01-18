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
import glass.yasan.magic.core.resources.answer_packs
import glass.yasan.magic.core.resources.new_answer_pack
import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.util.PreviewWithTest
import glass.yasan.toolkit.compose.spacer.verticalSpacerItem
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
}

@Composable
private fun AnswerPacksScreen(
    state: AnswerPacksViewModel.State,
    sendEvent: (AnswerPacksViewModel.Event) -> Unit,
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
                    onClick = { sendEvent(AnswerPacksViewModel.Event.OnNewAnswerPackClick) },
                )
            }

            verticalSpacerItem(height = 8.dp)

            items(items = state.answerPacks) { pack ->
                AnswerPackItem(
                    pack = pack,
                    selected = pack.id == state.activeAnswerPackId,
                    onClick = {
                        sendEvent(
                            AnswerPacksViewModel.Event.OnSelectAnswerPackClick(
                                id = pack.id,
                            )
                        )
                    },
                    onEditClick = {
                        sendEvent(
                            AnswerPacksViewModel.Event.OnEditAnswerPackClick(
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
    pack: AnswerPack,
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
            title = pack.name(),
            selected = selected,
            onClick = onClick,
            modifier = Modifier.weight(1f),
        )
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

@PreviewWithTest
@Composable
internal fun AnswerPackItemPreview() {
    KepkoTheme {
        Midground {
            AnswerPackItem(
                pack = AnswerPack(
                    id = 1L,
                    name = { "Classic" },
                    prompt = { "Ask the Magic 8 Ball" },
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
                    AnswerPack(
                        id = 1L,
                        name = { "Classic" },
                        prompt = { "Ask the Magic 8 Ball" },
                        answers = persistentListOf(),
                    ),
                    AnswerPack(
                        id = 2L,
                        name = { "Sarcastic" },
                        prompt = { "Ask the Sarcastic Ball" },
                        answers = persistentListOf(),
                    ),
                    AnswerPack(
                        id = 3L,
                        name = { "Optimistic" },
                        prompt = { "Ask the Optimistic Ball" },
                        answers = persistentListOf(),
                    ),
                ),
                activeAnswerPackId = 1,
            ),
            sendEvent = {},
            onBackClick = {},
        )
    }
}
