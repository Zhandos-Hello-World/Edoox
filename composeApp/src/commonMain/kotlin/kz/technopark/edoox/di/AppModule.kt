package kz.technopark.edoox.di

import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kz.technopark.edoox.feature.data.interactor.DefaultQuizInteractor
import kz.technopark.edoox.feature.data.mapper.ExpectTypeMapper
import kz.technopark.edoox.feature.data.mapper.QuizChoiceMapper
import kz.technopark.edoox.feature.data.mapper.QuizDragAndDropMapper
import kz.technopark.edoox.feature.data.mapper.QuizFillMapper
import kz.technopark.edoox.feature.data.mapper.QuizGeminiPromptMapper
import kz.technopark.edoox.feature.data.mapper.QuizRequestMapper
import kz.technopark.edoox.feature.data.remote.gemini.GeminiService
import kz.technopark.edoox.feature.data.repository.DefaultQuizRepository
import kz.technopark.edoox.feature.data.repository.GeminiRepository
import kz.technopark.edoox.feature.domain.interactor.QuizInteractor
import kz.technopark.edoox.feature.domain.repository.QuizRepository
import kz.technopark.edoox.feature.presentation.error.ErrorStateScreenLauncher
import kz.technopark.edoox.feature.presentation.error.ErrorStateScreenViewModel
import kz.technopark.edoox.feature.presentation.loading.LoadingLauncher
import kz.technopark.edoox.feature.presentation.loading.LoadingViewModel
import kz.technopark.edoox.feature.presentation.main.MainViewModel
import kz.technopark.edoox.feature.presentation.mapper.QuizDvoMapper
import kz.technopark.edoox.feature.presentation.navigation.AppRouter
import kz.technopark.edoox.feature.presentation.navigation.DefaultAppRouter
import kz.technopark.edoox.feature.presentation.quiz.QuizLauncher
import kz.technopark.edoox.feature.presentation.quiz.QuizViewModel
import kz.technopark.edoox.feature.presentation.review.QuizReviewLauncher
import kz.technopark.edoox.feature.presentation.review.QuizReviewViewModel
import kz.technopark.edoox.feature.presentation.subject.SubjectSelectionViewModel
import kz.technopark.edoox.shared.coredata.httpClient
import kz.technopark.edoox.shared.coreui.ThemeManager
import kz.technopark.edoox.shared.coreui.theme.language.AppTranslations
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun appModule() = module {
    single { CoroutineScope(SupervisorJob() + Dispatchers.Default) }

    single<HttpClient> { httpClient }


    single {
        GeminiRepository(
            client = get(),
            apiKey = "AIzaSyC7nB81o0gDPcv2TPBxlMOXJ_NrdohF_JU",
        )
    }

    single {
        GeminiService(
            client = get(),
            apiKey = "AIzaSyC7nB81o0gDPcv2TPBxlMOXJ_NrdohF_JU",
        )
    }

    single<QuizRepository> {
        DefaultQuizRepository(
            geminiService = get(),
            quizRequestMapper = get(),
            quizGeminiPromptMapper = get(),
            quizChoiceMapper = get(),
            quizFillMapper = get(),
            quizDragAndDropMapper = get(),
        )
    }

    single<QuizInteractor> {
        DefaultQuizInteractor(
            quizRepository = get(),
        )
    }

    single<AppRouter> { DefaultAppRouter() }
    single<ThemeManager> { ThemeManager() }


    viewModel {
        MainViewModel(
            geminiRepository = get(),
            themeManager = get(),
            localeManager = get(),
            router = get(),
        )
    }

    viewModel { (appTranslations: AppTranslations) ->
        SubjectSelectionViewModel(
            appTranslations = appTranslations,
            router = get(),
        )
    }

    viewModel {
            (
                appTranslations: AppTranslations,
                launcher: LoadingLauncher,
            ),
        ->
        LoadingViewModel(
            router = get(),
            appTranslations = appTranslations,
            loadingLauncher = launcher,
        )
    }

    viewModel { (launcher: QuizLauncher) ->
        QuizViewModel(
            quizLauncher = launcher,
            router = get(),
        )
    }

    viewModel {
            (
                appTranslations: AppTranslations,
                launcher: ErrorStateScreenLauncher,
            ),
        ->
        ErrorStateScreenViewModel(
            router = get(),
            appTranslations = appTranslations,
            launcher = launcher,
        )
    }

    viewModel {
            (
                appTranslations: AppTranslations,
                launcher: QuizReviewLauncher,
            ),
        ->
        QuizReviewViewModel(
            appTranslations = appTranslations,
            launcher = launcher,
            router = get(),
        )
    }

    factory { ExpectTypeMapper() }
    factory { QuizGeminiPromptMapper() }
    factory { QuizChoiceMapper() }
    factory { QuizFillMapper() }
    factory { QuizDragAndDropMapper() }
    factory {
        QuizRequestMapper(
            expectTypeMapper = get(),
        )
    }

    //PRESENTATION
    factory { QuizDvoMapper() }
}

//fun initModules() {
//    startKoin {
//
//        modules(
//            platformModule(),
//            appModule(),
//        )
//    }
//}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()

        modules(
            platformModule(),
            appModule(),
        )
    }
}