package kz.technopark.edoox.feature.data.model

data class QuizRequest(
    val subject: String,
    val language: String,
    val questions: Int,
    val difficult: String,
    val expectType: String,
    val expectTypeName: String,
)