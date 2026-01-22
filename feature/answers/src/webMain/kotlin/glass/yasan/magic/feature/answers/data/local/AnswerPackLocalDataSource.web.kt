package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * In-memory implementation of [CustomAnswerPackLocalDataSource] for web.
 */
internal class CustomAnswerPackLocalDataSourceImpl : CustomAnswerPackLocalDataSource {

    private val _customAnswerPacks = MutableStateFlow<ImmutableList<CustomAnswerPack>>(persistentListOf())
    override val answerPacks: Flow<ImmutableList<CustomAnswerPack>> = _customAnswerPacks

    override suspend fun insertAnswerPack(answerPack: CustomAnswerPack) {
        _customAnswerPacks.update { currentPacks ->
            currentPacks
                .filter { it.id != answerPack.id }
                .plus(answerPack)
                .toImmutableList()
        }
    }

    override suspend fun removeAnswerPack(answerPack: CustomAnswerPack) {
        _customAnswerPacks.update { currentPacks ->
            currentPacks
                .filter { it.id != answerPack.id }
                .toImmutableList()
        }
    }

}
