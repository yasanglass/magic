package glass.yasan.magic.feature.answers.domain.model

import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

public data class BuiltInAnswerPack(
    override val id: String,
    val nameStringRes: StringResource,
    val promptStringRes: StringResource,
    override val answers: ImmutableList<BuiltInAnswer>,
) : AnswerPack<BuiltInAnswer> {

    @Composable
    override fun resolveName(): String = stringResource(nameStringRes)

    @Composable
    override fun resolvePrompt(): String = stringResource(promptStringRes)

    override val isEditable: Boolean = false

}
