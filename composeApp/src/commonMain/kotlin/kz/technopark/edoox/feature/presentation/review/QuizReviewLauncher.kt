package kz.technopark.edoox.feature.presentation.review

import kotlinx.serialization.Serializable
import kz.technopark.edoox.feature.presentation.model.QuizDvo

@Serializable
class QuizReviewLauncher(
    val dvo: List<QuizDvo>,
)