package glass.yasan.magic.ui.magic

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.magic.domain.model.Answer
import glass.yasan.toolkit.compose.coroutines.collectAsStateWithLifecycleIfAvailable
import glass.yasan.toolkit.compose.font.rubikFontFamily
import glass.yasan.toolkit.compose.viewmodel.rememberSendViewEvent
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
private fun AnimatedCharacter(
    targetChar: Char,
    previousChar: Char,
    index: Int,
    color: Color,
    animationKey: Int,
    fontSize: TextUnit,
    modifier: Modifier = Modifier
) {
    // Initialize with previous char if we're animating, otherwise with target
    var displayedChar by remember(animationKey) {
        mutableStateOf(if (animationKey == 0) targetChar else previousChar)
    }
    val randomChars = remember { "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!?.,;: " }

    LaunchedEffect(animationKey) {
        if (animationKey == 0) {
            displayedChar = targetChar
        } else {
            // Staggered start delay for wave effect
            delay(index * 20L)

            val cycles = 8 + index % 5
            repeat(cycles) {
                displayedChar = randomChars.random()
                delay(50)
            }
            displayedChar = targetChar
        }
    }

    AnimatedContent(
        targetState = displayedChar,
        transitionSpec = {
            (slideInVertically(
                animationSpec = tween(durationMillis = 80, easing = LinearEasing)
            ) { height -> height } + fadeIn(
                animationSpec = tween(durationMillis = 80, easing = LinearEasing)
            )) togetherWith
                    (slideOutVertically(
                        animationSpec = tween(durationMillis = 80, easing = LinearEasing)
                    ) { height -> -height } + fadeOut(
                        animationSpec = tween(durationMillis = 80, easing = LinearEasing)
                    ))
        },
        modifier = modifier
    ) { char ->
        Text(
            text = char.toString(),
            fontSize = fontSize,
            color = color,
            fontFamily = rubikFontFamily(),
        )
    }
}

val AffirmativeColor = Color(0xFF77dd77)
val NonCommittalColor = Color(0xFFffb347)
val NegativeColor = Color(0xFFff6961)

@Composable
fun getAnswerColors(answerType: Answer.Type, isDark: Boolean): Pair<Color, Color> {
    val typeColor = when (answerType) {
        Answer.Type.AFFIRMATIVE -> AffirmativeColor
        Answer.Type.NON_COMMITTAL -> NonCommittalColor
        Answer.Type.NEGATIVE -> NegativeColor
        Answer.Type.NEUTRAL -> if (isDark) Color.White else Color.Black
    }

    return if (isDark) {
        // Dark theme: black background, colored text
        Color.Black to typeColor
    } else {
        // Light theme: colored background, black text
        typeColor to Color.Black
    }
}


@Composable
internal fun MagicScreen() {
    val viewModel: MagicViewModel = koinViewModel()

    val state by viewModel.viewState.collectAsStateWithLifecycleIfAvailable()
    val sendEvent = rememberSendViewEvent(viewModel)

    MagicScreen(state, sendEvent)
}

@Composable
private fun MagicScreen(
    state: MagicViewModel.State,
    sendEvent: (MagicViewModel.Event) -> Unit
) {
    var displayedAnswer by remember { mutableStateOf(state.answer) }
    var previousAnswer by remember { mutableStateOf(state.answer) }
    var animationCounter by remember { mutableIntStateOf(0) }
    val isDark = isSystemInDarkTheme()

    // Update when answer changes from ViewModel
    LaunchedEffect(state.answer) {
        if (state.answer != displayedAnswer) {
            previousAnswer = displayedAnswer
            displayedAnswer = state.answer
            animationCounter++
        }
    }

    // Pad texts to same length for smooth transition, centered
    val maxLength = maxOf(displayedAnswer.text.length, previousAnswer.text.length)
    val targetPaddingNeeded = maxLength - displayedAnswer.text.length
    val previousPaddingNeeded = maxLength - previousAnswer.text.length

    // Center the text by adding padding on both sides
    val targetPadStart = targetPaddingNeeded / 2
    val targetPadEnd = targetPaddingNeeded - targetPadStart
    val previousPadStart = previousPaddingNeeded / 2
    val previousPadEnd = previousPaddingNeeded - previousPadStart

    val paddedTarget = " ".repeat(targetPadStart) + displayedAnswer.text + " ".repeat(targetPadEnd)
    val paddedPrevious = " ".repeat(previousPadStart) + previousAnswer.text + " ".repeat(previousPadEnd)

    val (targetBackgroundColor, targetTextColor) = getAnswerColors(displayedAnswer.type, isDark)

    // Animate colors
    val backgroundColor by animateColorAsState(
        targetValue = targetBackgroundColor,
        animationSpec = tween(durationMillis = 600, easing = LinearEasing)
    )
    val textColor by animateColorAsState(
        targetValue = targetTextColor,
        animationSpec = tween(durationMillis = 600, easing = LinearEasing)
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(backgroundColor)
            .safeContentPadding()
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                sendEvent(MagicViewModel.Event.AskQuestion)
            },
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Calculate font size based on available width and text length
            // Rough estimate: each character takes about 0.6 * fontSize in width
            val maxFontSize = 32.sp
            val estimatedCharWidth = maxFontSize.value * 0.6f
            val estimatedTextWidth = paddedTarget.length * estimatedCharWidth
            val availableWidth = maxWidth.value

            val fontSize = if (estimatedTextWidth > availableWidth) {
                // Scale down font to fit
                ((availableWidth / paddedTarget.length) / 0.6f).sp
            } else {
                maxFontSize
            }

            Row {
                paddedTarget.forEachIndexed { index, char ->
                    AnimatedCharacter(
                        targetChar = char,
                        previousChar = paddedPrevious.getOrElse(index) { ' ' },
                        index = index,
                        color = textColor,
                        animationKey = animationCounter,
                        fontSize = fontSize,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}