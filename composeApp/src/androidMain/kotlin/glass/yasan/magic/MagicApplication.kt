package glass.yasan.magic

import android.app.Application
import glass.yasan.magic.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MagicApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MagicApplication)
            modules(appModule)
        }
    }
}
