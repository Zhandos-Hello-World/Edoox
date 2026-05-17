package kz.technopark.edoox.feature.presentation.review

interface QuizReviewController {
    fun onCardDismissed(swipedRight: Boolean)
    fun onNextClicked()
    fun onBackClicked()
}