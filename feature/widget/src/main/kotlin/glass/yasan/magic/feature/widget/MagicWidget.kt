package glass.yasan.magic.feature.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import glass.yasan.magic.feature.answers.domain.model.Answer
import org.koin.core.context.GlobalContext

class MagicWidget : GlanceAppWidget() {

    override val stateDefinition = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val initialPrompt = GlobalContext.get().get<WidgetAnswerSource>().resolveInitialPrompt()

        provideContent {
            WidgetContent(initialPrompt = initialPrompt)
        }
    }

    @Composable
    private fun WidgetContent(initialPrompt: String) {
        val prefs = currentState<Preferences>()
        val text = prefs[answerTextKey] ?: initialPrompt
        val type = prefs[answerTypeKey]
            ?.let { name -> runCatching { Answer.Type.valueOf(name) }.getOrNull() }
            ?: Answer.Type.GENERIC
        val colors = type.toWidgetColors()

        Box(
            contentAlignment = Alignment.Center,
            modifier = GlanceModifier
                .fillMaxSize()
                .cornerRadius(20.dp)
                .background(colors.background)
                .clickable(actionRunCallback<RefreshAnswerAction>())
                .padding(16.dp),
        ) {
            Text(
                text = text,
                style = TextStyle(
                    color = colors.foreground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }

    internal companion object {
        val answerTextKey = stringPreferencesKey("answer_text")
        val answerTypeKey = stringPreferencesKey("answer_type")
    }
}
