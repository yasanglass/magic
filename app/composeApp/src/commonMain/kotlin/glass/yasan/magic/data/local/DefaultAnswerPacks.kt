package glass.yasan.magic.data.local

import glass.yasan.magic.domain.model.Answer
import glass.yasan.magic.domain.model.Answer.Type
import glass.yasan.magic.resources.Res
import glass.yasan.magic.resources.answer_affirmative_as_i_see_it_yes
import glass.yasan.magic.resources.answer_affirmative_it_is_certain
import glass.yasan.magic.resources.answer_affirmative_it_is_decidedly_so
import glass.yasan.magic.resources.answer_affirmative_most_likely
import glass.yasan.magic.resources.answer_affirmative_outlook_good
import glass.yasan.magic.resources.answer_affirmative_signs_point_to_yes
import glass.yasan.magic.resources.answer_affirmative_without_a_doubt
import glass.yasan.magic.resources.answer_affirmative_yes
import glass.yasan.magic.resources.answer_affirmative_yes_definitely
import glass.yasan.magic.resources.answer_affirmative_you_may_rely_on_it
import glass.yasan.magic.resources.answer_negative_dont_count_on_it
import glass.yasan.magic.resources.answer_negative_my_reply_is_no
import glass.yasan.magic.resources.answer_negative_my_sources_say_no
import glass.yasan.magic.resources.answer_negative_outlook_not_so_good
import glass.yasan.magic.resources.answer_negative_very_doubtful
import glass.yasan.magic.resources.answer_non_committal_ask_again_later
import glass.yasan.magic.resources.answer_non_committal_better_not_tell_you_now
import glass.yasan.magic.resources.answer_non_committal_cannot_predict_now
import glass.yasan.magic.resources.answer_non_committal_concentrate_and_ask_again
import glass.yasan.magic.resources.answer_non_committal_reply_hazy_try_again
import glass.yasan.magic.resources.dice_1
import glass.yasan.magic.resources.dice_2
import glass.yasan.magic.resources.dice_3
import glass.yasan.magic.resources.dice_4
import glass.yasan.magic.resources.dice_5
import glass.yasan.magic.resources.dice_6
import glass.yasan.magic.resources.dice_7
import glass.yasan.magic.resources.dice_8
import glass.yasan.magic.resources.dice_9
import glass.yasan.magic.resources.dice_10
import glass.yasan.magic.resources.dice_11
import glass.yasan.magic.resources.dice_12
import glass.yasan.magic.resources.dice_13
import glass.yasan.magic.resources.dice_14
import glass.yasan.magic.resources.dice_15
import glass.yasan.magic.resources.dice_16
import glass.yasan.magic.resources.dice_17
import glass.yasan.magic.resources.dice_18
import glass.yasan.magic.resources.dice_19
import glass.yasan.magic.resources.dice_20
import org.jetbrains.compose.resources.stringResource

object DefaultAnswerPacks {

    val magicEightBallAnswers: Set<Answer> = setOf(
        Answer(
            getText = { stringResource(Res.string.answer_affirmative_as_i_see_it_yes) },
            type = Type.GREEN,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_affirmative_it_is_certain) },
            type = Type.GREEN,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_affirmative_it_is_decidedly_so) },
            type = Type.GREEN,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_affirmative_most_likely) },
            type = Type.GREEN,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_affirmative_outlook_good) },
            type = Type.GREEN,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_affirmative_signs_point_to_yes) },
            type = Type.GREEN,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_affirmative_without_a_doubt) },
            type = Type.GREEN,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_affirmative_yes) },
            type = Type.GREEN,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_affirmative_yes_definitely) },
            type = Type.GREEN,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_affirmative_you_may_rely_on_it) },
            type = Type.GREEN,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_negative_dont_count_on_it) },
            type = Type.RED,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_negative_my_reply_is_no) },
            type = Type.RED,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_negative_my_sources_say_no) },
            type = Type.RED,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_negative_outlook_not_so_good) },
            type = Type.RED,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_negative_very_doubtful) },
            type = Type.RED,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_non_committal_ask_again_later) },
            type = Type.ORANGE,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_non_committal_better_not_tell_you_now) },
            type = Type.ORANGE,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_non_committal_cannot_predict_now) },
            type = Type.ORANGE,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_non_committal_concentrate_and_ask_again) },
            type = Type.ORANGE,
        ),
        Answer(
            getText = { stringResource(Res.string.answer_non_committal_reply_hazy_try_again) },
            type = Type.ORANGE,
        ),
    )

    val d4Answers: Set<Answer> = setOf(
        Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.BLUE),
    )

    val d6Answers: Set<Answer> = setOf(
        Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_5) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_6) }, type = Type.BLUE),
    )

    val d8Answers: Set<Answer> = setOf(
        Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_5) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_6) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_7) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_8) }, type = Type.BLUE),
    )

    val d10Answers: Set<Answer> = setOf(
        Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_5) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_6) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_7) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_8) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_9) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_10) }, type = Type.BLUE),
    )

    val d12Answers: Set<Answer> = setOf(
        Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_5) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_6) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_7) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_8) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_9) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_10) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_11) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_12) }, type = Type.BLUE),
    )

    val d20Answers: Set<Answer> = setOf(
        Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_5) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_6) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_7) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_8) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_9) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_10) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_11) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_12) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_13) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_14) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_15) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_16) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_17) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_18) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_19) }, type = Type.BLUE),
        Answer(getText = { stringResource(Res.string.dice_20) }, type = Type.BLUE),
    )

}
