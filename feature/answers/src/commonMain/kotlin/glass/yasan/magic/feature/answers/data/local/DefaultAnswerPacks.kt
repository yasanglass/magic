package glass.yasan.magic.feature.answers.data.local

import glass.yasan.magic.feature.answers.domain.model.BuiltInAnswer
import glass.yasan.magic.feature.answers.domain.model.BuiltInAnswerPack
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
import glass.yasan.magic.core.resources.answer_pack_prompt_dice
import glass.yasan.magic.core.resources.answer_pack_prompt_magic_8_ball
import glass.yasan.magic.feature.answers.domain.model.Answer.Type.CAUTION
import glass.yasan.magic.feature.answers.domain.model.Answer.Type.DANGER
import glass.yasan.magic.feature.answers.domain.model.Answer.Type.SUCCESS

public object DefaultAnswerPacks {

    internal val magicEightBall: BuiltInAnswerPack = BuiltInAnswerPack(
        id = "magic-8-ball",
        nameStringRes = Res.string.answer_pack_magic_8_ball,
        promptStringRes = Res.string.answer_pack_prompt_magic_8_ball,
        answers = persistentListOf(
            BuiltInAnswer(textStringRes = Res.string.answer_affirmative_as_i_see_it_yes, type = SUCCESS),
            BuiltInAnswer(textStringRes = Res.string.answer_affirmative_it_is_certain, type = SUCCESS),
            BuiltInAnswer(textStringRes = Res.string.answer_affirmative_it_is_decidedly_so, type = SUCCESS),
            BuiltInAnswer(textStringRes = Res.string.answer_affirmative_most_likely, type = SUCCESS),
            BuiltInAnswer(textStringRes = Res.string.answer_affirmative_outlook_good, type = SUCCESS),
            BuiltInAnswer(textStringRes = Res.string.answer_affirmative_signs_point_to_yes, type = SUCCESS),
            BuiltInAnswer(textStringRes = Res.string.answer_affirmative_without_a_doubt, type = SUCCESS),
            BuiltInAnswer(textStringRes = Res.string.answer_affirmative_yes, type = SUCCESS),
            BuiltInAnswer(textStringRes = Res.string.answer_affirmative_yes_definitely, type = SUCCESS),
            BuiltInAnswer(textStringRes = Res.string.answer_affirmative_you_may_rely_on_it, type = SUCCESS),
            BuiltInAnswer(textStringRes = Res.string.answer_negative_dont_count_on_it, type = DANGER),
            BuiltInAnswer(textStringRes = Res.string.answer_negative_my_reply_is_no, type = DANGER),
            BuiltInAnswer(textStringRes = Res.string.answer_negative_my_sources_say_no, type = DANGER),
            BuiltInAnswer(textStringRes = Res.string.answer_negative_outlook_not_so_good, type = DANGER),
            BuiltInAnswer(textStringRes = Res.string.answer_negative_very_doubtful, type = DANGER),
            BuiltInAnswer(textStringRes = Res.string.answer_non_committal_ask_again_later, type = CAUTION),
            BuiltInAnswer(textStringRes = Res.string.answer_non_committal_better_not_tell_you_now, type = CAUTION),
            BuiltInAnswer(textStringRes = Res.string.answer_non_committal_cannot_predict_now, type = CAUTION),
            BuiltInAnswer(textStringRes = Res.string.answer_non_committal_concentrate_and_ask_again, type = CAUTION),
            BuiltInAnswer(textStringRes = Res.string.answer_non_committal_reply_hazy_try_again, type = CAUTION),
        ),
    )

    internal val d4: BuiltInAnswerPack = BuiltInAnswerPack(
        id = "d4",
        nameStringRes = Res.string.answer_pack_d4,
        promptStringRes = Res.string.answer_pack_prompt_dice,
        answers = persistentListOf(
            BuiltInAnswer(textStringRes = Res.string.dice_1),
            BuiltInAnswer(textStringRes = Res.string.dice_2),
            BuiltInAnswer(textStringRes = Res.string.dice_3),
            BuiltInAnswer(textStringRes = Res.string.dice_4),
        ),
    )

    internal val d6: BuiltInAnswerPack = BuiltInAnswerPack(
        id = "d6",
        nameStringRes = Res.string.answer_pack_d6,
        promptStringRes = Res.string.answer_pack_prompt_dice,
        answers = persistentListOf(
            BuiltInAnswer(textStringRes = Res.string.dice_1),
            BuiltInAnswer(textStringRes = Res.string.dice_2),
            BuiltInAnswer(textStringRes = Res.string.dice_3),
            BuiltInAnswer(textStringRes = Res.string.dice_4),
            BuiltInAnswer(textStringRes = Res.string.dice_5),
            BuiltInAnswer(textStringRes = Res.string.dice_6),
        ),
    )

    internal val d8: BuiltInAnswerPack = BuiltInAnswerPack(
        id = "d8",
        nameStringRes = Res.string.answer_pack_d8,
        promptStringRes = Res.string.answer_pack_prompt_dice,
        answers = persistentListOf(
            BuiltInAnswer(textStringRes = Res.string.dice_1),
            BuiltInAnswer(textStringRes = Res.string.dice_2),
            BuiltInAnswer(textStringRes = Res.string.dice_3),
            BuiltInAnswer(textStringRes = Res.string.dice_4),
            BuiltInAnswer(textStringRes = Res.string.dice_5),
            BuiltInAnswer(textStringRes = Res.string.dice_6),
            BuiltInAnswer(textStringRes = Res.string.dice_7),
            BuiltInAnswer(textStringRes = Res.string.dice_8),
        ),
    )

    internal val d10: BuiltInAnswerPack = BuiltInAnswerPack(
        id = "d10",
        nameStringRes = Res.string.answer_pack_d10,
        promptStringRes = Res.string.answer_pack_prompt_dice,
        answers = persistentListOf(
            BuiltInAnswer(textStringRes = Res.string.dice_1),
            BuiltInAnswer(textStringRes = Res.string.dice_2),
            BuiltInAnswer(textStringRes = Res.string.dice_3),
            BuiltInAnswer(textStringRes = Res.string.dice_4),
            BuiltInAnswer(textStringRes = Res.string.dice_5),
            BuiltInAnswer(textStringRes = Res.string.dice_6),
            BuiltInAnswer(textStringRes = Res.string.dice_7),
            BuiltInAnswer(textStringRes = Res.string.dice_8),
            BuiltInAnswer(textStringRes = Res.string.dice_9),
            BuiltInAnswer(textStringRes = Res.string.dice_10),
        ),
    )

    internal val d12: BuiltInAnswerPack = BuiltInAnswerPack(
        id = "d12",
        nameStringRes = Res.string.answer_pack_d12,
        promptStringRes = Res.string.answer_pack_prompt_dice,
        answers = persistentListOf(
            BuiltInAnswer(textStringRes = Res.string.dice_1),
            BuiltInAnswer(textStringRes = Res.string.dice_2),
            BuiltInAnswer(textStringRes = Res.string.dice_3),
            BuiltInAnswer(textStringRes = Res.string.dice_4),
            BuiltInAnswer(textStringRes = Res.string.dice_5),
            BuiltInAnswer(textStringRes = Res.string.dice_6),
            BuiltInAnswer(textStringRes = Res.string.dice_7),
            BuiltInAnswer(textStringRes = Res.string.dice_8),
            BuiltInAnswer(textStringRes = Res.string.dice_9),
            BuiltInAnswer(textStringRes = Res.string.dice_10),
            BuiltInAnswer(textStringRes = Res.string.dice_11),
            BuiltInAnswer(textStringRes = Res.string.dice_12),
        ),
    )

    internal val d20: BuiltInAnswerPack = BuiltInAnswerPack(
        id = "d20",
        nameStringRes = Res.string.answer_pack_d20,
        promptStringRes = Res.string.answer_pack_prompt_dice,
        answers = persistentListOf(
            BuiltInAnswer(textStringRes = Res.string.dice_1),
            BuiltInAnswer(textStringRes = Res.string.dice_2),
            BuiltInAnswer(textStringRes = Res.string.dice_3),
            BuiltInAnswer(textStringRes = Res.string.dice_4),
            BuiltInAnswer(textStringRes = Res.string.dice_5),
            BuiltInAnswer(textStringRes = Res.string.dice_6),
            BuiltInAnswer(textStringRes = Res.string.dice_7),
            BuiltInAnswer(textStringRes = Res.string.dice_8),
            BuiltInAnswer(textStringRes = Res.string.dice_9),
            BuiltInAnswer(textStringRes = Res.string.dice_10),
            BuiltInAnswer(textStringRes = Res.string.dice_11),
            BuiltInAnswer(textStringRes = Res.string.dice_12),
            BuiltInAnswer(textStringRes = Res.string.dice_13),
            BuiltInAnswer(textStringRes = Res.string.dice_14),
            BuiltInAnswer(textStringRes = Res.string.dice_15),
            BuiltInAnswer(textStringRes = Res.string.dice_16),
            BuiltInAnswer(textStringRes = Res.string.dice_17),
            BuiltInAnswer(textStringRes = Res.string.dice_18),
            BuiltInAnswer(textStringRes = Res.string.dice_19),
            BuiltInAnswer(textStringRes = Res.string.dice_20),
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

    public val default: BuiltInAnswerPack = magicEightBall

}
