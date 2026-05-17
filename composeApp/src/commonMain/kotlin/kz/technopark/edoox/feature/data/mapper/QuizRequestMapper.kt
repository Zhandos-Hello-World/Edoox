package kz.technopark.edoox.feature.data.mapper

import kz.technopark.edoox.feature.data.model.QuizRequest
import kz.technopark.edoox.feature.domain.model.QuizParams

class QuizRequestMapper(
    private val expectTypeMapper: ExpectTypeMapper,
) {

    fun toQuizRequest(from: QuizParams): QuizRequest {
        return QuizRequest(
            subject = "Language proficiency - " + from.subject.name,
            language = from.language.name,
            questions = from.questions,
            difficult = from.difficult.name,
            expectType = expectTypeMapper.toRequest(from.expectType),
            expectTypeName = from.expectType.name
        )
    }
}