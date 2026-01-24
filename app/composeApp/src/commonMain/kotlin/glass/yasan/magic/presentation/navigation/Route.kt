package glass.yasan.magic.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    @Serializable
    data object Magic : Route

    @Serializable
    data object Settings : Route {

        @Serializable
        data object AnswerPacks : Route {

            @Serializable
            data class Edit(
                val answerPackId: String? = null,
            ) : Route

        }

        @Serializable
        data object Style : Route

        @Serializable
        data object About : Route

    }

}
