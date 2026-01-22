package glass.yasan.magic.feature.answers.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import glass.yasan.magic.feature.answers.data.local.db.MagicDatabase
import glass.yasan.magic.feature.answers.domain.model.Answer
import glass.yasan.magic.feature.answers.domain.model.CustomAnswer
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import glass.yasan.magic.feature.answers.test.TestDatabaseDriverFactory
import glass.yasan.magic.feature.answers.test.TestDispatcherProvider
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class CustomAnswerPackLocalDataSourceTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var driver: SqlDriver
    private lateinit var dataSource: CustomAnswerPackLocalDataSourceImpl

    @BeforeTest
    fun setUp() {
        driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        MagicDatabase.Schema.create(driver)
        driver.execute(null, "PRAGMA foreign_keys = ON;", 0)

        dataSource = CustomAnswerPackLocalDataSourceImpl(
            dispatcherProvider = TestDispatcherProvider(testDispatcher),
            mapper = AnswerPackMapper(),
            driverFactory = TestDatabaseDriverFactory(driver),
        )
    }

    @AfterTest
    fun tearDown() {
        driver.close()
    }

    @Test
    fun whenInsertNewPack_thenPackAppearsInFlow() = testScope.runTest {
        // given
        val pack = createTestPack(
            id = "pack-1",
            name = "Test Pack",
            prompt = "Test Prompt",
            answers = persistentListOf(
                CustomAnswer(id = "answer-1", text = "Yes", type = Answer.Type.SUCCESS),
                CustomAnswer(id = "answer-2", text = "No", type = Answer.Type.DANGER),
            ),
        )

        // when
        dataSource.insertAnswerPack(pack)

        // then
        val packs = dataSource.answerPacks.first()
        assertEquals(1, packs.size)
        assertEquals(pack.id, packs[0].id)
        assertEquals(pack.name, packs[0].name)
        assertEquals(pack.prompt, packs[0].prompt)
    }

    @Test
    fun whenInsertPackWithAnswers_thenAnswersAreGroupedCorrectly() = testScope.runTest {
        // given
        val answers = persistentListOf(
            CustomAnswer(id = "answer-1", text = "Yes", type = Answer.Type.SUCCESS),
            CustomAnswer(id = "answer-2", text = "No", type = Answer.Type.DANGER),
            CustomAnswer(id = "answer-3", text = "Maybe", type = Answer.Type.INFO),
        )
        val pack = createTestPack(
            id = "pack-1",
            name = "Test Pack",
            prompt = "Test Prompt",
            answers = answers,
        )

        // when
        dataSource.insertAnswerPack(pack)

        // then
        val packs = dataSource.answerPacks.first()
        assertEquals(1, packs.size)
        assertEquals(3, packs[0].answers.size)
        assertEquals("Yes", packs[0].answers[0].text)
        assertEquals(Answer.Type.SUCCESS, packs[0].answers[0].type)
        assertEquals("No", packs[0].answers[1].text)
        assertEquals(Answer.Type.DANGER, packs[0].answers[1].type)
        assertEquals("Maybe", packs[0].answers[2].text)
        assertEquals(Answer.Type.INFO, packs[0].answers[2].type)
    }

    @Test
    fun givenExistingPack_whenInsertWithSameId_thenPackIsUpdated() = testScope.runTest {
        // given
        val originalPack = createTestPack(
            id = "pack-1",
            name = "Original Name",
            prompt = "Original Prompt",
            answers = persistentListOf(
                CustomAnswer(id = "answer-1", text = "Original Answer"),
            ),
        )
        dataSource.insertAnswerPack(originalPack)

        val updatedPack = createTestPack(
            id = "pack-1",
            name = "Updated Name",
            prompt = "Updated Prompt",
            answers = persistentListOf(
                CustomAnswer(id = "answer-2", text = "New Answer 1"),
                CustomAnswer(id = "answer-3", text = "New Answer 2"),
            ),
        )

        // when
        dataSource.insertAnswerPack(updatedPack)

        // then
        val packs = dataSource.answerPacks.first()
        assertEquals(1, packs.size)
        assertEquals("Updated Name", packs[0].name)
        assertEquals("Updated Prompt", packs[0].prompt)
        assertEquals(2, packs[0].answers.size)
        assertEquals("New Answer 1", packs[0].answers[0].text)
        assertEquals("New Answer 2", packs[0].answers[1].text)
    }

    @Test
    fun givenExistingPack_whenRemove_thenPackDisappearsFromFlow() = testScope.runTest {
        // given
        val pack = createTestPack(
            id = "pack-1",
            name = "Test Pack",
            prompt = "Test Prompt",
        )
        dataSource.insertAnswerPack(pack)

        val packsBeforeRemove = dataSource.answerPacks.first()
        assertEquals(1, packsBeforeRemove.size)

        // when
        dataSource.removeAnswerPack(pack)

        // then
        val packsAfterRemove = dataSource.answerPacks.first()
        assertTrue(packsAfterRemove.isEmpty())
    }

    @Test
    fun givenPackWithAnswers_whenRemove_thenAnswersAreCascadeDeleted() = testScope.runTest {
        // given
        val pack = createTestPack(
            id = "pack-1",
            name = "Test Pack",
            prompt = "Test Prompt",
            answers = persistentListOf(
                CustomAnswer(id = "answer-1", text = "Answer 1"),
                CustomAnswer(id = "answer-2", text = "Answer 2"),
            ),
        )
        dataSource.insertAnswerPack(pack)

        // when
        dataSource.removeAnswerPack(pack)

        // then
        val packs = dataSource.answerPacks.first()
        assertTrue(packs.isEmpty())

        // Verify answers are also deleted (no orphan answers)
        val database = MagicDatabase(driver)
        val orphanAnswers = database.magicDatabaseQueries.selectAllAnswers().executeAsList()
        assertTrue(orphanAnswers.isEmpty())
    }

    @Test
    fun whenDatabaseEmpty_thenFlowEmitsEmptyList() = testScope.runTest {
        // when
        val packs = dataSource.answerPacks.first()

        // then
        assertTrue(packs.isEmpty())
    }

    @Test
    fun whenMultiplePacksInserted_thenFlowEmitsAllPacks() = testScope.runTest {
        // given
        val pack1 = createTestPack(
            id = "pack-1",
            name = "Pack 1",
            prompt = "Prompt 1",
        )
        val pack2 = createTestPack(
            id = "pack-2",
            name = "Pack 2",
            prompt = "Prompt 2",
        )
        val pack3 = createTestPack(
            id = "pack-3",
            name = "Pack 3",
            prompt = "Prompt 3",
        )

        // when
        dataSource.insertAnswerPack(pack1)
        dataSource.insertAnswerPack(pack2)
        dataSource.insertAnswerPack(pack3)

        // then
        val packs = dataSource.answerPacks.first()
        assertEquals(3, packs.size)
        assertTrue(packs.any { it.id == "pack-1" })
        assertTrue(packs.any { it.id == "pack-2" })
        assertTrue(packs.any { it.id == "pack-3" })
    }

    private fun createTestPack(
        id: String,
        name: String,
        prompt: String,
        answers: kotlinx.collections.immutable.ImmutableList<CustomAnswer> = persistentListOf(
            CustomAnswer(id = "default-answer-1", text = "Default Answer"),
        ),
    ): CustomAnswerPack = CustomAnswerPack(
        id = id,
        name = name,
        prompt = prompt,
        answers = answers,
    )

}
