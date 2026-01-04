package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class InMemoryAnswerPackLocalDataSource : AnswerPackLocalDataSource {

    private val _answerPacks = MutableStateFlow(
        DefaultAnswerPacks.all.toImmutableList()
    )
    override val answerPacks: StateFlow<ImmutableList<AnswerPack>> =
        _answerPacks.asStateFlow()

    override fun insertAnswerPack(answerPack: AnswerPack) {
        val current = _answerPacks.value
        if (!current.contains(answerPack)) {
            _answerPacks.value = (current + answerPack).toImmutableList()
        }
    }

    override fun removeAnswerPack(answerPack: AnswerPack) {
        val current = _answerPacks.value
        val updated = current.filterNot { it == answerPack }.toImmutableList()

        if (updated.isNotEmpty()) {
            _answerPacks.value = updated
        }
    }
}
