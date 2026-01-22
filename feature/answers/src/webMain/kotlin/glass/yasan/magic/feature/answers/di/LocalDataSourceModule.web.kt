package glass.yasan.magic.feature.answers.di

import glass.yasan.magic.feature.answers.data.local.CustomAnswerPackLocalDataSource
import glass.yasan.magic.feature.answers.data.local.CustomAnswerPackLocalDataSourceImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val answersPlatformModule: Module = module {
    singleOf(::CustomAnswerPackLocalDataSourceImpl) {
        bind<CustomAnswerPackLocalDataSource>()
    }
}
