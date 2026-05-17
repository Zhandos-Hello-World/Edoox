package kz.technopark.edoox.feature.presentation.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kz.technopark.edoox.feature.presentation.navigation.AppRouter
import kz.technopark.edoox.shared.coreui.theme.language.AppTranslations
import kotlin.coroutines.cancellation.CancellationException

class LoadingViewModel(
    private val router: AppRouter,
    appTranslations: AppTranslations,
    loadingLauncher: LoadingLauncher,
) : ViewModel(), LoadingController {
    private val behavior = loadingLauncher.loadingBehavior

    private val _uiState = MutableStateFlow(
        LoadingUiState(
            initialMessages = appTranslations.loadingMaterials + "...",
            dynamicMessages = listOf(
                appTranslations.loadingMaterials + "...",
                appTranslations.calibratingNeuralNetwork +  "...",
                appTranslations.generatingCustomQuiz + "...",
                appTranslations.almostReady + "...",
            ),
        ),
    )
    val uiState: StateFlow<LoadingUiState> = _uiState.asStateFlow()

    override fun startLoading() {
        viewModelScope.launch {
            try {
                val data = behavior.loadData()
                behavior.onSuccessLoaded(
                    value = data,
                )
            } catch (cancellationEx: CancellationException) {
                throw cancellationEx
            } catch (ex: Exception) {
                behavior.onFailLoaded(ex)
            }
        }
    }

    override fun onNavigateBack() = router.pop()
}
