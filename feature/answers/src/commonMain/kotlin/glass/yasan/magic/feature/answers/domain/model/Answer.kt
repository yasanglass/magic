package glass.yasan.magic.feature.answers.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.theme.KepkoTheme

public sealed interface Answer {

    public val type: Type

    @Composable
    public fun resolveText(): String

    public enum class Type {
        GENERIC,
        SUCCESS,
        INFO,
        CAUTION,
        DANGER,
        ;

        @Composable
        public fun resolveColor(): Color = when (this) {
            GENERIC -> KepkoTheme.colors.content
            SUCCESS -> KepkoTheme.colors.success
            INFO -> KepkoTheme.colors.information
            CAUTION -> KepkoTheme.colors.caution
            DANGER -> KepkoTheme.colors.danger
        }

    }

}
