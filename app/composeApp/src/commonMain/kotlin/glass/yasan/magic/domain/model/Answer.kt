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
        CONTENT,
        SUCCESS,
        INFO,
        CAUTION,
        DANGER,
        ;

        @OptIn(ExperimentalKepkoApi::class)
        @Composable
        fun getContainerColor(): Color =
            when (this) {
                EMPTY -> KepkoTheme.colors.foreground
                CONTENT -> KepkoTheme.colors.content
                SUCCESS -> KepkoTheme.colors.success
                INFO -> KepkoTheme.colors.information
                CAUTION -> KepkoTheme.colors.caution
                DANGER -> KepkoTheme.colors.danger
            }

    }

}
