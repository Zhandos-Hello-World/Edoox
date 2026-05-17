package kz.technopark.edoox.feature.presentation.subject.strategy

import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kz.technopark.edoox.feature.domain.interactor.QuizInteractor
import kz.technopark.edoox.feature.domain.model.Language
import kz.technopark.edoox.feature.domain.model.QuizModel
import kz.technopark.edoox.feature.domain.model.QuizModels
import kz.technopark.edoox.feature.domain.model.QuizParams
import kz.technopark.edoox.feature.presentation.loading.LoadedData
import kz.technopark.edoox.feature.presentation.loading.LoadingBehavior
import kz.technopark.edoox.feature.presentation.mapper.QuizDvoMapper
import kz.technopark.edoox.feature.presentation.model.QuizDvo
import kz.technopark.edoox.feature.presentation.model.QuizesDvo
import kz.technopark.edoox.feature.presentation.navigation.AppRouter
import kz.technopark.edoox.feature.presentation.navigation.Screen
import kz.technopark.edoox.feature.presentation.quiz.QuizLauncher
import kz.technopark.edoox.shared.coredata.exceptions.GeminiEmptyException
import kz.technopark.edoox.shared.coredata.toErrorType
import org.koin.mp.KoinPlatform.getKoin

@Serializable
class SubjectLoadingBehavior(
    private val subject: QuizParams.Subject,
) : LoadingBehavior<QuizesDvo> {

    @Transient
    private val quizInteractor: QuizInteractor = getKoin().get()

    @Transient
    private val router: AppRouter = getKoin().get()

    @Transient
    private val mapper: QuizDvoMapper = getKoin().get()

    override suspend fun loadData(): QuizesDvo {
//        delay(4000L)
//        val models = quizInteractor.getQuizQuestion(
//            params = QuizParams(
//                subject = subject,
//                language = Language.KZ,
//                questions = 1,
//                answers = 4,
//                difficult = QuizParams.DIFFICULT.MEDIUM,
//                expectType = QuizParams.ExpectType.CHOICE,
//            )
//        )
//        throw GeminiEmptyException()

        val models = QuizModels(
            quizModels = listOf(
                QuizModel.Choice(
                    questionText = "Вопрос #1",
                    answers = listOf(
                        QuizModel.Choice.Answer(
                            text = "answer weo jfopewk fopke wpokf opkeop kfweop kfp ekfpe w fkewop1",
                            isCorrect = false,
                        ),
                        QuizModel.Choice.Answer(
                            text = "answer2",
                            isCorrect = false,
                        ),
                        QuizModel.Choice.Answer(
                            text = "answer3",
                            isCorrect = false,
                        ),
                        QuizModel.Choice.Answer(
                            text = "answer4",
                            isCorrect = true,
                        ),
                    ),
                    explanation = "Explanation for question #1"
                ),
                QuizModel.Choice(
                    questionText = "Вопрос #2",
                    answers = listOf(
                        QuizModel.Choice.Answer(
                            text = "answer1",
                            isCorrect = false,
                        ),
                        QuizModel.Choice.Answer(
                            text = "answer2",
                            isCorrect = false,
                        ),
                        QuizModel.Choice.Answer(
                            text = "answer3",
                            isCorrect = false,
                        ),
                        QuizModel.Choice.Answer(
                            text = "answer4",
                            isCorrect = true,
                        ),
                    ),
                    explanation = "Explanation for question #1"
                ),
                QuizModel.Choice(
                    questionText = "Вопрос #3",
                    answers = listOf(
                        QuizModel.Choice.Answer(
                            text = "answer1",
                            isCorrect = false,
                        ),
                        QuizModel.Choice.Answer(
                            text = "answer2",
                            isCorrect = false,
                        ),
                        QuizModel.Choice.Answer(
                            text = "answer3",
                            isCorrect = false,
                        ),
                        QuizModel.Choice.Answer(
                            text = "answer4",
                            isCorrect = true,
                        ),
                    ),
                    explanation = "Explanation for question #1"
                ),
                QuizModel.Fill(
                    questionText = "Вопрос #4",
                    correctAnswer = "Hello world",
                    explanation = "Explanation for question #1"
                ),
                QuizModel.DragAndDrop(
                    connectedText = mapOf(
                        "answer1" to "answer1",
                        "answer2" to "answer2",
                        "answer3" to "answer3",
                        "answer4" to "answer4",
                    ),
                    explanation = "Explanation for question #1"
                ),
            )
        )
        return mapper.toQuizesDvo(models)
    }

    override suspend fun onFailLoaded(ex: Exception) {
        val errorType = ex.toErrorType()
        router.replace(
            Screen.Error(
                errorLauncher = kz.technopark.edoox.feature.presentation.error.ErrorStateScreenLauncher(
                    behavior = this as LoadingBehavior<LoadedData>,
                    errorType = errorType,
                )
            )
        )
    }

    override suspend fun onSuccessLoaded(value: QuizesDvo) {
        router.replace(
            Screen.Quiz(
                quizLauncher = QuizLauncher(
                    quizesDvo = value,
                ),
            ),
        )
    }
}