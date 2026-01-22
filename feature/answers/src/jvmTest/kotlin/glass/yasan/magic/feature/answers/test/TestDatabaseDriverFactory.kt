package glass.yasan.magic.feature.answers.test

import app.cash.sqldelight.db.SqlDriver
import glass.yasan.magic.feature.answers.data.local.DatabaseDriverFactory

internal class TestDatabaseDriverFactory(
    private val driver: SqlDriver,
) : DatabaseDriverFactory {

    override fun createSqlDriver(): SqlDriver = driver

}
