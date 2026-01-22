package glass.yasan.magic.feature.answers.di

import glass.yasan.magic.feature.answers.data.local.CustomAnswerPackLocalDataSource
import glass.yasan.magic.feature.answers.data.local.CustomAnswerPackLocalDataSourceImpl
import glass.yasan.magic.feature.answers.data.local.AnswerPackMapper
import glass.yasan.magic.feature.answers.data.local.DatabaseDriverFactory
import glass.yasan.magic.feature.answers.data.local.createPlatformDatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val answersPlatformModule: Module = module {
    single<DatabaseDriverFactory> { createPlatformDatabaseDriverFactory() }
    singleOf(::AnswerPackMapper)
    singleOf(::CustomAnswerPackLocalDataSourceImpl) {
        bind<CustomAnswerPackLocalDataSource>()
    }
}
