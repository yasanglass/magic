package glass.yasan.magic.feature.answers.domain.model

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

public data class BuiltInAnswer(
    val textStringRes: StringResource,
    override val type: Answer.Type = Answer.Type.GENERIC,
) : Answer {

    @Composable
    override fun resolveText(): String = stringResource(textStringRes)

}
