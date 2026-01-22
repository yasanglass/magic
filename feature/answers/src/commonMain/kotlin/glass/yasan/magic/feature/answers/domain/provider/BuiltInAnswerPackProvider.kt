package glass.yasan.magic.feature.answers.domain.provider

import glass.yasan.magic.feature.answers.domain.model.BuiltInAnswerPack

public interface BuiltInAnswerPackProvider {

    public fun getAll(): List<BuiltInAnswerPack>

    public fun getDefault(): BuiltInAnswerPack

}
