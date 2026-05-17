package kz.technopark.edoox.feature.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizFillAnswerResponseDto(
    @SerialName("questions") val questions: List<QuizFillAnswerDto>,
) {

    @Serializable
    data class QuizFillAnswerDto(
        @SerialName("question") val question: String,
        @SerialName("correct_answer") val correctAnswer: String,
        @SerialName("explanation") val explanation: String,
    )
}