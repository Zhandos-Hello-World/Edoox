package kz.technopark.edoox.feature.data.repository

import kz.technopark.edoox.feature.data.mapper.QuizChoiceMapper
import kz.technopark.edoox.feature.data.mapper.QuizDragAndDropMapper
import kz.technopark.edoox.feature.data.mapper.QuizFillMapper
import kz.technopark.edoox.feature.data.mapper.QuizGeminiPromptMapper
import kz.technopark.edoox.feature.data.mapper.QuizRequestMapper
import kz.technopark.edoox.feature.data.model.QuizChoiceResponseDto
import kz.technopark.edoox.feature.data.model.QuizDragAndDropResponseDto
import kz.technopark.edoox.feature.data.model.QuizFillAnswerResponseDto
import kz.technopark.edoox.feature.data.remote.gemini.GeminiService
import kz.technopark.edoox.feature.domain.model.QuizModel
import kz.technopark.edoox.feature.domain.model.QuizParams
import kz.technopark.edoox.feature.domain.repository.QuizRepository

class DefaultQuizRepository(
    private val geminiService: GeminiService,
    private val quizRequestMapper: QuizRequestMapper,
    private val quizGeminiPromptMapper: QuizGeminiPromptMapper,
    private val quizChoiceMapper: QuizChoiceMapper,
    private val quizFillMapper: QuizFillMapper,
    private val quizDragAndDropMapper: QuizDragAndDropMapper,
) : QuizRepository {

    override suspend fun generateChoiceQuestion(quizParams: QuizParams): List<QuizModel.Choice> {
        val request = quizRequestMapper.toQuizRequest(quizParams)
        val result = geminiService.execute(
            query = quizGeminiPromptMapper.toChoiceGeminiPrompt(request),
            strategy = QuizChoiceResponseDto.serializer(),
        )
        return quizChoiceMapper.toQuizChoiceModel(result)
    }

    override suspend fun generateFillQuestion(quizParams: QuizParams): List<QuizModel.Fill> {
        val request = quizRequestMapper.toQuizRequest(quizParams)
        val result = geminiService.execute(
            query = quizGeminiPromptMapper.toFillInTheBlankGeminiPrompt(request),
            strategy = QuizFillAnswerResponseDto.serializer(),
        )
        return quizFillMapper.toQuizFillModel(result)
    }

    override suspend fun generateDragAndDrop(quizParams: QuizParams): List<QuizModel.DragAndDrop> {
        val request = quizRequestMapper.toQuizRequest(quizParams)
        val result = geminiService.execute(
            query = quizGeminiPromptMapper.toDragAndDropGeminiPrompt(request),
            strategy = QuizDragAndDropResponseDto.serializer(),
        )
        return quizDragAndDropMapper.toQuizDragAndDropModel(result)
    }

}