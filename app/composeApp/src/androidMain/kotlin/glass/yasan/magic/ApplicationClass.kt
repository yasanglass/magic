package glass.yasan.magic

import android.app.Application
import glass.yasan.magic.di.appModule
import glass.yasan.magic.feature.errors.domain.ErrorReporting
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ApplicationClass : Application() {

    private val errorReporting: ErrorReporting by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin()
        errorReporting.init()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@ApplicationClass)
            modules(appModule)
        }
    }
}
