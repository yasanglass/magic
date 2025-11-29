package glass.yasan.magic.di

import glass.yasan.magic.MagicViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MagicViewModel)
}
