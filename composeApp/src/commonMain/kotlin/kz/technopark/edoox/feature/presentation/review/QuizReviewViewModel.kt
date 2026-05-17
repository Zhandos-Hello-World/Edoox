package kz.technopark.edoox.feature.presentation.review

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kz.technopark.edoox.feature.presentation.navigation.AppRouter
import kz.technopark.edoox.shared.coreui.theme.language.AppTranslations

class QuizReviewViewModel(
    private val appTranslations: AppTranslations,
    launcher: QuizReviewLauncher,
    private val router: AppRouter,
) : QuizReviewController, ViewModel() {

    private val _uiState = MutableStateFlow(
        QuizReviewUIState(
            quiz = launcher.dvo,
            title = appTranslations.errorCorrection,
            onPrimaryButtonText = appTranslations.remembered,
            isFinished = false,

            )
    )
    val uiState: StateFlow<QuizReviewUIState> = _uiState.asStateFlow()

    override fun onCardDismissed(swipedRight: Boolean) {
        val currentQuizList = _uiState.value.quiz
        val topCard = currentQuizList.firstOrNull()

        if (topCard != null) {
            val updatedList = currentQuizList.drop(1)

            _uiState.update { currentState ->
                currentState.copy(
                    quiz = updatedList,
                    isFinished = updatedList.isEmpty(),
                    onPrimaryButtonText = if (updatedList.isEmpty()) appTranslations.backToHome else appTranslations.remembered,
                )
            }
        }
    }

    override fun onNextClicked() {
        if (_uiState.value.isFinished) {
            onBackClicked()
            return
        }

        val currentList = _uiState.value.quiz
        val updatedList = currentList.drop(1)
        val isNowFinished = updatedList.isEmpty()

        _uiState.update { currentState ->
            currentState.copy(
                quiz = updatedList,
                isFinished = isNowFinished,
                onPrimaryButtonText = if (isNowFinished) appTranslations.backToHome else appTranslations.remembered,
            )
        }
    }

    override fun onBackClicked() {
        router.pop()
    }
}