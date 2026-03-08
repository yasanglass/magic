package glass.yasan.magic.feature.settings.domain.model

data class Settings(
    val activeAnswerPackId: String,
) {

    companion object {
        val default: Settings = Settings(
            activeAnswerPackId = "magic-8-ball",
        )
    }

}
