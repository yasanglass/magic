package glass.yasan.magic.presentation.route.magic

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import glass.yasan.kepko.component.Text
import glass.yasan.magic.data.local.DefaultAnswerPacks
import glass.yasan.magic.presentation.route.magic.MagicViewModel.Event
import glass.yasan.magic.presentation.util.SystemBarColorsEffect
import glass.yasan.magic.presentation.route.magic.MagicViewModel.State
import glass.yasan.toolkit.compose.color.toContentColor
import glass.yasan.toolkit.compose.viewmodel.rememberSendViewEvent
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MagicScreen() {
    val viewModel: MagicViewModel = koinViewModel()
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val sendEvent = rememberSendViewEvent(viewModel)

    MagicScreen(
        state = state,
        sendEvent = sendEvent,
    )
}

@Composable
private fun MagicScreen(
    state: State,
    sendEvent: (Event) -> Unit,
) {
    val containerColor by animateColorAsState(state.answer.type.getContainerColor())
    val contentColor by animateColorAsState(containerColor.toContentColor())
    SystemBarColorsEffect(containerColor)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(containerColor)
            .clickable(
                interactionSource = null,
                indication = null,
            ) {
                sendEvent(Event.OnClick)
            },
    ) {
        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = state.answer.getText(),
                    fontSize = 32.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = contentColor,
                    modifier = Modifier
                        .padding(32.dp),
                )
            }
        }
    }
}

private class StatePreviewParameterProvider : PreviewParameterProvider<State> {
    override val values: Sequence<State> = DefaultAnswerPacks.magicEightBallAnswers
        .map { answer -> State(answer = answer) }
        .asSequence()
}

@Preview
@Composable
private fun MagicScreenPreview(
    @PreviewParameter(StatePreviewParameterProvider::class) state: State,
) {
    MagicScreen(
        state = state,
        sendEvent = {},
    )
}
