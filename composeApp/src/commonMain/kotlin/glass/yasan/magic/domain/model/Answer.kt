package glass.yasan.magic.domain.model

data class Answer(
    val text: String,
    val type: Type
) {

    enum class Type {
        NEUTRAL,
        AFFIRMATIVE,
        NON_COMMITTAL,
        NEGATIVE
    }


}