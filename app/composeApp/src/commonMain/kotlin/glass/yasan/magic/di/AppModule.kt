package glass.yasan.magic.di

import glass.yasan.magic.feature.answers.di.answersModule
import glass.yasan.magic.feature.settings.di.settingsModule
import glass.yasan.magic.presentation.route.magic.MagicViewModel
import glass.yasan.toolkit.koin.toolkitModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    includes(
        answersModule,
        settingsModule,
        toolkitModule,
    )

    viewModelOf(::MagicViewModel)
}

