package glass.yasan.magic.feature.answers.util.preview

import glass.yasan.magic.feature.answers.domain.model.Answer
import glass.yasan.magic.feature.answers.domain.model.Answer.Type
import glass.yasan.magic.core.resources.Res
import glass.yasan.magic.core.resources.answer_affirmative_as_i_see_it_yes
import glass.yasan.magic.core.resources.answer_negative_dont_count_on_it
import glass.yasan.magic.core.resources.answer_non_committal_ask_again_later
import glass.yasan.magic.core.resources.answer_non_committal_better_not_tell_you_now
import glass.yasan.magic.core.resources.answer_non_committal_cannot_predict_now
import org.jetbrains.compose.resources.stringResource

public object PreviewAnswers {

    public val successAnswer: Answer = Answer(
        getText = { stringResource(Res.string.answer_affirmative_as_i_see_it_yes) },
        type = Type.SUCCESS,
    )

    public val dangerAnswer: Answer = Answer(
        getText = { stringResource(Res.string.answer_negative_dont_count_on_it) },
        type = Type.DANGER,
    )

    public val cautionAnswer: Answer = Answer(
        getText = { stringResource(Res.string.answer_non_committal_ask_again_later) },
        type = Type.CAUTION,
    )

    public val infoAnswer: Answer = Answer(
        getText = { stringResource(Res.string.answer_non_committal_better_not_tell_you_now) },
        type = Type.INFO,
    )

    public val genericAnswer: Answer = Answer(
        getText = { stringResource(Res.string.answer_non_committal_cannot_predict_now) },
        type = Type.GENERIC,
    )

}
