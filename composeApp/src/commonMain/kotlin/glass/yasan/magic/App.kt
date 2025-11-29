package glass.yasan.magic

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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import glass.yasan.toolkit.compose.coroutines.collectAsStateWithLifecycleIfAvailable
import glass.yasan.toolkit.compose.font.rubikFontFamily
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AnimatedCharacter(
    targetChar: Char,
    previousChar: Char,
    index: Int,
    color: androidx.compose.ui.graphics.Color,
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
fun getAnswerColors(answerType: AnswerType, isDark: Boolean): Pair<Color, Color> {
    val typeColor = when (answerType) {
        AnswerType.AFFIRMATIVE -> AffirmativeColor
        AnswerType.NON_COMMITTAL -> NonCommittalColor
        AnswerType.NEGATIVE -> NegativeColor
        AnswerType.NEUTRAL -> if (isDark) Color.White else Color.Black
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
@Preview
fun App(viewModel: MagicViewModel = koinViewModel()) {
    MaterialTheme {
        val currentAnswer by viewModel.currentAnswer.collectAsStateWithLifecycleIfAvailable()
        var displayedAnswer by remember { mutableStateOf(currentAnswer) }
        var previousAnswer by remember { mutableStateOf(currentAnswer) }
        var animationCounter by remember { mutableIntStateOf(0) }
        val isDark = isSystemInDarkTheme()

        // Update when answer changes from ViewModel
        LaunchedEffect(currentAnswer) {
            if (currentAnswer != displayedAnswer) {
                previousAnswer = displayedAnswer
                displayedAnswer = currentAnswer
                animationCounter++
            }
        }

        // Pad texts to same length for smooth transition
        val maxLength = maxOf(displayedAnswer.text.length, previousAnswer.text.length)
        val paddedTarget = displayedAnswer.text.padEnd(maxLength, ' ')
        val paddedPrevious = previousAnswer.text.padEnd(maxLength, ' ')

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
                    viewModel.getNewAnswer()
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

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
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
}