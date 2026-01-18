package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class InMemoryAnswerPackLocalDataSource : AnswerPackLocalDataSource {

    private val modificationMutex = Mutex()

    private val _answerPacks = MutableStateFlow(
        DefaultAnswerPacks.all.toImmutableList()
    )
    override val answerPacks: StateFlow<ImmutableList<AnswerPack>> =
        _answerPacks.asStateFlow()

    override suspend fun insertAnswerPack(answerPack: AnswerPack) {
        modificationMutex.withLock {
            val current = _answerPacks.value
            if (!current.contains(answerPack)) {
                _answerPacks.value = (current + answerPack).toImmutableList()
            }
        }
    }

    override suspend fun removeAnswerPack(answerPack: AnswerPack) {
        modificationMutex.withLock {
            val current = _answerPacks.value
            val updated = current.filterNot { it == answerPack }.toImmutableList()

            if (updated.isNotEmpty()) {
                _answerPacks.value = updated
            }
        }
    }
}
