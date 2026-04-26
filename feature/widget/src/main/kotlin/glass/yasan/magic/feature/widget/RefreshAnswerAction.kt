package glass.yasan.magic.feature.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import org.koin.core.context.GlobalContext

class RefreshAnswerAction : ActionCallback {

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters,
    ) {
        val answer = GlobalContext.get().get<WidgetAnswerSource>().resolveNewAnswer()

        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId) { prefs ->
            prefs.toMutablePreferences().apply {
                this[MagicWidget.answerTextKey] = answer.text
                this[MagicWidget.answerTypeKey] = answer.type.name
            }
        }

        MagicWidget().update(context, glanceId)
    }
}
