package glass.yasan.magic.feature.answers.domain.model

import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.ImmutableList

public data class CustomAnswerPack(
    override val id: String,
    val name: String,
    val prompt: String,
    override val answers: ImmutableList<CustomAnswer>,
) : AnswerPack<CustomAnswer> {

    @Composable
    override fun resolveName(): String = name

    @Composable
    override fun resolvePrompt(): String = prompt

    override val isEditable: Boolean = true

}
