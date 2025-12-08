package glass.yasan.magic.di

import glass.yasan.magic.ui.magic.MagicViewModel
import glass.yasan.toolkit.koin.toolkitModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    includes(toolkitModule)
    viewModelOf(::MagicViewModel)
}
