package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.Answer
import glass.yasan.magic.feature.answers.domain.model.Answer.Type
import glass.yasan.magic.feature.answers.domain.model.AnswerPack
import glass.yasan.magic.core.resources.Res
import kotlinx.collections.immutable.persistentListOf
import glass.yasan.magic.core.resources.answer_affirmative_as_i_see_it_yes
import glass.yasan.magic.core.resources.answer_affirmative_it_is_certain
import glass.yasan.magic.core.resources.answer_affirmative_it_is_decidedly_so
import glass.yasan.magic.core.resources.answer_affirmative_most_likely
import glass.yasan.magic.core.resources.answer_affirmative_outlook_good
import glass.yasan.magic.core.resources.answer_affirmative_signs_point_to_yes
import glass.yasan.magic.core.resources.answer_affirmative_without_a_doubt
import glass.yasan.magic.core.resources.answer_affirmative_yes
import glass.yasan.magic.core.resources.answer_affirmative_yes_definitely
import glass.yasan.magic.core.resources.answer_affirmative_you_may_rely_on_it
import glass.yasan.magic.core.resources.answer_negative_dont_count_on_it
import glass.yasan.magic.core.resources.answer_negative_my_reply_is_no
import glass.yasan.magic.core.resources.answer_negative_my_sources_say_no
import glass.yasan.magic.core.resources.answer_negative_outlook_not_so_good
import glass.yasan.magic.core.resources.answer_negative_very_doubtful
import glass.yasan.magic.core.resources.answer_non_committal_ask_again_later
import glass.yasan.magic.core.resources.answer_non_committal_better_not_tell_you_now
import glass.yasan.magic.core.resources.answer_non_committal_cannot_predict_now
import glass.yasan.magic.core.resources.answer_non_committal_concentrate_and_ask_again
import glass.yasan.magic.core.resources.answer_non_committal_reply_hazy_try_again
import glass.yasan.magic.core.resources.dice_1
import glass.yasan.magic.core.resources.dice_10
import glass.yasan.magic.core.resources.dice_11
import glass.yasan.magic.core.resources.dice_12
import glass.yasan.magic.core.resources.dice_13
import glass.yasan.magic.core.resources.dice_14
import glass.yasan.magic.core.resources.dice_15
import glass.yasan.magic.core.resources.dice_16
import glass.yasan.magic.core.resources.dice_17
import glass.yasan.magic.core.resources.dice_18
import glass.yasan.magic.core.resources.dice_19
import glass.yasan.magic.core.resources.dice_2
import glass.yasan.magic.core.resources.dice_20
import glass.yasan.magic.core.resources.dice_3
import glass.yasan.magic.core.resources.dice_4
import glass.yasan.magic.core.resources.dice_5
import glass.yasan.magic.core.resources.dice_6
import glass.yasan.magic.core.resources.dice_7
import glass.yasan.magic.core.resources.dice_8
import glass.yasan.magic.core.resources.dice_9
import glass.yasan.magic.core.resources.answer_pack_magic_8_ball
import glass.yasan.magic.core.resources.answer_pack_d4
import glass.yasan.magic.core.resources.answer_pack_d6
import glass.yasan.magic.core.resources.answer_pack_d8
import glass.yasan.magic.core.resources.answer_pack_d10
import glass.yasan.magic.core.resources.answer_pack_d12
import glass.yasan.magic.core.resources.answer_pack_d20
import org.jetbrains.compose.resources.stringResource

internal object DefaultAnswerPacks {

    val magicEightBall: AnswerPack = AnswerPack(
        name = { stringResource(Res.string.answer_pack_magic_8_ball) },
        answers = persistentListOf(
            Answer(
                getText = { stringResource(Res.string.answer_affirmative_as_i_see_it_yes) },
                type = Type.SUCCESS,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_affirmative_it_is_certain) },
                type = Type.SUCCESS,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_affirmative_it_is_decidedly_so) },
                type = Type.SUCCESS,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_affirmative_most_likely) },
                type = Type.SUCCESS,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_affirmative_outlook_good) },
                type = Type.SUCCESS,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_affirmative_signs_point_to_yes) },
                type = Type.SUCCESS,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_affirmative_without_a_doubt) },
                type = Type.SUCCESS,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_affirmative_yes) },
                type = Type.SUCCESS,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_affirmative_yes_definitely) },
                type = Type.SUCCESS,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_affirmative_you_may_rely_on_it) },
                type = Type.SUCCESS,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_negative_dont_count_on_it) },
                type = Type.DANGER,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_negative_my_reply_is_no) },
                type = Type.DANGER,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_negative_my_sources_say_no) },
                type = Type.DANGER,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_negative_outlook_not_so_good) },
                type = Type.DANGER,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_negative_very_doubtful) },
                type = Type.DANGER,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_non_committal_ask_again_later) },
                type = Type.CAUTION,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_non_committal_better_not_tell_you_now) },
                type = Type.CAUTION,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_non_committal_cannot_predict_now) },
                type = Type.CAUTION,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_non_committal_concentrate_and_ask_again) },
                type = Type.CAUTION,
            ),
            Answer(
                getText = { stringResource(Res.string.answer_non_committal_reply_hazy_try_again) },
                type = Type.CAUTION,
            ),
        ),
    )

    val d4: AnswerPack = AnswerPack(
        name = { stringResource(Res.string.answer_pack_d4) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.INFO),
        ),
    )

    val d6: AnswerPack = AnswerPack(
        name = { stringResource(Res.string.answer_pack_d6) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_5) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_6) }, type = Type.INFO),
        ),
    )

    val d8: AnswerPack = AnswerPack(
        name = { stringResource(Res.string.answer_pack_d8) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_5) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_6) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_7) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_8) }, type = Type.INFO),
        ),
    )

    val d10: AnswerPack = AnswerPack(
        name = { stringResource(Res.string.answer_pack_d10) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_5) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_6) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_7) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_8) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_9) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_10) }, type = Type.INFO),
        ),
    )

    val d12: AnswerPack = AnswerPack(
        name = { stringResource(Res.string.answer_pack_d12) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_5) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_6) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_7) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_8) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_9) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_10) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_11) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_12) }, type = Type.INFO),
        ),
    )

    val d20: AnswerPack = AnswerPack(
        name = { stringResource(Res.string.answer_pack_d20) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_2) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_3) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_4) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_5) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_6) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_7) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_8) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_9) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_10) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_11) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_12) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_13) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_14) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_15) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_16) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_17) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_18) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_19) }, type = Type.INFO),
            Answer(getText = { stringResource(Res.string.dice_20) }, type = Type.INFO),
        ),
    )

}
