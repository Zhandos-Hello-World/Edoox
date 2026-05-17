package kz.technopark.edoox.feature.domain.model

sealed interface QuizModel {
    val explanation: String

    data class Choice(
        override val explanation: String,
        val questionText: String,
        val answers: List<Answer>,
    ) : QuizModel {

        data class Answer(
            val text: String,
            val isCorrect: Boolean,
        )
    }

    data class Fill(
        override val explanation: String,
        val questionText: String,
        val correctAnswer: String,
    ) : QuizModel

    data class DragAndDrop(
        override val explanation: String,
        val connectedText: Map<String, String>,
    ) : QuizModel
}