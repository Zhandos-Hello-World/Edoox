package kz.technopark.edoox.feature.data.interactor

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kz.technopark.edoox.feature.domain.interactor.QuizInteractor
import kz.technopark.edoox.feature.domain.model.QuizModels
import kz.technopark.edoox.feature.domain.model.QuizParams
import kz.technopark.edoox.feature.domain.repository.QuizRepository

class DefaultQuizInteractor(
    private val quizRepository: QuizRepository,
) : QuizInteractor {

    override suspend fun getQuizQuestions(params: List<QuizParams>): QuizModels {
        return coroutineScope {
            val results = mutableListOf<QuizModels>()

            params.forEach { param ->
                val question = getQuizQuestion(param)
                results.add(question)

                delay(500L)
            }

            QuizModels(
                quizModels = results.flatMap { it.quizModels },
            )
        }
    }

    override suspend fun getQuizQuestion(params: QuizParams): QuizModels {
        return QuizModels(
            when (params.expectType) {
                QuizParams.ExpectType.CHOICE -> quizRepository.generateChoiceQuestion(
                    quizParams = params,
                )

                QuizParams.ExpectType.FIL_ANSWER -> quizRepository.generateFillQuestion(
                    quizParams = params,
                )

                QuizParams.ExpectType.DRAG_AND_DROP -> quizRepository.generateDragAndDrop(
                    quizParams = params,
                )
            },
        )
    }
}