package glass.yasan.magic.feature.answers.data.local

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

internal expect class PlatformDatabaseDriverFactory : DatabaseDriverFactory {

    override fun createSqlDriver(): SqlDriver

}

internal expect fun Scope.createPlatformDatabaseDriverFactory(): PlatformDatabaseDriverFactory
