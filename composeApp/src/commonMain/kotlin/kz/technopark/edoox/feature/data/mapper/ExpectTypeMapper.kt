package kz.technopark.edoox.feature.data.mapper

import kz.technopark.edoox.feature.domain.model.QuizParams

class ExpectTypeMapper {

    fun toRequest(from: QuizParams.ExpectType): String {
        return when (from) {
            QuizParams.ExpectType.CHOICE -> """
                {
                    "questions": [
                    {
                    "question": "string",
                    "explanation": "string",
                    "answers": [
                        { "answer": "string", "correct": true },
                        { "answer": "string", "correct": false },
                        { "answer": "string", "correct": false },
                        { "answer": "string", "correct": false }
                    ]
                    }
                ]
            }
            """.trimIndent()

            QuizParams.ExpectType.FIL_ANSWER -> """
                {
                  "questions": [
                    {
                      "question": "string",
                      "explanation": "string",
                      "correct_answer": "string"
                    }
                  ]
                }
            """.trimIndent()
            QuizParams.ExpectType.DRAG_AND_DROP -> """
                {
                  "quizDragAndDrop": [
                    {
                      "connectedText": {
                        "string": "string",
                        "string": "string",
                        "string": "string"
                      },
                      "explanation": "string",
                    }
                  ]
                }
            """.trimIndent()
        }
    }
}