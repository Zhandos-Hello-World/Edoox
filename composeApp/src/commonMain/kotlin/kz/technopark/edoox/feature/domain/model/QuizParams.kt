package kz.technopark.edoox.feature.domain.model

data class QuizParams(
    val subject: Subject,
    val language: Language,
    val questions: Int,
    val answers: Int,
    val difficult: DIFFICULT,
    val expectType: ExpectType,
) {

    enum class DIFFICULT {
        EASY, MEDIUM, HARD;
    }

    enum class ExpectType {
        CHOICE, FIL_ANSWER, DRAG_AND_DROP;
    }

    enum class Subject {
        MATH,
        KZ_HISTORY,
        BIOLOGY,
        KZ_LANGUAGE,
    }
}
