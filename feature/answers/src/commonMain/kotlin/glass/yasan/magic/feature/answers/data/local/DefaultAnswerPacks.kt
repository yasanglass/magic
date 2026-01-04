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

public object DefaultAnswerPacks {

    internal val magicEightBall: AnswerPack = AnswerPack(
        id = -1L,
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

    internal val d4: AnswerPack = AnswerPack(
        id = -2L,
        name = { stringResource(Res.string.answer_pack_d4) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }),
            Answer(getText = { stringResource(Res.string.dice_2) }),
            Answer(getText = { stringResource(Res.string.dice_3) }),
            Answer(getText = { stringResource(Res.string.dice_4) }),
        ),
    )

    internal val d6: AnswerPack = AnswerPack(
        id = -3L,
        name = { stringResource(Res.string.answer_pack_d6) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }),
            Answer(getText = { stringResource(Res.string.dice_2) }),
            Answer(getText = { stringResource(Res.string.dice_3) }),
            Answer(getText = { stringResource(Res.string.dice_4) }),
            Answer(getText = { stringResource(Res.string.dice_5) }),
            Answer(getText = { stringResource(Res.string.dice_6) }),
        ),
    )

    internal val d8: AnswerPack = AnswerPack(
        id = -4L,
        name = { stringResource(Res.string.answer_pack_d8) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }),
            Answer(getText = { stringResource(Res.string.dice_2) }),
            Answer(getText = { stringResource(Res.string.dice_3) }),
            Answer(getText = { stringResource(Res.string.dice_4) }),
            Answer(getText = { stringResource(Res.string.dice_5) }),
            Answer(getText = { stringResource(Res.string.dice_6) }),
            Answer(getText = { stringResource(Res.string.dice_7) }),
            Answer(getText = { stringResource(Res.string.dice_8) }),
        ),
    )

    internal val d10: AnswerPack = AnswerPack(
        id = -5L,
        name = { stringResource(Res.string.answer_pack_d10) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }),
            Answer(getText = { stringResource(Res.string.dice_2) }),
            Answer(getText = { stringResource(Res.string.dice_3) }),
            Answer(getText = { stringResource(Res.string.dice_4) }),
            Answer(getText = { stringResource(Res.string.dice_5) }),
            Answer(getText = { stringResource(Res.string.dice_6) }),
            Answer(getText = { stringResource(Res.string.dice_7) }),
            Answer(getText = { stringResource(Res.string.dice_8) }),
            Answer(getText = { stringResource(Res.string.dice_9) }),
            Answer(getText = { stringResource(Res.string.dice_10) }),
        ),
    )

    internal val d12: AnswerPack = AnswerPack(
        id = -6L,
        name = { stringResource(Res.string.answer_pack_d12) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }),
            Answer(getText = { stringResource(Res.string.dice_2) }),
            Answer(getText = { stringResource(Res.string.dice_3) }),
            Answer(getText = { stringResource(Res.string.dice_4) }),
            Answer(getText = { stringResource(Res.string.dice_5) }),
            Answer(getText = { stringResource(Res.string.dice_6) }),
            Answer(getText = { stringResource(Res.string.dice_7) }),
            Answer(getText = { stringResource(Res.string.dice_8) }),
            Answer(getText = { stringResource(Res.string.dice_9) }),
            Answer(getText = { stringResource(Res.string.dice_10) }),
            Answer(getText = { stringResource(Res.string.dice_11) }),
            Answer(getText = { stringResource(Res.string.dice_12) }),
        ),
    )

    internal val d20: AnswerPack = AnswerPack(
        id = -7L,
        name = { stringResource(Res.string.answer_pack_d20) },
        answers = persistentListOf(
            Answer(getText = { stringResource(Res.string.dice_1) }),
            Answer(getText = { stringResource(Res.string.dice_2) }),
            Answer(getText = { stringResource(Res.string.dice_3) }),
            Answer(getText = { stringResource(Res.string.dice_4) }),
            Answer(getText = { stringResource(Res.string.dice_5) }),
            Answer(getText = { stringResource(Res.string.dice_6) }),
            Answer(getText = { stringResource(Res.string.dice_7) }),
            Answer(getText = { stringResource(Res.string.dice_8) }),
            Answer(getText = { stringResource(Res.string.dice_9) }),
            Answer(getText = { stringResource(Res.string.dice_10) }),
            Answer(getText = { stringResource(Res.string.dice_11) }),
            Answer(getText = { stringResource(Res.string.dice_12) }),
            Answer(getText = { stringResource(Res.string.dice_13) }),
            Answer(getText = { stringResource(Res.string.dice_14) }),
            Answer(getText = { stringResource(Res.string.dice_15) }),
            Answer(getText = { stringResource(Res.string.dice_16) }),
            Answer(getText = { stringResource(Res.string.dice_17) }),
            Answer(getText = { stringResource(Res.string.dice_18) }),
            Answer(getText = { stringResource(Res.string.dice_19) }),
            Answer(getText = { stringResource(Res.string.dice_20) }),
        ),
    )

    internal val all = listOf(
        magicEightBall,
        d4,
        d6,
        d8,
        d10,
        d12,
        d20,
    )

    public val default: AnswerPack = magicEightBall

}
