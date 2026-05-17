package kz.technopark.edoox.feature.presentation.error

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kz.technopark.edoox.feature.presentation.loading.LoadingLauncher
import kz.technopark.edoox.feature.presentation.navigation.AppRouter
import kz.technopark.edoox.feature.presentation.navigation.Screen
import kz.technopark.edoox.shared.coredata.ErrorType
import kz.technopark.edoox.shared.coreui.theme.language.AppTranslations

class ErrorStateScreenViewModel(
    private val router: AppRouter,
    private val appTranslations: AppTranslations,
    launcher: ErrorStateScreenLauncher,
) : ViewModel(), ErrorStateController {
    private val behavior = launcher.behavior

    private val _uiState = MutableStateFlow<ErrorUIState>(createErrorUiState(launcher.errorType))
    val uiState: StateFlow<ErrorUIState> = _uiState.asStateFlow()

    override fun onNavigateBackClick() {
        router.pop()
    }

    override fun onRepeatClick() {
        router.replace(
            Screen.Loading(
                loadingLauncher = LoadingLauncher(
                    titleText = "",
                    loadingBehavior = behavior,
                )
            )
        )
    }

    private fun createErrorUiState(
        errorType: ErrorType,
    ): ErrorUIState {
        return ErrorUIState(
            message = when (errorType) {
                ErrorType.NO_INTERNET -> appTranslations.errorNoInternet
                ErrorType.SERVER_ERROR ->appTranslations.errorServerError
                ErrorType.GEMINI_LIMIT_EXCEEDED -> appTranslations.errorGeminiLimitExceeded
                ErrorType.UNKNOWN -> appTranslations.errorUnknown
            },
        )
    }
}