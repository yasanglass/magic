package glass.yasan.magic.widget

import glass.yasan.magic.feature.widget.WidgetAnswerSource
import org.koin.dsl.bind
import org.koin.dsl.module

val widgetModule = module {
    single { WidgetAnswerSourceImpl(get(), get()) } bind WidgetAnswerSource::class
}
