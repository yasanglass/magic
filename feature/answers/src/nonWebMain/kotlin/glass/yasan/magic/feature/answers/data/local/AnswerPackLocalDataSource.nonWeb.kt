package glass.yasan.magic.feature.answers.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import glass.yasan.magic.feature.answers.data.local.db.MagicDatabase
import glass.yasan.magic.feature.answers.domain.model.CustomAnswerPack
import glass.yasan.toolkit.core.coroutines.DispatcherProvider
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

internal class CustomAnswerPackLocalDataSourceImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val mapper: AnswerPackMapper,
    driverFactory: DatabaseDriverFactory,
) : CustomAnswerPackLocalDataSource {

    private val database = MagicDatabase(driverFactory.createSqlDriver())
    private val queries = database.magicDatabaseQueries

    override val answerPacks: Flow<ImmutableList<CustomAnswerPack>> =
        combine(
            queries.selectAllPacks().asFlow().mapToList(context = dispatcherProvider.io),
            queries.selectAllAnswers().asFlow().mapToList(context = dispatcherProvider.io),
        ) { packs, allAnswers ->
            val answersByPackId = allAnswers.groupBy { it.pack_id }
            packs.map { packEntity ->
                val answerEntities = answersByPackId[packEntity.id].orEmpty()
                mapper.map(packEntity, answerEntities)
            }.toImmutableList()
        }.flowOn(dispatcherProvider.io)

    override suspend fun insertAnswerPack(answerPack: CustomAnswerPack) {
        withContext(dispatcherProvider.io) {
            queries.transaction {
                queries.deletePack(answerPack.id)
                queries.deleteAnswersForPack(answerPack.id)

                queries.insertPack(
                    id = answerPack.id,
                    name = answerPack.name,
                    prompt = answerPack.prompt,
                )

                answerPack.answers.forEach { answer ->
                    queries.insertAnswer(
                        id = answer.id,
                        packId = answerPack.id,
                        text = answer.text,
                        type = answer.type.id,
                    )
                }
            }
        }
    }

    override suspend fun removeAnswerPack(answerPack: CustomAnswerPack) {
        withContext(dispatcherProvider.io) {
            queries.deletePack(answerPack.id)
        }
    }
}
