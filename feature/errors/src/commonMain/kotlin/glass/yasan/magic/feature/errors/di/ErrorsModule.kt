package glass.yasan.magic.feature.errors.di

import glass.yasan.magic.feature.errors.data.ErrorReportingImpl
import glass.yasan.magic.feature.errors.domain.ErrorReporting
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Koin module for error reporting.
 */
public val errorsModule: Module = module {
    single<ErrorReporting> { ErrorReportingImpl(config = get()) }
}
