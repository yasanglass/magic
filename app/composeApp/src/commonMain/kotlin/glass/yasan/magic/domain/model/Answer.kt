package glass.yasan.magic.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.theme.KepkoTheme
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
            type = EMPTY,
        )

    }

    enum class Type {
        EMPTY,
        GENERIC,
        GREEN,
        BLUE,
        ORANGE,
        RED,
        ;

        @OptIn(ExperimentalKepkoApi::class)
        @Composable
        fun getContainerColor(): Color =
            when (this) {
                EMPTY -> KepkoTheme.colors.foreground
                GENERIC -> KepkoTheme.colors.content
                GREEN -> KepkoTheme.colors.success
                BLUE -> KepkoTheme.colors.information
                ORANGE -> KepkoTheme.colors.caution
                RED -> KepkoTheme.colors.danger
            }

    }

}
