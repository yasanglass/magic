package glass.yasan.magic.feature.answers.domain.model

import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.ImmutableList

public data class AnswerPack(
    val name: @Composable () -> String,
    val answers: ImmutableList<Answer>,
)
