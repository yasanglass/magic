package glass.yasan.magic.di

import glass.yasan.magic.domain.usecase.GetActiveAnswerPackUseCase
import glass.yasan.magic.domain.usecase.GetNewAnswerUseCase
import glass.yasan.magic.feature.answers.di.answersModule
import glass.yasan.magic.feature.settings.di.settingsModule
import glass.yasan.magic.presentation.route.magic.MagicViewModel
import glass.yasan.toolkit.koin.toolkitModule
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    includes(
        toolkitModule,
        answersModule,
        settingsModule,
    )

    viewModelOf(::MagicViewModel)

    singleOf(::GetNewAnswerUseCase)
    singleOf(::GetActiveAnswerPackUseCase)
}

