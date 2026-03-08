package glass.yasan.magic.feature.settings.data

import glass.yasan.magic.feature.settings.domain.model.Settings

internal class SettingsMapper {

    fun map(
        answerPackId: String?,
    ): Settings = Settings(
        activeAnswerPackId = answerPackId ?: Settings.default.activeAnswerPackId,
    )

}
