package glass.yasan.magic.feature.errors.data

import glass.yasan.magic.feature.errors.domain.ErrorReporting
import glass.yasan.magic.feature.errors.domain.ErrorReportingConfig
import io.sentry.kotlin.multiplatform.Sentry

internal class ErrorReportingImpl(
    private val config: ErrorReportingConfig,
) : ErrorReporting {

    override fun init() {
        if (config.sentryDsn.isBlank()) return

        Sentry.init { options ->
            options.dsn = config.sentryDsn
            options.release = "${config.versionName}+${config.versionCode}"
        }
    }

}
