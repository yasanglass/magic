package glass.yasan.magic.feature.answers.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import glass.yasan.magic.feature.answers.data.local.db.MagicDatabase
import org.koin.core.scope.Scope
import java.io.File

internal actual class PlatformDatabaseDriverFactory : DatabaseDriverFactory {

    actual override fun createSqlDriver(): SqlDriver {
        val databasePath = getDatabasePath()
        val databaseFile = File(databasePath)
        val isNewDatabase = !databaseFile.exists()
        val driver = JdbcSqliteDriver("jdbc:sqlite:$databasePath")
        if (isNewDatabase) {
            MagicDatabase.Schema.create(driver)
        }
        return driver
    }

    private fun getDatabasePath(): String {
        val appDir = File(System.getProperty("user.home"), ".magic")
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        return File(appDir, "magic.db").absolutePath
    }

}

internal actual fun Scope.createPlatformDatabaseDriverFactory(): PlatformDatabaseDriverFactory =
    PlatformDatabaseDriverFactory()
