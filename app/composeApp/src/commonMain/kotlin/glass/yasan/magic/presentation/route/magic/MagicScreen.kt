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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
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
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.magic.feature.answers.domain.model.Answer
import glass.yasan.magic.feature.answers.domain.model.Answer.Type.CAUTION
import glass.yasan.magic.feature.answers.domain.model.Answer.Type.GENERIC
import glass.yasan.magic.feature.answers.domain.model.Answer.Type.DANGER
import glass.yasan.magic.feature.answers.domain.model.Answer.Type.INFO
import glass.yasan.magic.feature.answers.domain.model.Answer.Type.SUCCESS
import glass.yasan.magic.feature.answers.util.preview.PreviewAnswers
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.magic.feature.settings.domain.model.Settings.Theme.DARK
import glass.yasan.magic.feature.settings.domain.model.Settings.Theme.LIGHT
import glass.yasan.magic.feature.settings.domain.model.Settings.Theme.SYSTEM
import glass.yasan.magic.presentation.navigation.Route
import glass.yasan.magic.presentation.route.magic.MagicViewModel.Action.NavigateToSettings
import glass.yasan.magic.presentation.route.magic.MagicViewModel.Event
import glass.yasan.magic.presentation.util.SystemBarColorsEffect
import glass.yasan.magic.presentation.route.magic.MagicViewModel.State
import glass.yasan.magic.core.resources.Res
import glass.yasan.magic.core.resources.answer_pack_prompt_magic_8_ball
import glass.yasan.magic.core.resources.long_click_for_settings
import glass.yasan.magic.core.resources.open_settings
import glass.yasan.magic.util.PreviewWithTest
import glass.yasan.toolkit.compose.color.isDark
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
        settings = settings,
        state = state,
        sendEvent = sendEvent,
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
    settings: Settings,
    state: State,
    sendEvent: (Event) -> Unit,
) {
    val themeStyle = settings.theme.asKepkoThemeStyle()
    val (backgroundColor, contentColor) = state.answer.resolveColors(themeStyle)
    val animatedBackgroundColor by animateColorAsState(backgroundColor)
    val animatedContentColor by animateColorAsState(contentColor)
    val tipAlpha by animateFloatAsState(if (state.answer == Answer.empty) 1f else 0f)

    SystemBarColorsEffect(
        statusBarColor = animatedBackgroundColor,
        darkIcons = !animatedBackgroundColor.isDark(),
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(animatedBackgroundColor)
            .safeContentPadding()
            .combinedClickable(
                interactionSource = null,
                indication = null,
                onClickLabel = stringResource(Res.string.answer_pack_prompt_magic_8_ball),
                onClick = { sendEvent(Event.Ask) },
                onLongClickLabel = stringResource(Res.string.open_settings),
                onLongClick = { sendEvent(Event.OpenSettings) },
            ),
    ) {
        Answer(state, animatedContentColor)
        Tip(
            color = animatedContentColor,
            modifier = Modifier
                .alpha(tipAlpha)
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
    color: Color,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(Res.string.long_click_for_settings),
        color = color,
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

@Composable
private fun Answer.resolveColors(
    themeStyle: ThemeStyle,
): Pair<Color, Color> {
    val typeColor = when (type) {
        GENERIC -> KepkoTheme.colors.content
        SUCCESS -> KepkoTheme.colors.success
        INFO -> KepkoTheme.colors.information
        CAUTION -> KepkoTheme.colors.caution
        DANGER -> KepkoTheme.colors.danger
    }

    val backgroundColor = if (themeStyle.isDark) KepkoTheme.colors.midground else typeColor
    val contentColor = if (themeStyle.isDark) typeColor else backgroundColor.toContentColor()

    return backgroundColor to contentColor
}

@PreviewWithTest
@Composable
internal fun MagicScreenEmptyLightPreview() {
    PreviewContent(answer = Answer.empty, theme = LIGHT)
}

@PreviewWithTest
@Composable
internal fun MagicScreenEmptyDarkPreview() {
    PreviewContent(answer = Answer.empty, theme = DARK)
}

@PreviewWithTest
@Composable
internal fun MagicScreenSuccessLightPreview() {
    PreviewContent(
        answer = PreviewAnswers.successAnswer,
        theme = LIGHT,
    )
}

@PreviewWithTest
@Composable
internal fun MagicScreenSuccessDarkPreview() {
    PreviewContent(
        answer = PreviewAnswers.successAnswer,
        theme = DARK,
    )
}

@PreviewWithTest
@Composable
internal fun MagicScreenCautionLightPreview() {
    PreviewContent(
        answer = PreviewAnswers.cautionAnswer,
        theme = LIGHT,
    )
}

@PreviewWithTest
@Composable
internal fun MagicScreenCautionDarkPreview() {
    PreviewContent(
        answer = PreviewAnswers.cautionAnswer,
        theme = DARK,
    )
}

@PreviewWithTest
@Composable
internal fun MagicScreenDangerLightPreview() {
    PreviewContent(
        answer = PreviewAnswers.dangerAnswer,
        theme = LIGHT,
    )
}

@PreviewWithTest
@Composable
internal fun MagicScreenDangerDarkPreview() {
    PreviewContent(
        answer = PreviewAnswers.dangerAnswer,
        theme = DARK,
    )
}

@PreviewWithTest
@Composable
internal fun MagicScreenInfoLightPreview() {
    PreviewContent(
        answer = PreviewAnswers.infoAnswer,
        theme = LIGHT,
    )
}

@PreviewWithTest
@Composable
internal fun MagicScreenInfoDarkPreview() {
    PreviewContent(
        answer = PreviewAnswers.infoAnswer,
        theme = DARK,
    )
}

@Composable
private fun PreviewContent(
    answer: Answer,
    theme: Settings.Theme,
) {
    KepkoTheme(
        style = theme.asKepkoThemeStyle(),
    ) {
        MagicScreen(
            state = State(
                answer = answer,
            ),
            sendEvent = {},
            settings = Settings.default.copy(
                theme = theme,
            ),
        )
    }
}

@PreviewWithTest
@Composable
internal fun MagicScreenColorMatrixPreview() {
    val themes = Settings.Theme.entries.filterNot { it == SYSTEM }

    Column {
        themes.forEach { theme ->
            val themeStyle = theme.asKepkoThemeStyle()
            KepkoTheme(style = themeStyle) {
                Row {
                    Answer.Type.entries.forEach { type ->
                        val answer = Answer(getText = { type.name }, type = type)
                        val (backgroundColor, contentColor) = answer.resolveColors(themeStyle)

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(64.dp)
                                .background(backgroundColor),
                        ) {
                            Text(
                                text = type.name,
                                color = contentColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}
