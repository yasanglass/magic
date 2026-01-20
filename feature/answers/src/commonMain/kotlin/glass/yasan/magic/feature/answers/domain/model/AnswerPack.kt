package glass.yasan.magic.feature.answers.domain.model

import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.ImmutableList

public sealed interface AnswerPack<out A : Answer> {

    public val id: String

    public val answers: ImmutableList<A>

    public val isEditable: Boolean

    @Composable
    public fun resolveName(): String

    @Composable
    public fun resolvePrompt(): String

}
