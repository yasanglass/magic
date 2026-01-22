package glass.yasan.magic.feature.answers.data.local

import app.cash.sqldelight.db.SqlDriver

internal interface DatabaseDriverFactory {

    fun createSqlDriver(): SqlDriver

}
