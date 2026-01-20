package glass.yasan.magic.feature.answers.domain.model

import androidx.compose.runtime.Composable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
public data class CustomAnswer(
    val id: String = Uuid.random().toString(),
    val text: String = "",
    override val type: Answer.Type = Answer.Type.GENERIC,
) : Answer {

    @Composable
    override fun resolveText(): String = text

}
