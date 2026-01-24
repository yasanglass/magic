package glass.yasan.magic.di

import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify
import kotlin.test.Test

@OptIn(KoinExperimentalAPI::class)
class KoinModuleVerificationTest {

    @Test
    fun verifyKoinModule() {
        appModule.verify()
    }

}
