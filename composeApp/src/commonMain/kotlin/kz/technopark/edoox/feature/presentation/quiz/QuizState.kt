package kz.technopark.edoox.feature.presentation.quiz

data class QuizState(
    val currentQuestionIndex: Int = 0,
    val totalQuestions: Int = 5,
    val currentQuestionText: String = "",
    val answers: List<String> = emptyList(),
    val isFinished: Boolean = false
)