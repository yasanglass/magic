package glass.yasan.magic.feature.answers.di

import glass.yasan.magic.feature.answers.data.provider.BuiltInAnswerPackProviderImpl
import glass.yasan.magic.feature.answers.data.repository.CustomAnswerPackRepositoryImpl
import glass.yasan.magic.feature.answers.domain.provider.BuiltInAnswerPackProvider
import glass.yasan.magic.feature.answers.domain.repository.CustomAnswerPackRepository
import glass.yasan.magic.feature.answers.domain.usecase.GetAllAnswerPacksUseCase
import glass.yasan.magic.feature.answers.domain.usecase.GetDefaultAnswerPackUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

public val answersModule: Module = module {
    includes(answersPlatformModule)
    singleOf(::BuiltInAnswerPackProviderImpl) {
        bind<BuiltInAnswerPackProvider>()
    }
    singleOf(::CustomAnswerPackRepositoryImpl) {
        bind<CustomAnswerPackRepository>()
    }
    singleOf(::GetAllAnswerPacksUseCase)
    singleOf(::GetDefaultAnswerPackUseCase)
}

internal expect val answersPlatformModule: Module
