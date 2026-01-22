package glass.yasan.magic.feature.answers.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import glass.yasan.magic.feature.answers.data.local.db.MagicDatabase
import org.koin.core.scope.Scope

internal actual class PlatformDatabaseDriverFactory : DatabaseDriverFactory {
    actual override fun createSqlDriver(): SqlDriver {
        return NativeSqliteDriver(MagicDatabase.Schema, "magic.db")
    }
}

internal actual fun Scope.createPlatformDatabaseDriverFactory(): PlatformDatabaseDriverFactory {
    return PlatformDatabaseDriverFactory()
}
