package glass.yasan.magic.test

import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import glass.yasan.magic.feature.answers.domain.repository.CustomAnswerPackRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

internal class FakeCustomAnswerPackRepository : CustomAnswerPackRepository {

    private val _answerPacks = MutableStateFlow<ImmutableList<CustomAnswerPack>>(persistentListOf())

    override val answerPacks: Flow<ImmutableList<CustomAnswerPack>> = _answerPacks

    val currentPacks: ImmutableList<CustomAnswerPack> get() = _answerPacks.value

    override suspend fun insertAnswerPack(answerPack: CustomAnswerPack) {
        _answerPacks.update { current ->
            val existingIndex = current.indexOfFirst { it.id == answerPack.id }
            if (existingIndex >= 0) {
                current.toMutableList().apply { set(existingIndex, answerPack) }.toImmutableList()
            } else {
                (current + answerPack).toImmutableList()
            }
        }
    }

    override suspend fun removeAnswerPack(answerPack: CustomAnswerPack) {
        _answerPacks.update { current ->
            current.filter { it.id != answerPack.id }.toImmutableList()
        }
    }

    fun setInitialPacks(packs: List<CustomAnswerPack>) {
        _answerPacks.value = packs.toImmutableList()
    }

}
