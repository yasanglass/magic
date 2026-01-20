package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.BuiltInAnswerPack
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class InMemoryAnswerPackLocalDataSource : AnswerPackLocalDataSource {

    private val modificationMutex = Mutex()

    override val builtInAnswerPacks: StateFlow<ImmutableList<BuiltInAnswerPack>> =
        MutableStateFlow(DefaultAnswerPacks.all.toImmutableList()).asStateFlow()

    private val _customAnswerPacks = MutableStateFlow<ImmutableList<CustomAnswerPack>>(
        persistentListOf()
    )
    override val customAnswerPacks: StateFlow<ImmutableList<CustomAnswerPack>> =
        _customAnswerPacks.asStateFlow()

    override suspend fun insertAnswerPack(answerPack: CustomAnswerPack) {
        modificationMutex.withLock {
            val current = _customAnswerPacks.value
            val existingIndex = current.indexOfFirst { it.id == answerPack.id }
            _customAnswerPacks.value = if (existingIndex >= 0) {
                current.mapIndexed { index, pack ->
                    if (index == existingIndex) answerPack else pack
                }.toImmutableList()
            } else {
                (current + answerPack).toImmutableList()
            }
        }
    }

    override suspend fun removeAnswerPack(answerPack: CustomAnswerPack) {
        modificationMutex.withLock {
            val current = _customAnswerPacks.value
            val updated = current.filterNot { it == answerPack }.toImmutableList()
            _customAnswerPacks.value = updated
        }
    }
}
