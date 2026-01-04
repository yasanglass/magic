package glass.yasan.magic.feature.answers.domain.model

import androidx.compose.runtime.Composable
import glass.yasan.magic.core.resources.Res
import glass.yasan.magic.core.resources.answer_ask
import org.jetbrains.compose.resources.stringResource

public data class Answer(
    val getText: @Composable () -> String,
    val type: Type = Type.GENERIC,
) {

    public companion object {

        public val empty: Answer = Answer(
            getText = { stringResource(Res.string.answer_ask) },
            type = Type.GENERIC,
        )

    }

    public enum class Type {
        GENERIC,
        SUCCESS,
        INFO,
        CAUTION,
        DANGER,
        ;
    }

}
