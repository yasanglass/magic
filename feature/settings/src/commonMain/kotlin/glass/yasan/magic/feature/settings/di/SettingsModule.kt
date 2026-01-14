package glass.yasan.magic.feature.settings.di

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.observable.makeObservable
import glass.yasan.magic.feature.settings.data.SettingsMapper
import glass.yasan.magic.feature.settings.data.SettingsRepositoryImpl
import glass.yasan.magic.feature.settings.domain.repository.SettingsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

@OptIn(ExperimentalSettingsApi::class)
val settingsModule = module {
    single<ObservableSettings> { Settings().makeObservable() }
    singleOf(::SettingsMapper)
    singleOf(::SettingsRepositoryImpl) { bind<SettingsRepository>() }
}
