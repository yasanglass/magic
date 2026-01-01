package glass.yasan.magic.domain.model

import androidx.compose.runtime.Composable
import glass.yasan.magic.resources.Res
import glass.yasan.magic.resources.answer_ask
import org.jetbrains.compose.resources.stringResource

data class Answer(
    val getText: @Composable () -> String,
    val type: Type,
) {

    companion object {

        val empty: Answer = Answer(
            getText = { stringResource(Res.string.answer_ask) },
            type = GENERIC,
        )

    }

    enum class Type {
        GENERIC,
        SUCCESS,
        INFO,
        CAUTION,
        DANGER,
        ;
    }

}
