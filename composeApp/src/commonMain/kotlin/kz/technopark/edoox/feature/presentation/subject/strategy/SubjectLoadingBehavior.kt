package kz.technopark.edoox.feature.presentation.subject.strategy

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kz.technopark.edoox.feature.domain.interactor.QuizInteractor
import kz.technopark.edoox.feature.domain.model.Language
import kz.technopark.edoox.feature.domain.model.QuizParams
import kz.technopark.edoox.feature.presentation.loading.LoadedData
import kz.technopark.edoox.feature.presentation.loading.LoadingBehavior
import kz.technopark.edoox.feature.presentation.mapper.QuizDvoMapper
import kz.technopark.edoox.feature.presentation.model.QuizesDvo
import kz.technopark.edoox.feature.presentation.navigation.AppRouter
import kz.technopark.edoox.feature.presentation.navigation.Screen
import kz.technopark.edoox.feature.presentation.quiz.QuizLauncher
import kz.technopark.edoox.shared.coredata.toErrorType
import org.koin.mp.KoinPlatform.getKoin

@Serializable
class SubjectLoadingBehavior(
    private val subject: QuizParams.Subject,
    private val currentLocale: String,
) : LoadingBehavior<QuizesDvo> {

    @Transient
    private val quizInteractor: QuizInteractor = getKoin().get()

    @Transient
    private val router: AppRouter = getKoin().get()

    @Transient
    private val mapper: QuizDvoMapper = getKoin().get()

    override suspend fun loadData(): QuizesDvo {
        val lang = when (currentLocale) {
            "ru" -> Language.RU
            "kk" -> Language.KZ
            else -> Language.RU
        }
        val models = quizInteractor.getQuizQuestions(
            listOf(
                QuizParams(
                    subject = subject,
                    language = lang,
                    questions = 2,
                    answers = 4,
                    difficult = QuizParams.DIFFICULT.MEDIUM,
                    expectType = QuizParams.ExpectType.CHOICE,
                ), QuizParams(
                    subject = subject,
                    language = lang,
                    questions = 2,
                    answers = 4,
                    difficult = QuizParams.DIFFICULT.MEDIUM,
                    expectType = QuizParams.ExpectType.FIL_ANSWER,
                ), QuizParams(
                    subject = subject,
                    language = lang,
                    questions = 1,
                    answers = 4,
                    difficult = QuizParams.DIFFICULT.MEDIUM,
                    expectType = QuizParams.ExpectType.DRAG_AND_DROP,
                )
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