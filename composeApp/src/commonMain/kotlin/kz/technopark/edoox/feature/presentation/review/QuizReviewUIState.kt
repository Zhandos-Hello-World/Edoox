package kz.technopark.edoox.feature.presentation.review

import kz.technopark.edoox.feature.presentation.model.QuizDvo

data class QuizReviewUIState(
    val quiz: List<QuizDvo>,
    val onPrimaryButtonText: String,
    val title: String,
    val isFinished: Boolean = false,
) {
    val currentCard: QuizDvo? get() = quiz.firstOrNull()
}