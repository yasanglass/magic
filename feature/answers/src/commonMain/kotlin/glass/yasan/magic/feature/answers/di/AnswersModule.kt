package glass.yasan.magic.feature.answers.di

import glass.yasan.magic.feature.answers.data.local.AnswerPackLocalDataSource
import glass.yasan.magic.feature.answers.data.local.AnswerRepositoryImpl
import glass.yasan.magic.feature.answers.data.local.InMemoryAnswerPackLocalDataSource
import glass.yasan.magic.feature.answers.domain.repository.AnswerRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.Module
import org.koin.dsl.module

public val answersModule: Module = module {
    singleOf(::InMemoryAnswerPackLocalDataSource) {
        bind<AnswerPackLocalDataSource>()
    }
    singleOf(::AnswerRepositoryImpl) {
        bind<AnswerRepository>()
    }
}
