package glass.yasan.magic.di

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import glass.yasan.magic.data.settings.SettingsMapper
import glass.yasan.magic.data.settings.SettingsRepositoryImpl
import glass.yasan.magic.domain.repository.SettingsRepository
import glass.yasan.magic.presentation.route.magic.MagicViewModel
import glass.yasan.toolkit.koin.toolkitModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    includes(toolkitModule)

    single<ObservableSettings> { Settings() as ObservableSettings }
    single { SettingsMapper() }
    single<SettingsRepository> { SettingsRepositoryImpl(get(), get()) }

    viewModelOf(::MagicViewModel)
}

