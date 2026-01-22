package glass.yasan.magic.feature.answers.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import glass.yasan.magic.feature.answers.data.local.db.MagicDatabase
import org.koin.core.scope.Scope

internal actual class PlatformDatabaseDriverFactory(
    private val context: Context,
) : DatabaseDriverFactory {
    actual override fun createSqlDriver(): SqlDriver {
        return AndroidSqliteDriver(MagicDatabase.Schema, context, "magic.db")
    }
}

internal actual fun Scope.createPlatformDatabaseDriverFactory(): PlatformDatabaseDriverFactory =
    PlatformDatabaseDriverFactory(
        context = get()
    )
