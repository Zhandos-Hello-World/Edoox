package kz.technopark.edoox.feature.presentation.quiz

import kz.technopark.edoox.feature.presentation.model.QuizDvo

data class QuizUiState(
    val title: String = "",
    val questions: List<QuizDvo> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val isQuizFinished: Boolean = false,
    val score: Int = 0,
    val isSubmitButtonEnabled: Boolean,
) {
    // Хелпер, чтобы удобно брать текущий активный вопрос на экране
    val currentQuestion: QuizDvo?
        get() = questions.getOrNull(currentQuestionIndex)
}