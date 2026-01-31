package glass.yasan.magic.feature.settings.data

import glass.yasan.magic.feature.settings.domain.model.Settings

internal class SettingsMapper {

    fun map(
        theme: String?,
        answerPackId: String?,
        analyticsEnabled: Boolean?,
        errorReportingEnabled: Boolean?,
    ): Settings = Settings(
        theme = Settings.Theme.fromId(theme) ?: Settings.default.theme,
        activeAnswerPackId = answerPackId ?: Settings.default.activeAnswerPackId,
        analyticsEnabled = analyticsEnabled ?: Settings.default.analyticsEnabled,
        errorReportingEnabled = errorReportingEnabled ?: Settings.default.errorReportingEnabled,
    )

}
