package glass.yasan.magic.feature.widget

import androidx.compose.ui.graphics.Color
import androidx.glance.unit.ColorProvider
import glass.yasan.magic.feature.answers.domain.model.Answer
import androidx.glance.color.ColorProvider as DayNightColorProvider

internal data class WidgetColors(
    val background: ColorProvider,
    val foreground: ColorProvider,
)

private val ContentLight = Color(0xFF212121)
private val ContentDark = Color(0xFFE0E0E0)
private val MidgroundDark = Color(0xFF121212)
private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF000000)

private val Success = Color(0xFF04B34F)
private val Information = Color(0xFF0057B8)
private val Caution = Color(0xFFFF9900)
private val Danger = Color(0xFFD00036)

internal fun Answer.Type.toWidgetColors(): WidgetColors = when (this) {
    Answer.Type.GENERIC -> WidgetColors(
        background = DayNightColorProvider(day = ContentLight, night = MidgroundDark),
        foreground = DayNightColorProvider(day = White, night = ContentDark),
    )
    Answer.Type.SUCCESS -> WidgetColors(
        background = DayNightColorProvider(day = Success, night = MidgroundDark),
        foreground = DayNightColorProvider(day = White, night = Success),
    )
    Answer.Type.INFO -> WidgetColors(
        background = DayNightColorProvider(day = Information, night = MidgroundDark),
        foreground = DayNightColorProvider(day = White, night = Information),
    )
    Answer.Type.CAUTION -> WidgetColors(
        background = DayNightColorProvider(day = Caution, night = MidgroundDark),
        foreground = DayNightColorProvider(day = Black, night = Caution),
    )
    Answer.Type.DANGER -> WidgetColors(
        background = DayNightColorProvider(day = Danger, night = MidgroundDark),
        foreground = DayNightColorProvider(day = White, night = Danger),
    )
}
