package glass.yasan.magic.feature.settings.di

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import glass.yasan.magic.feature.settings.data.SettingsMapper
import glass.yasan.magic.feature.settings.data.SettingsRepositoryImpl
import glass.yasan.magic.feature.settings.domain.repository.SettingsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val settingsModule = module {
    single<ObservableSettings> { Settings() as ObservableSettings }
    singleOf(::SettingsMapper)
    singleOf(::SettingsRepositoryImpl) { bind<SettingsRepository>() }
}
