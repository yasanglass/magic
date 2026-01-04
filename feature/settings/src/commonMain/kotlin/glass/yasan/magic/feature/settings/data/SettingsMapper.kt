package glass.yasan.magic.feature.settings.data

import glass.yasan.magic.feature.settings.domain.model.Settings

internal class SettingsMapper {

    fun map(
        theme: String?,
        answerPackId: Long?,
    ): Settings = Settings(
        theme = Settings.Theme.fromId(theme) ?: Settings.default.theme,
        activeAnswerPackId = answerPackId ?: -1L,
    )

}
