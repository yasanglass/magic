package glass.yasan.magic.presentation.route.magic

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import glass.yasan.kepko.component.Text
import glass.yasan.magic.data.local.DefaultAnswerPacks
import glass.yasan.magic.domain.model.Answer
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.presentation.navigation.Route
import glass.yasan.magic.presentation.route.magic.MagicViewModel.Action.NavigateToSettings
import glass.yasan.magic.presentation.route.magic.MagicViewModel.Event
import glass.yasan.magic.presentation.util.SystemBarColorsEffect
import glass.yasan.magic.presentation.route.magic.MagicViewModel.State
import glass.yasan.magic.resources.Res
import glass.yasan.magic.resources.answer_ask
import glass.yasan.magic.resources.long_click_for_settings
import glass.yasan.magic.resources.open_settings
import glass.yasan.magic.util.PreviewWithTest
import glass.yasan.toolkit.compose.color.toContentColor
import glass.yasan.toolkit.compose.viewmodel.ViewActionEffect
import glass.yasan.toolkit.compose.viewmodel.rememberSendViewEvent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MagicScreen(
    navController: NavHostController,
    settings: Settings,
) {
    val viewModel: MagicViewModel = koinViewModel()
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val sendEvent = rememberSendViewEvent(viewModel)

    MagicScreen(
        state = state,
        sendEvent = sendEvent,
        darkIcons = !settings.theme.asKepkoThemeStyle().isDark,
    )

    ViewActionEffect(
        viewAction = viewModel.viewAction,
    ) { action ->
        when (action) {
            is NavigateToSettings -> navController.navigate(Route.Settings)
        }
    }
}

@Composable
private fun MagicScreen(
    state: State,
    sendEvent: (Event) -> Unit,
    darkIcons: Boolean = true,
) {
    val containerColor by animateColorAsState(state.answer.type.getContainerColor())
    val contentColor by animateColorAsState(containerColor.toContentColor())
    val additionalContentAlpha by animateFloatAsState(if (state.answer == Answer.empty) 1f else 0f)

    SystemBarColorsEffect(containerColor, darkIcons = darkIcons)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(containerColor)
            .safeContentPadding()
            .combinedClickable(
                interactionSource = null,
                indication = null,
                onClickLabel = stringResource(Res.string.answer_ask),
                onClick = { sendEvent(Event.Ask) },
                onLongClickLabel = stringResource(Res.string.open_settings),
                onLongClick = { sendEvent(Event.OpenSettings) },
            ),
    ) {
        Answer(state, contentColor)
        Tip(
            modifier = Modifier
                .alpha(additionalContentAlpha)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun Answer(
    state: State,
    contentColor: Color
) {
    AnimatedContent(
        targetState = state.answer,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
    ) { answer ->
        Text(
            text = answer.getText(),
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

@Composable
private fun Tip(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(Res.string.long_click_for_settings),
        fontSize = 12.sp,
        fontWeight = FontWeight.Light,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 32.dp,
                horizontal = 16.dp,
            ),
    )
}

@PreviewWithTest
@Composable
internal fun MagicScreenEmptyPreview() {
    PreviewContent(
        answer = Answer.empty,
    )
}

@PreviewWithTest
@Composable
internal fun MagicScreenSuccessPreview() {
    PreviewContent(
        answer = DefaultAnswerPacks.magicEightBallAnswers.first { it.type == Answer.Type.SUCCESS },
    )
}

@PreviewWithTest
@Composable
internal fun MagicScreenCautionPreview() {
    PreviewContent(
        answer = DefaultAnswerPacks.magicEightBallAnswers.first { it.type == Answer.Type.CAUTION },
    )
}

@PreviewWithTest
@Composable
internal fun MagicScreenDangerPreview() {
    PreviewContent(
        answer = DefaultAnswerPacks.magicEightBallAnswers.first { it.type == Answer.Type.DANGER },
    )
}

@Composable
private fun PreviewContent(
    answer: Answer,
) {
    MagicScreen(
        state = State(
            answer = answer,
        ),
        sendEvent = {},
    )
}
