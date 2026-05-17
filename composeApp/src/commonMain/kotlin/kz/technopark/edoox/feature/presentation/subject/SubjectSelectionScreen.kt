package kz.technopark.edoox.feature.presentation.subject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edoox.composeapp.generated.resources.Res
import edoox.composeapp.generated.resources.img_boy
import kz.technopark.edoox.feature.domain.model.QuizParams
import kz.technopark.edoox.feature.presentation.subject.model.SubjectDvo
import kz.technopark.edoox.feature.presentation.subject.view.SubjectCard
import kz.technopark.edoox.shared.coreui.icons.BiologyIcon
import kz.technopark.edoox.shared.coreui.icons.HistoryApiIcon
import kz.technopark.edoox.shared.coreui.icons.LanguageIcon
import kz.technopark.edoox.shared.coreui.icons.MathIcon
import kz.technopark.edoox.shared.coreui.theme.EdooxTheme
import kz.technopark.edoox.shared.coreui.theme.language.AppStrings
import kz.technopark.edoox.shared.coreui.view.NavBar
import kz.technopark.edoox.shared.coreui.view.PrimaryButton
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SubjectSelectionScreenEntryPoint() {
    val appTranslations = AppStrings.current
    val viewModel: SubjectSelectionViewModel = koinViewModel { parametersOf(appTranslations) }
    val uiState by viewModel.uiState.collectAsState()

    SubjectSelectionScreen(
        uiState = uiState,
        controller = viewModel,
    )
}

@Composable
fun SubjectSelectionScreen(
    uiState: SubjectSelectionUiState,
    controller: SubjectSelectionController,
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NavBar(
                title = AppStrings.current.chooseSubject,
                showBackButton = true,
                onBackClick = controller::onBackClick,
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        content = { paddingValues ->
            SubjectSelectionContent(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                uiState = uiState,
                controller = controller,
            )
        },
    )


}

@Composable
fun SubjectSelectionContent(
    modifier: Modifier,
    uiState: SubjectSelectionUiState,
    controller: SubjectSelectionController,
) {
    Column(
        modifier = modifier.background(
            MaterialTheme.colorScheme.surface,
        ).padding(16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(uiState.subjects) { subject ->
                SubjectCard(
                    subject = subject,
                    onClick = controller::onSelectSubject,
                )
            }
        }

        Spacer(
            modifier = Modifier.weight(1F),
        )

        Image(
            painter = painterResource(Res.drawable.img_boy),
            contentDescription = null,
            modifier = Modifier.height(200.dp)
        )

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = AppStrings.current.continueText,
            onClick = controller::onContinueClick,
            enabled = uiState.isContinueEnabled,
        )
    }
}

@Preview
@Composable
fun SubjectSelectionScreenPreview() = EdooxTheme {
    SubjectSelectionScreen(
        uiState = SubjectSelectionUiState(
            subjects = listOf(
                SubjectDvo(
                    id = "001",
                    name = "Математика",
                    color = Color(0xFF4CAF50),
                    icon = MathIcon,
                    isSelected = true,
                    subjectType = QuizParams.Subject.MATH
                ),
                SubjectDvo(
                    id = "001",
                    name = "История\nКазахстана",
                    color = Color(0xFF2196F3),
                    icon = HistoryApiIcon,
                    isSelected = false,
                    subjectType = QuizParams.Subject.MATH
                ),
                SubjectDvo(
                    id = "001",
                    name = "Биология",
                    color = Color(0xFFFF9800),
                    icon = BiologyIcon,
                    isSelected = false,
                    subjectType = QuizParams.Subject.MATH
                ),
                SubjectDvo(
                    id = "001",
                    name = "Казахский язык",
                    color = Color(0xFFE91E63),
                    icon = LanguageIcon,
                    isSelected = false,
                    subjectType = QuizParams.Subject.MATH
                ),
            ),
            selectedSubject = null,
            isContinueEnabled = false,
        ),
        controller = object : SubjectSelectionController {
            override fun onBackClick() = Unit
            override fun onSelectSubject(subject: SubjectDvo) = Unit
            override fun onContinueClick() = Unit
        },
    )
}