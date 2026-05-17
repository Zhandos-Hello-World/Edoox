package kz.technopark.edoox.feature.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuizDragAndDropResponseDto(
    @SerialName("quizDragAndDrop") val quizDragAndDrops: List<QuizDragAndDrop>
) {

    @Serializable
    data class QuizDragAndDrop(
        @SerialName("explanation") val explanation: String,
        @SerialName("connectedText") val connectedText: Map<String, String>
    )
}