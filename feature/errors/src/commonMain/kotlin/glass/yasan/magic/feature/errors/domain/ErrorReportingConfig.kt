package glass.yasan.magic.feature.errors.domain

public data class ErrorReportingConfig(
    val sentryDsn: String,
    val versionName: String,
    val versionCode: String,
)
