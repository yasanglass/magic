package glass.yasan.magic.feature.answers.test

import glass.yasan.toolkit.core.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher

internal class TestDispatcherProvider(
    private val testDispatcher: TestDispatcher = StandardTestDispatcher(),
) : DispatcherProvider {

    override val default: CoroutineDispatcher = testDispatcher

    override val io: CoroutineDispatcher = testDispatcher

    override val main: CoroutineDispatcher = testDispatcher

    override val unconfined: CoroutineDispatcher = testDispatcher

}
