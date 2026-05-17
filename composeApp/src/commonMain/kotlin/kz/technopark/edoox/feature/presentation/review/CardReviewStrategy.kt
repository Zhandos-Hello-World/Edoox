package kz.technopark.edoox.feature.presentation.review

interface CardReviewStrategy {
    fun onCardDismissed(questionId: String, swipedRight: Boolean)
}