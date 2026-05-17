package kz.technopark.edoox.feature.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizChoiceResponseDto(
    @SerialName("questions")
    val questions: List<ChoiceQuizDto>
) {
    @Serializable
    data class ChoiceQuizDto(
        @SerialName("question") val question: String,
        @SerialName("explanation") val explanation: String,
        @SerialName("answers") val answers: List<AnswerDto>
    )

    @Serializable
    data class AnswerDto(
        @SerialName("answer") val answer: String,
        @SerialName("correct") val correct: Boolean,
    )
}