package kz.technopark.edoox.feature.presentation.subject

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kz.technopark.edoox.feature.domain.model.QuizParams
import kz.technopark.edoox.feature.presentation.loading.LoadedData
import kz.technopark.edoox.feature.presentation.loading.LoadingBehavior
import kz.technopark.edoox.feature.presentation.loading.LoadingLauncher
import kz.technopark.edoox.feature.presentation.navigation.AppRouter
import kz.technopark.edoox.feature.presentation.navigation.Screen
import kz.technopark.edoox.feature.presentation.subject.model.SubjectDvo
import kz.technopark.edoox.feature.presentation.subject.strategy.SubjectLoadingBehavior
import kz.technopark.edoox.shared.coreui.icons.BiologyIcon
import kz.technopark.edoox.shared.coreui.icons.HistoryApiIcon
import kz.technopark.edoox.shared.coreui.icons.LanguageIcon
import kz.technopark.edoox.shared.coreui.icons.MathIcon
import kz.technopark.edoox.shared.coreui.theme.language.AppTranslations

class SubjectSelectionViewModel(
    private val appTranslations: AppTranslations,
    private val router: AppRouter,
) : SubjectSelectionController, ViewModel() {
    private val _uiState = MutableStateFlow(
        SubjectSelectionUiState(
            subjects = getDefaultSubjects(
                appTranslations = appTranslations,
            ),
            selectedSubject = null,
            isContinueEnabled = false,
        )
    )
    val uiState: StateFlow<SubjectSelectionUiState> = _uiState

    override fun onBackClick() = router.pop()

    override fun onSelectSubject(subject: SubjectDvo) {
        _uiState.update {
            it.copy(
                selectedSubject = subject,
                isContinueEnabled = true,
                subjects = getDefaultSubjects(appTranslations, subject)
            )
        }
    }

    override fun onContinueClick() {
        router.navigateTo(
            Screen.Loading(
                loadingLauncher = LoadingLauncher(
                    titleText = "Загрузка материалов...",
                    loadingBehavior = SubjectLoadingBehavior(
                        subject = _uiState.value.selectedSubject?.subjectType!!
                    ) as LoadingBehavior<LoadedData>,
                )
            )
        )
    }

    private fun getDefaultSubjects(
        appTranslations: AppTranslations,
        selectedSubject: SubjectDvo? = null,
    ): List<SubjectDvo> {
        return listOf(
            SubjectDvo(
                id = "001",
                name = appTranslations.math,
                color = Color(0xFF4CAF50),
                icon = MathIcon,
                isSelected = (selectedSubject?.id == "001"),
                subjectType = QuizParams.Subject.MATH
            ),
            SubjectDvo(
                id = "002",
                name = appTranslations.historyOfKazakhstan,
                color = Color(0xFF2196F3),
                icon = HistoryApiIcon,
                isSelected = (selectedSubject?.id == "002"),
                subjectType = QuizParams.Subject.KZ_HISTORY

            ),
            SubjectDvo(
                id = "003",
                name = appTranslations.biology,
                color = Color(0xFFFF9800),
                icon = BiologyIcon,
                isSelected = (selectedSubject?.id == "003"),
                subjectType = QuizParams.Subject.BIOLOGY,
            ),
            SubjectDvo(
                id = "004",
                name = appTranslations.kazakhLanguage,
                color = Color(0xFFE91E63),
                icon = LanguageIcon,
                isSelected = (selectedSubject?.id == "004"),
                subjectType = QuizParams.Subject.KZ_LANGUAGE,
            ),
        )
    }
}