package glass.yasan.magic.presentation.route.answerpacks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import glass.yasan.kepko.component.PreferenceRadioButton
import glass.yasan.kepko.component.Scaffold
import glass.yasan.magic.core.resources.Res
import glass.yasan.magic.core.resources.answer_packs
import glass.yasan.magic.feature.answers.domain.repository.AnswerRepository
import glass.yasan.magic.feature.settings.domain.model.Settings
import glass.yasan.toolkit.compose.spacer.verticalSpacerItem
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun AnswerPacksScreen(
    navController: NavHostController,
    settings: Settings,
    updateSettings: (Settings.() -> Settings) -> Unit,
) {
    val answerRepository: AnswerRepository = koinInject()
    val answerPacks by answerRepository.answerPacks.collectAsStateWithLifecycle()

    Scaffold(
        title = stringResource(Res.string.answer_packs),
        onBackClick = { navController.navigateUp() },
    ) { contentPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = contentPadding,
        ) {
            verticalSpacerItem(height = 12.dp)

            items(answerPacks.size) { index ->
                val pack = answerPacks[index]

                PreferenceRadioButton(
                    title = pack.name(),
                    selected = pack.id == settings.activeAnswerPackId,
                    onClick = {
                        updateSettings { copy(activeAnswerPackId = pack.id) }
                    },
                )

//                ButtonText(
//                    text = pack.name(),
//                    leadingIcon = null,
//                    trailingIcon = Icons.chevronForward,
//                    onClick = {},
//                )
            }
        }
    }
}
