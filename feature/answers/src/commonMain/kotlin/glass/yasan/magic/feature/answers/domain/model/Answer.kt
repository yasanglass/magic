package glass.yasan.magic.feature.answers.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import glass.yasan.kepko.foundation.theme.KepkoTheme

public sealed interface Answer {

    public val type: Type

    @Composable
    public fun resolveText(): String

    public enum class Type(public val id: String) {
        GENERIC("generic"),
        SUCCESS("success"),
        INFO("info"),
        CAUTION("caution"),
        DANGER("danger"),
        ;


        public companion object {
            public fun fromIdOrDefault(id: String): Type = entries.firstOrNull { it.id == id } ?: GENERIC
        }

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
