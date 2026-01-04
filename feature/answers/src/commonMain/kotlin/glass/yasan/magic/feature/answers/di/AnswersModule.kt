package glass.yasan.magic.feature.answers.di

import glass.yasan.magic.feature.answers.domain.usecase.GetNewAnswerUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.Module
import org.koin.dsl.module

public val answersModule: Module = module {
    singleOf(::GetNewAnswerUseCase)
}
